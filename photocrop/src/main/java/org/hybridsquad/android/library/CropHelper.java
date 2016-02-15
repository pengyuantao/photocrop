package org.hybridsquad.android.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class CropHelper {

    protected static final String TAG = "CropHelper";

    //请求code标记位
    public static final int REQUEST_CROP = 127;
    public static final int REQUEST_CAMERA = 128;
    public static final int REQUEST_PICK = 129;

    protected static final String CROP_CACHE_FOLDER = "ImageCrop";//存储文件的目录
    protected static final String DEFAULT_CACHE_FILE = CropFileUtils.getSDPath() + File.separator + CROP_CACHE_FOLDER;//文件路径
    protected static final String IMAGE_CACHE = "image_cache";//缓存用户选择的图片
    protected static final String IMAGE_CROP = "image_crop";//用户剪裁后的图片
    protected static final String IMAGE_COMPRESS = "image_compress";//图片压缩后的路径

    //是否需要截图的标记位(true为需要切图)
    private boolean isCrop = false;

    //需要调用的回调接口
    private CropCallback mCropCallback;

    //当前类的对象
    private static CropHelper mCropHelper;

    //单例模式的方法
    public static CropHelper getInstance(CropCallback cropCallback) {
        if (mCropHelper == null) {
            mCropHelper = new CropHelper(cropCallback);
        }
        mCropHelper.setCropCallback(cropCallback);
        return mCropHelper;
    }

    public CropHelper(CropCallback cropCallback) {
        this.mCropCallback = cropCallback;
    }

    /**
     * 获取缓存图片的Uri
     *
     * @return Uri
     */
    public static Uri generateUri() {
        return generateBaseUri(IMAGE_CACHE);
    }

    /**
     * 获取裁剪图片Uri
     *
     * @return Uri
     */
    public static Uri generateCropUri() {
        return generateBaseUri(IMAGE_CROP);
    }

    /**
     * 获取压缩图片的Uri
     *
     * @return Uri
     */
    public static Uri generateCompressUri() {
        return generateBaseUri(IMAGE_COMPRESS);
    }

    /**
     * 获取基础Uri的方法
     * @param fileName
     * @return Uri
     */
    public static Uri generateBaseUri(String fileName) {
        if (checkCacheFloder()) {
            return Uri.fromFile(new File(DEFAULT_CACHE_FILE)).buildUpon().appendPath(fileName.concat(System.currentTimeMillis()+".jpg")).build();
        }
        return null;
    }

    /**
     * 判断缓存文件的文件夹是否存在的
     *
     * @return Uri
     */
    private static boolean checkCacheFloder() {
        File cacheFolder = new File(DEFAULT_CACHE_FILE);
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                Log.d(TAG, "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
                return result;
            } catch (Exception e) {
                Log.e(TAG, "generateUri failed: " + cacheFolder, e);
            }
        }
        return true;
    }


    /**
     * 是否裁剪成功
     *
     * @param uri
     * @return boolean
     */
    public boolean isPhotoReallyCropped(Uri uri) {
        File file = new File(uri.getPath());
        long length = file.length();
        return length > 0;
    }

    /**
     * 用户代理Activity中的onActivityResult()
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void handleResult(int requestCode, int resultCode, Intent intent) {
        if (mCropCallback == null) return;

        if (resultCode == Activity.RESULT_CANCELED) {

            mCropCallback.onCancel(requestCode);

        } else if (resultCode == Activity.RESULT_OK) {

            CropParams cropParams = mCropCallback.getCropParams();
            String uriPath = cropParams.getUri().getPath();
            if (cropParams == null) {
                mCropCallback.onFailed("CropCallback's params must not be null!(CropParams 不能为null )");
                return;
            }
            switch (requestCode) {
                case REQUEST_PICK:
                    //如果是相册获取的图片的话需要拷贝文件到我们的缓存的路径
                    Context context = mCropCallback.getCropParams().getContext();
                    if (context != null) {
                        if (intent != null && intent.getData() != null) {
                            //这里返回的data是Uri
                            String path = CropFileUtils.getSmartFilePath(context, intent.getData());
                            Log.d(TAG, path);
                            //将用户选取到的图片缓存到我们自己定义的目录文件下
                            boolean result = CropFileUtils.copyFile(path, uriPath);
                            if (!result) {
                                mCropCallback.onFailed("Copy file to cached folder failed");
                                break;
                            }
                        } else {
                            mCropCallback.onFailed("Returned data is null " + intent);
                            break;
                        }
                    } else {
                        mCropCallback.onFailed("CropCallback's context must not be null!");
                    }
                case REQUEST_CAMERA:
                    //判断是否需要截图
                    if (isCrop) {
                        mCropCallback.handleIntent(buildCropIntent(cropParams), REQUEST_CROP);
                    } else {
                        onPhotoEnsure(mCropCallback, cropParams);
                    }
                    break;
                case REQUEST_CROP:
                    if (isPhotoReallyCropped(cropParams.getUri())) {
                        Log.d(TAG, "Photo cropped success!");
                        onPhotoEnsure(mCropCallback, cropParams);
                    }
                    break;

            }
        }
    }




    /**
     * 当确定了用户所选择的图片
     *
     * @param handler
     * @param cropParams
     */
    private void onPhotoEnsure(CropCallback handler, CropParams cropParams) {
        if (cropParams.isCompress()) {
            Uri originUri = cropParams.getUri();
            Uri compressUri = generateCompressUri();
            CompressImageUtils.compressImageFile(cropParams, originUri, compressUri);
            handler.onCompressed(compressUri);
        } else {
            handler.onPhotoCropped(cropParams.getUri());
        }
    }

    /**
     * 创建一个  不  需要裁剪图片的Intent请求
     *
     */
    public void startGalleryIntent() {
        isCrop = false;
        startGalleryBaseIntent();
    }

    /**
     * 创建一个需要裁剪的Intent（浏览图片）
     *
     */
    public void startGalleryCropIntent() {
        isCrop = true;
        startGalleryBaseIntent();
    }


    /**
     * 调用相册的Intent
     */
    private void startGalleryBaseIntent() {
        if (mCropCallback == null) {
            return;
        }
        mCropCallback.handleIntent(new Intent(Intent.ACTION_PICK)
                .setType("image/*")
                .putExtra(MediaStore.EXTRA_OUTPUT, mCropCallback.getCropParams().getUri())
                .putExtra("rotateToCorrectDirection", true
                ), REQUEST_PICK);


    }

    /**
     * 创建一个  不  需要裁剪的Intent（相机拍照）
     *
     */
    public void startCameraIntent() {
        isCrop = false;
        startCameraBaseIntent();
    }

    /**
     * 创建一个需要裁剪的Intent（相机拍照）
     *
     */
    public void startCameraCropIntent() {
        isCrop = true;
        startCameraBaseIntent();
    }

    private void startCameraBaseIntent() {
        if (mCropCallback == null) {
            return;
        }
//        clearCache();
        mCropCallback.handleIntent(new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra("rotateToCorrectDirection", true)
                .putExtra(MediaStore.EXTRA_OUTPUT, mCropCallback.getCropParams().getUri()), REQUEST_CAMERA);
    }


    /**
     * 创建一个裁剪的Intent
     *
     * @param params
     * @return Intent
     */
    private Intent buildCropIntent(CropParams params) {
        return new Intent("com.android.camera.action.CROP")
                .setDataAndType(params.getUri(), params.getType())
                .putExtra("crop", "true")
                .putExtra("scale", params.isScale())
                .putExtra("aspectX", params.getAspectX())
                .putExtra("aspectY", params.getAspectY())
                .putExtra("outputX", params.getOutputX())
                .putExtra("outputY", params.getOutputY())
                .putExtra("return-data", params.isReturnData())
                .putExtra("outputFormat", params.getOutputFormat())
                .putExtra("noFaceDetection", params.isNoFaceDetection())
                .putExtra("scaleUpIfNeeded", params.isScaleUpIfNeeded())
                .putExtra("rotateToCorrectDirection", params.isRotateToCorrectDirection())
                .putExtra(MediaStore.EXTRA_OUTPUT, params.setUri(generateCropUri()));
    }



    /**
     * 删除所有的图片文件
     */
    public void clearCacheDir() {
        CropFileUtils.deleteFolderFile(DEFAULT_CACHE_FILE, true);
    }

    /**
     * 只是进行展示
     *
     * @param imageView
     * @param params
     * @param uri
     */
    public void display(ImageView imageView, CropParams params, Uri uri) {
        if (params.isCompress()) {
            imageView.setImageBitmap(BitmapUtil.decodeUriAsBitmap(params.getContext(), uri));
        } else {
            //这里进行简单地展示
            Uri newUri = generateBaseUri(System.currentTimeMillis() + "");
            CompressImageUtils.compressImageFile(params, uri, newUri);
            imageView.setImageBitmap(BitmapUtil.decodeUriAsBitmap(params.getContext(), newUri));
            //删除刚刚缓存的地方
            CropFileUtils.deleteFile(newUri.getPath());
        }

    }

    /**
     * 设置回调接口
     * @param cropCallback
     */
    public void setCropCallback(CropCallback cropCallback) {
        this.mCropCallback = cropCallback;
    }
}
