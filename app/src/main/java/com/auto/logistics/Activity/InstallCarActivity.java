package com.auto.logistics.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
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
    @AbIocView(id = R.id.IV_installUpImg1)
    ImageView IV_installUpImg1;
    @AbIocView(id = R.id.IV_installUpImg2)
    ImageView IV_installUpImg2;
    @AbIocView(id = R.id.IV_installUpImg3)
    ImageView IV_installUpImg3;
    @AbIocView(id = R.id.IV_installback, click = "click")
    ImageView IV_installback;
    @AbIocView(id = R.id.tv_installcommit, click = "click")
    private TextView tv_installcommit;
    private LogTaskBean.DataBean.LogsBean logsBean;
    private int count = 0;
    private Uri tempUri;
    private AbHttpUtil mAbHttpUtil;
    private File file1, file2, file3;
    private File tempFile;
    AbRequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.installcarlayout);
        mAbHttpUtil = AbHttpUtil.getInstance(InstallCarActivity.this);
        mAbHttpUtil.setTimeout(10000);
        params = new AbRequestParams();
        //首先获取上个页面传过来的数据
        Intent intent = getIntent();
        logsBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("logsBean");
//        设置值
        setView();
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


    //点击事件
    public void click(View view) {
        switch (view.getId()) {
//            提交操作
            case R.id.tv_installcommit:
                if (isLoadImageViews()) {
                params.put("Token", SharedPreferencesSava.getInstance().getStringValue(InstallCarActivity.this, "Token"));
                params.put("TaskNum", logsBean.getTaskNum());
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
                                    intent.putExtra("logsBean", logsBean);
                                    startActivity(intent);
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
                count++;
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
                    startPhotoZoom(tempUri, 1, 1, 500, 500);
                }
                break;
            case 103:
                if (null != data) {
                    startPhotoZoom(data.getData(), 1, 1, 500, 500);
                }
                break;
            case 104:
                Bitmap bitmap = decodeUriAsBitmap(tempUri);// decode bitmap
                switch (count) {
                    case 1:
                        file1 = tempFile;

                        if (file1.exists()) {
                            params.put("file1", file1);
                            IV_installUpImg1.setImageBitmap(bitmap);
                            IV_installUpImg1.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 2:
                        file2 = tempFile;
                        if (file2.exists()) {
                            params.put("file2", file2);
                            IV_installUpImg2.setImageBitmap(bitmap);
                            IV_installUpImg2.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 3:
                        file3 = tempFile;
                        if (file3.exists()) {
                            params.put("file3", file3);
                            IV_installUpImg3.setImageBitmap(bitmap);
                            IV_installUpImg3.setVisibility(View.VISIBLE);
                            IV_installAddImg.setVisibility(View.GONE);
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


    /**
     * @return 最少上传一张照片
     */
    public boolean isLoadImageViews() {
        if (IV_installUpImg1.getDrawable()==null&&IV_installUpImg2.getDrawable()==null&&IV_installUpImg3.getDrawable()==null){
            AbToastUtil.showToast(InstallCarActivity.this,"最少上传一张照片哦~");
            return false;
        }

        return true;
    }
}
