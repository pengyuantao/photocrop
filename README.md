选取和裁剪图片工具
=================
![baidu](http://img.my.csdn.net/uploads/201602/16/1455629759_9142.gif "使用效果图") 
功能：
------
1、使用拍照图片<br>
2、使用相册图片<br>
3、拍照并且剪裁<br>
4、选取相册并且剪裁<br>

兼容性：
------
1、解决无法回传大图<br>
2、兼容到6.0<br>
3、解决不同手机对图片旋转，显示不正确<br>

引入工程：
------
eclipse: jar目录下的jar包，解压放入工程即可。<br>
AndroidStudio: 在build.gradle文件里的dependencies中，添加 compile 'com.peng.lib:photocrop:2.4' <br>

如何使用：
------
//创建裁剪参数<br>
CropParams mCropParams = new CropParams(Context);<br>
mCropParams.setAspect(120,57);  //设置长宽比例  <br>
mCropParams.setOutput(600,285); //设置输出长宽值<br>
<br>


//创建裁剪帮组类,设置选取和裁剪回调方法<br>
 mCropHelper = CropHelper.getInstance(CropCallback);<br>
<br>


 //重写onDestroy和onActivityResult方法
 
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //交给代理方法去处理
        mCropHelper.handleResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();<br>
        mCropHelper.clearCacheDir();
    }
    
 //回调方法说明
 <br>
    
    public interface CropCallback {

      void onPhotoCropped(Uri uri);//图片裁剪

      void onPhotoSelected(Uri uri);//图片选择

      void onPhotoTaken(Uri uri);//图片拍照

      void onPhotoCompressed(Uri uri);//图片压缩

      void onCancel(int requestCode);//取消操作

      void onFailed(String message);//失败操作
  
      void handleIntent(Intent intent, int requestCode);//进行startActivityForResult

      CropParams getCropParams();//获取裁剪参数
    }

 
 
