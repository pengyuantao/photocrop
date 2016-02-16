package org.hybridsquad.android.photocropper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.peng.photocrop.CropCallback;
import com.peng.photocrop.CropHelper;
import com.peng.photocrop.CropParams;
import com.squareup.picasso.Picasso;


public class TestActivity extends AppCompatActivity implements CropCallback, View.OnClickListener {

    public static final String TAG = "TestActivity";

    ImageView mImageView;

    CropParams mCropParams;
    private CropHelper mCropHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mCropParams = new CropParams(this);
        mImageView = (ImageView) findViewById(R.id.image);
        mCropHelper = CropHelper.getInstance(this);
        mCropParams.setAspect(120,57);
        mCropParams.setOutput(600,285);

        findViewById(R.id.bt_crop_capture).setOnClickListener(this);
        findViewById(R.id.bt_crop_gallery).setOnClickListener(this);
        findViewById(R.id.bt_capture).setOnClickListener(this);
        findViewById(R.id.bt_gallery).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.bt_crop_capture: {
                mCropParams.setCompress(false);
                mCropHelper.startCameraCropIntent();
            }
            break;
            case R.id.bt_crop_gallery: {
                mCropParams.setCompress(false);
                mCropHelper.startGalleryCropIntent();
            }
            break;
            case R.id.bt_capture: {
                mCropParams.setCompress(false);
                mCropHelper.startCameraIntent();

            }
            break;
            case R.id.bt_gallery: {
                mCropParams.setCompress(false);
                mCropHelper.startGalleryIntent();
            }
            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //交给代理方法去处理
        mCropHelper.handleResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCropHelper.clearCacheDir();
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
//        Picasso.with(this).load(uri).resize(mImageView.getMeasuredWidth(),mImageView.getMeasuredHeight()).centerInside().skipMemoryCache().into(mImageView);
        Log.i(TAG, "Crop Uri in path: " + uri.getPath());
        mCropHelper.display(mImageView,mCropParams,uri);

    }

    @Override
    public void onPhotoSelected(Uri uri) {
        Log.i(TAG, "Select Uri in path: " + uri.getPath());
        mCropHelper.display(mImageView, mCropParams, uri);
//        Picasso.with(this).load(uri).resize(mImageView.getMeasuredWidth(),mImageView.getMeasuredHeight()).centerInside().skipMemoryCache().into(mImageView);
    }

    @Override
    public void onPhotoTaken(Uri uri) {
        Log.i(TAG, "Taken Uri in path: " + uri.getPath());
        mCropHelper.display(mImageView, mCropParams, uri);
//        Picasso.with(this).load(uri).resize(mImageView.getMeasuredWidth(),mImageView.getMeasuredHeight()).centerInside().into(mImageView);
    }

    @Override
    public void onPhotoCompressed(Uri uri) {
        Log.i(TAG, "Compress Uri in path: " + uri.getPath());
        mCropHelper.display(mImageView, mCropParams, uri);
//        Picasso.with(this).load(uri).resize(mImageView.getMeasuredWidth(),mImageView.getMeasuredHeight()).centerInside().skipMemoryCache().into(mImageView);
    }



    @Override
    public void onCancel(int requestCode) {
        Toast.makeText(this, "取消了操作!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, "操作失败: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleIntent(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }
}
