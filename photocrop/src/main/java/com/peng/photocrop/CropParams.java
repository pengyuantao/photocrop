package com.peng.photocrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;


public class CropParams {

    public static final String CROP_TYPE = "image/*";
    public static final String OUTPUT_FORMAT = Bitmap.CompressFormat.JPEG.toString();

    public static final int DEFAULT_ASPECT = 1;
    public static final int DEFAULT_OUTPUT = 300;
    public static final int DEFAULT_COMPRESS_WIDTH = 320;
    public static final int DEFAULT_COMPRESS_HEIGHT = 320;
    public static final int DEFAULT_COMPRESS_QUALITY = 100;

    private String type;//类型
    private String outputFormat;//输出格式
    private String crop;

    private boolean scale;//尺寸
    private boolean scaleUpIfNeeded;//尺寸是否允许黑边
    private boolean returnData;//返回的数据（是否将图片数据设为bitmap）
    private boolean noFaceDetection;//是否开始人脸识别（没卵用，暂时放着）
    private boolean compress;//是否进行文件压缩
    private boolean rotateToCorrectDirection;//含义是旋转到正确的方向（然并卵，flase和true没啥区别）
    private int compressWidth;//压缩宽度
    private int compressHeight;//压缩高度
    private int compressQuality;//压缩质量

    private int aspectX;//x比
    private int aspectY;//y比

    private int outputX;//x宽度
    private int outputY;//y高度

    private Context context;
    private Uri uri;

    public CropParams(Context context) {
        this.context = context;
        type = CROP_TYPE;
        outputFormat = OUTPUT_FORMAT;
        crop = "true";
        scale = true;
        returnData = false;
        noFaceDetection = true;
        scaleUpIfNeeded = true;
        rotateToCorrectDirection = true;
        compress = false;
        compressQuality = DEFAULT_COMPRESS_QUALITY;
        compressWidth = DEFAULT_COMPRESS_WIDTH;
        compressHeight = DEFAULT_COMPRESS_HEIGHT;
        aspectX = DEFAULT_ASPECT;
        aspectY = DEFAULT_ASPECT;
        outputX = DEFAULT_OUTPUT;
        outputY = DEFAULT_OUTPUT;
        uri = CropHelper.generateUri();
    }

    /**
     * 设置XY之间的比例
     * @param aspectX
     * @param aspectY
     */
    public void setAspect(int aspectX, int aspectY) {
        this.aspectX = aspectX;
        this.aspectY = aspectY;
    }

    /**
     * 设置输出图片的长宽度
     * @param outputX
     * @param outputY
     */
    public void setOutput(int outputX ,int outputY){
        this.outputX = outputX;
        this.outputY = outputY;
    }

    public void setCompress(int compressWidth, int compressHeight) {
        this.setCompress(compressWidth,compressHeight,DEFAULT_COMPRESS_QUALITY);
    }

    /**
     * 设置图片的压缩大小和压缩质量
     * @param compressWidth
     * @param compressHeight
     * @param compressQuality
     */
    public void setCompress(int compressWidth,int compressHeight ,int compressQuality){
        this.compressWidth = compressWidth;
        this.compressHeight = compressHeight;
        this.compressQuality = compressQuality;
    }

    /**
     * 设置是否进行压缩图片
     * @param compress
     */
    public void setCompress(boolean compress) {
        this.compress = compress;
    }



    public Context getContext() {
        return context;
    }

    public String getType() {
        return type;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public String getCrop() {
        return crop;
    }

    public boolean isScale() {
        return scale;
    }

    public boolean isScaleUpIfNeeded() {
        return scaleUpIfNeeded;
    }

    public boolean isReturnData() {
        return returnData;
    }

    public boolean isNoFaceDetection() {
        return noFaceDetection;
    }

    public boolean isCompress() {
        return compress;
    }

    public boolean isRotateToCorrectDirection() {
        return rotateToCorrectDirection;
    }

    public int getCompressWidth() {
        return compressWidth;
    }

    public int getCompressHeight() {
        return compressHeight;
    }

    public int getCompressQuality() {
        return compressQuality;
    }

    public int getAspectX() {
        return aspectX;
    }

    public int getAspectY() {
        return aspectY;
    }

    public int getOutputX() {
        return outputX;
    }

    public int getOutputY() {
        return outputY;
    }

    public Uri getUri() {
        return uri;
    }

    public Uri setUri(Uri uri){
        this.uri = uri;
        return uri;
    }
}
