package com.auto.logistics.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.ImageUtil;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/4.
 */
public class InstallCarActivity extends AbActivity {
    @AbIocView(id = R.id.TV_intallGoodsTitle)
    TextView TV_intallGoodsTitle;
    @AbIocView(id = R.id.TV_installWeight)
    TextView TV_installWeight;
    @AbIocView(id = R.id.TV_installArea)
    TextView TV_installArea;
    @AbIocView(id = R.id.IV_installAddImg, click = "click")
    ImageView IV_installAddImg;
    @AbIocView(id = R.id.IV_installUpImg1, longClick = "longclick",click = "click")
    ImageView IV_installUpImg1;
    @AbIocView(id = R.id.IV_installUpImg2, longClick = "longclick",click = "click")
    ImageView IV_installUpImg2;
    @AbIocView(id = R.id.IV_installUpImg3, longClick = "longclick",click = "click")
    ImageView IV_installUpImg3;
    @AbIocView(id = R.id.IV_installback, click = "click")
    ImageView IV_installback;
    @AbIocView(id = R.id.tv_installcommit, click = "click")
    private TextView tv_installcommit;
    @AbIocView(id = R.id.tv_Return, click = "click")
    TextView tv_Return;
    @AbIocView(id = R.id.LL_itemArea)
    LinearLayout LL_itemArea;
    private LogTaskBean.DataBean.LogsBean logsBean;
    //private int count = 0;
    private AbRequestParams params;
    private AbHttpUtil mAbHttpUtil;
    private  Intent intent;
    private ArrayList<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<>();
    private  StringBuffer buffer;

    private Uri tempUri;
    private File file1, file2, file3;
    private File tempFile;
    private File myCaptureFile;
    private Bitmap textBitmap1,textBitmap2,textBitmap3;
    private int  count=1;
    private int  state;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.installcarlayout);
        mAbHttpUtil = AbHttpUtil.getInstance(InstallCarActivity.this);
        mAbHttpUtil.setTimeout(10000);
        params = new AbRequestParams();
        intent=new Intent(this,ImageShowActivity.class);

        //首先获取上个页面传过来的数据
        Intent intent = getIntent();
        state  =  intent.getIntExtra("state",-1); //通过状态判断
        if (state==3){
            LL_itemArea.setVisibility(View.GONE);
            newlist = (ArrayList<LogTaskBean.DataBean.LogsBean>) intent.getSerializableExtra("newlist");
            buffer  = new StringBuffer();
            initlist(newlist);
            Log.d("InstallCarActivity", "initlist: "+buffer);
        }else {
            //     正常流程数据
            logsBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("logsBean");
            //        设置值
            setView();
        }


    }

    private void initlist(ArrayList<LogTaskBean.DataBean.LogsBean> newlist) {
        for (int i =0;i<newlist.size();i++){
            newlist.get(i).getTaskNum();
            buffer.append(newlist.get(i).getTaskNum()+",");
        }

    }

    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    //控件赋值
    private void setView() {
        TV_intallGoodsTitle.setText("商品名称：" + logsBean.getGoodsTitle());
        TV_installWeight.setText("重量：" + logsBean.getWeight() + "千克");
        TV_installArea.setText("送达地点：" + logsBean.getArea() + logsBean.getStreet());
    }

    //长按点击
    public void longclick(View view) {
        switch (view.getId()) {
            case R.id.IV_installUpImg1:
//                清空照片
                deleteImage(IV_installUpImg1);
                count = 1;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
            case R.id.IV_installUpImg2:
                deleteImage(IV_installUpImg2);
                count = 2;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
            case R.id.IV_installUpImg3:
                deleteImage(IV_installUpImg3);
                count = 3;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
        }
    }


    //点击事件
    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_installUpImg1:
                intent.putExtra("state",1);
                startActivity(intent);
                break;
            case R.id.IV_installUpImg2:
                intent.putExtra("state",2);
                startActivity(intent);
                break;
            case R.id.IV_installUpImg3:
                intent.putExtra("state",3);
                startActivity(intent);
                break;
//            返回
            case R.id.tv_Return:
                startActivity(new Intent(this, MainActivity.class));
                break;
//            提交操作
            case R.id.tv_installcommit:
                if (isLoadImageViews()) {
                    params.put("Token", SharedPreferencesSava.getInstance().getStringValue(InstallCarActivity.this, "Token"));

                    if (state==3){
                        params.put("TaskNum",buffer.toString());
                    }else {
                        params.put("TaskNum", logsBean.getTaskNum());
                    }
                    params.put("state", "4");

                    mAbHttpUtil.post(FinalURL.URL + "/LogTaskOper", params, new AbStringHttpResponseListener() {
                        @Override
                        public void onStart() {
                            AbDialogUtil.showProgressDialog(InstallCarActivity.this, -1, "正在上传数据");
                        }

                        @Override
                        public void onFinish() {
                            AbDialogUtil.removeDialog(InstallCarActivity.this);
                        }

                        @Override
                        public void onFailure(int i, String s, Throwable throwable) {
                            Log.e("1111", "onFailure: " + throwable);
                            AbDialogUtil.removeDialog(InstallCarActivity.this);
                            AbToastUtil.showToast(InstallCarActivity.this, "上传网络失败,请重试~");
                        }
                        @Override
                        public void onSuccess(int i, String s) {
                            if (s != null) {
                                try {
                                    JSONObject object = new JSONObject(s);
                                    boolean Suc = object.getBoolean("Suc");
                                    if (Suc) {
                                        Intent intent = new Intent(InstallCarActivity.this, SendGoodsActivity.class);
                                        intent.putExtra("state",3);
                                        if (state==3){ //通过状态判断
                                            intent.putExtra("newlist", newlist);
                                            intent.putExtra("state",3);
                                        }else {
                                            intent.putExtra("logsBean", logsBean);//正常流程
                                            intent.putExtra("state",state);
                                        }
                                        startActivity(intent);
                                        finish();
                                    } else if (object.getString("Msg").equals("token已失效")) {
                                        AbToastUtil.showToast(InstallCarActivity.this, "您的账号在其他客户端登录！");
                                        startActivity(new Intent(InstallCarActivity.this, LoginActivity.class));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                break;
            case R.id.IV_installback:// 返回按钮
                finish();
                break;
//            添加照片
            case R.id.IV_installAddImg:
                AlertDialog.Builder builder = new AlertDialog.Builder(InstallCarActivity.this);
                builder.setTitle("上传车辆照片")
                        .setItems(new String[]{"拍照上传", "本地相册"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        // 拍照
                                        //判断sd卡是否存在
                                        boolean sdCardExist = Environment.getExternalStorageState()
                                                .equals(Environment.MEDIA_MOUNTED);
                                        //  创建新文件夹
                                        File file = new File(Environment.getExternalStorageDirectory() + "/BJDLogistics");
                                        if (!file.exists()) {
                                            file.mkdirs();//不存在就建一个
                                        }
                                        if (sdCardExist) {
                                            startCamera();
                                        } else {
                                            AbToastUtil.showToast(InstallCarActivity.this, "SD卡不存在，请检查SD卡！");
                                        }
                                        break;
                                    case 1:
                                        startPick();
                                        break;
                                }
                            }
                        }).show();
                break;
            default:
                break;
        }
    }


    /**
     * 调用照相机之后，缩小比例，裁剪，等相关操作
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 102:
                if (resultCode == RESULT_OK) {
                    startPhotoZoom(tempUri, 1, 1, 1000, 1000);
                }
                break;
            case 103:
                if (null != data) {
                    startPhotoZoom(data.getData(), 1, 1, 1000, 1000);
                }
                break;
            case 104:
                Bitmap bitmap = decodeUriAsBitmap(tempUri);// decode bitmap
                switch (count) {
                    case 1:
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = format.format(new Date());
                        if (bitmap!=null){
                            textBitmap1 = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);

                        try {
                            saveFile(textBitmap1, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file1 = myCaptureFile;
                                params.put("file1", file1);
                                String img1 =file1.getAbsolutePath();
                                SharedPreferencesSava.getInstance().savaStringValue(InstallCarActivity.this,"img1",img1);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        IV_installUpImg1.setImageBitmap(textBitmap1);//时间水印
                        IV_installUpImg1.setVisibility(View.VISIBLE);
                        count=2;
                        }
                        break;
                    case 2:
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time = format.format(new Date());
                        if (bitmap!=null){
                            textBitmap2 = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);

                        try {
                            saveFile(textBitmap2, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file2 = myCaptureFile;
                                params.put("file2", file2);
                                String img2 =file2.getAbsolutePath();//获得文件路径
                                SharedPreferencesSava.getInstance().savaStringValue(InstallCarActivity.this,"img2",img2);//保存路径地址

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        IV_installUpImg2.setImageBitmap(textBitmap2);//时间水印
                        IV_installUpImg2.setVisibility(View.VISIBLE);
                        count=3;
                        }
                        break;
                    case 3:
                        file3 = tempFile;
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time = format.format(new Date());
                        if (bitmap!=null){
                            textBitmap3 = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);


                        try {
                            saveFile(textBitmap3, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file3 = myCaptureFile;
                                params.put("file3", file3);
                            }
                            String img3 =file3.getAbsolutePath();
                            SharedPreferencesSava.getInstance().savaStringValue(InstallCarActivity.this,"img3",img3);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        IV_installUpImg3.setImageBitmap(textBitmap3);//时间水印
                        IV_installUpImg3.setVisibility(View.VISIBLE);
                        IV_installAddImg.setVisibility(View.GONE);
                        count=1;
                        }
                        break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }


    // 调用系统相机
    protected void startCamera() {
        tempFile = new File(Environment.getExternalStorageDirectory() + "/BJDLogistics",
                getPhotoFileName());
        tempUri = Uri.fromFile(tempFile);
        // 调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2); // 调用前置摄像头
        intent.putExtra("autofocus", true); // 自动对焦
        intent.putExtra("fullScreen", false); // 全屏
        intent.putExtra("showActionIcons", false);
        // 指定调用相机拍照后照片的存储路径
        // 如果指定，则Bitmap bmp = (Bitmap) data.getExtras().get("data"); 为null
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);// 将照片存储到指定位置，否则会比较模糊
        startActivityForResult(intent, 102);
    }


    // 调用系统相册
    protected void startPick() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, 103);
    }


    //裁剪照片
    private void startPhotoZoom(Uri uri, int x, int y, int sizex, int sizey) {
        tempFile = new File(Environment.getExternalStorageDirectory() + "/BJDLogistics",
                getPhotoFileName());
        tempUri = Uri.fromFile(tempFile);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以裁剪
        intent.putExtra("crop", true);
        // aspectX,aspectY是宽高的比例
        intent.putExtra("aspectX", x);
        intent.putExtra("aspectY", y);
        // outputX,outputY是裁剪图片的宽高
        intent.putExtra("outputX", sizex);
        intent.putExtra("outputY", sizey);
        intent.putExtra("scale", true);
        // 设置是否返回数据
        intent.putExtra("return-data", false);
//        裁剪完成之后，在这里定义照片名字
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 104);
    }


    //    转换成bitmap格式
    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver()
                    .openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }



    private void deleteImage(final ImageView imageView) {
        AbDialogUtil.showAlertDialog(InstallCarActivity.this, -1, "提示！", "确定删除吗？", new AbAlertDialogFragment.AbDialogOnClickListener() {
            @Override
            public void onPositiveClick() {
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onNegativeClick() {
                AbDialogUtil.removeDialog(InstallCarActivity.this);
            }
        });
    }

    /**
     * bitmap转File
     *
     * @param bm
     * @param fileName
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        String path = Environment.getExternalStorageDirectory() + "/BJDLogistics/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }


    /**
     * @return 最少上传一张照片
     */
    public boolean isLoadImageViews() {
        if (IV_installUpImg1.getDrawable() == null && IV_installUpImg2.getDrawable() == null && IV_installUpImg3.getDrawable() == null) {
            AbToastUtil.showToast(InstallCarActivity.this, "最少上传一张照片哦~");
            return false;
        }

        return true;
    }
}
