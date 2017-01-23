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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
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
 * Created by Administrator on 2016/11/7.
 */
public class SendGoodsActivity extends AbActivity {
    @AbIocView(id = R.id.TV_SendRecPerson)
    TextView TV_SendRecPerson;
    @AbIocView(id = R.id.TV_SendRecTel)
    TextView TV_SendRecTel;
    @AbIocView(id = R.id.TV_SendRecAddr)
    TextView TV_SendRecAddr;
    @AbIocView(id = R.id.TV_SendDepTime)
    TextView TV_SendDepTime;
    @AbIocView(id = R.id.TV_SendDepUser)
    TextView TV_SendDepUser;
    @AbIocView(id = R.id.tv_sendgoods, click = "click")
    TextView tv_sendgoods;
    @AbIocView(id = R.id.IV_sendgoodsgoback, click = "click")
    ImageView IV_sendgoodsgoback;
    @AbIocView(id = R.id.IV_installAddImg,click = "click")
    ImageView IV_installAddImg;
    @AbIocView(id = R.id.IV_installUpImg1,longClick = "longclick",click = "click")
    ImageView IV_installUpImg1;
    @AbIocView(id = R.id.IV_installUpImg2,longClick = "longclick",click = "click")
    ImageView IV_installUpImg2;
    @AbIocView(id = R.id.IV_installUpImg3,longClick = "longclick",click = "click")
    ImageView IV_installUpImg3;
    @AbIocView(id =R.id.tv_sendReturn,click = "click")
    TextView tv_sendReturn;
    @AbIocView(id = R.id.LL_senditemArea)
    LinearLayout LL_senditemArea;
    private Intent intent;

    private LogTaskBean.DataBean.LogsBean logsBean;
    private AbHttpUtil mHttpUtil;
    //private int count=0;
    private  AbRequestParams params = new AbRequestParams();
    private Uri tempUri;
    private  File file1 ,file2,file3;
    private File tempFile ;
    private File myCaptureFile;
    private Intent imageintent;
    private int  count=1;
    private ArrayList<LogTaskBean.DataBean.LogsBean> newlist = new ArrayList<>();
    private  StringBuffer buffer = new StringBuffer();
    private int state;
    private Bitmap textBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.sendgoodslayout);

        mHttpUtil = AbHttpUtil.getInstance(SendGoodsActivity.this);
//        设置连接超时
        mHttpUtil.setTimeout(10000);
//       获取上个页面的bean
        Intent intent = getIntent();
        state  = intent.getIntExtra("state",-1);
        if (state==3||state==4){
            LL_senditemArea.setVisibility(View.GONE);
            newlist = (ArrayList<LogTaskBean.DataBean.LogsBean>) intent.getSerializableExtra("newlist");
            for (int i =0;i<newlist.size();i++){
                newlist.get(i).getTaskNum();
                buffer.append(newlist.get(i).getTaskNum()+",");
            }
        }else {
            logsBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("logsBean");
            //     注册并设置值
            setView();
        }
        imageintent   =new Intent(this,ImageShowActivity.class);
    }


    /**
     * 注册控件
     */
    private void setView() {
        TV_SendRecPerson.setText(logsBean.getRecPerson());
        TV_SendRecTel.setText(logsBean.getRecTel());
        TV_SendRecAddr.setText(logsBean.getRecAddr());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        TV_SendDepTime.setText(df.format(new Date()));
        TV_SendDepUser.setText(logsBean.getDepUser());
    }


    public  void  longclick(View view){
        switch (view.getId()){
            case R.id.IV_installUpImg1:
                deleteImage(IV_installUpImg1);
                count=1;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
            case R.id.IV_installUpImg2:
                deleteImage(IV_installUpImg2);
                count=2;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
            case R.id.IV_installUpImg3:
                deleteImage(IV_installUpImg3);
                count =3;
                IV_installAddImg.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void deleteImage(final ImageView image) {
            AbDialogUtil.showAlertDialog(SendGoodsActivity.this, -1, "提示！", "确定删除吗？", new AbAlertDialogFragment.AbDialogOnClickListener() {
                @Override
                public void onPositiveClick() {
                    image.setVisibility(View.GONE);
                }

                @Override
                public void onNegativeClick() {
                    AbDialogUtil.removeDialog(SendGoodsActivity.this);
                }
            });
        }



    /**
     * 点击事件
     *
     * @param view
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_installUpImg1:
                imageintent.putExtra("state",1);
                startActivity(imageintent);
                finish();
                break;
            case R.id.IV_installUpImg2:
                imageintent.putExtra("state",2);
                startActivity(imageintent);
                finish();
                break;
            case R.id.IV_installUpImg3:
                imageintent.putExtra("state",3);
                startActivity(imageintent);
                finish();
                break;
            case R.id.tv_sendReturn:
                //startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.IV_installAddImg:

                AlertDialog.Builder builder = new AlertDialog.Builder(SendGoodsActivity.this);
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
                                        File file =  new File(Environment.getExternalStorageDirectory()+"/BJDLogistics");
                                        if (!file.exists()){
                                            file.mkdirs();//不存在就建一个
                                        }
                                        if (sdCardExist) {
                                            startCamera();
                                        } else {
                                            AbToastUtil.showToast(SendGoodsActivity.this, "SD卡不存在，请检查SD卡！");
                                        }
                                        break;
                                    case 1:
                                        startPick();
                                        break;
                                }
                            }
                        }).show();
            break;
            case R.id.IV_sendgoodsgoback:
                finish();
                break;
            case R.id.tv_sendgoods:
                if (isLoadImage()) {
                params.put("Token", SharedPreferencesSava.getInstance().getStringValue(SendGoodsActivity.this, "Token"));
                    if (state ==3||state==4){
                        params.put("TaskNum",buffer.toString());
                    }else {
                        params.put("TaskNum", logsBean.getTaskNum());
                    }
                params.put("state", "15");
                mHttpUtil.post(FinalURL.URL + "/LogTaskOper", params, new AbStringHttpResponseListener() {
                    @Override
                    public void onStart() {
                        AbDialogUtil.showProgressDialog(SendGoodsActivity.this, -1, "正在请求网络，请稍后");
                    }

                    @Override
                    public void onFinish() {
                        AbDialogUtil.removeDialog(SendGoodsActivity.this);

                    }

                    @Override
                    public void onFailure(int i, String s, Throwable throwable) {
                        AbDialogUtil.removeDialog(SendGoodsActivity.this);
                        AbToastUtil.showToast(SendGoodsActivity.this, "访问网络失败，请重试~");
                    }


                    @Override
                    public void onSuccess(int i, String s) {
                        if (s != null) {
                            try {
                                JSONObject object = new JSONObject(s);
                                if (object.getBoolean("Suc")) {

                                    intent  = new Intent(SendGoodsActivity.this, ReachAcitvity.class);
                                    if (state==3||state==4){
                                        intent.putExtra("state",state);
                                        intent.putExtra("newlist",newlist);
                                    }else {
                                        intent.putExtra("logsBean", logsBean);
                                        intent.putExtra("state",state);
                                    }

                                    //改变车辆状态,成功之后跳转页面
                                    changeCarState("3");
                                } else if (object.getString("Msg").equals("token已失效")){
                                    AbToastUtil.showToast(SendGoodsActivity.this,"您的账号在其他客户端登录！");
                                    startActivity(new Intent(SendGoodsActivity.this, LoginActivity.class));
                                } else {
//                                    如果有异常则直接吐司 服务信息
                                    AbToastUtil.showToast(SendGoodsActivity.this, object.getString("Msg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                }
                break;
        }

    }

    private void changeCarState(String State) {
        AbHttpUtil mAbHttpUtil = AbHttpUtil.getInstance(this);
        // mAbHttpUtil.post(FinalURL.URL + "/CarSign", params,new  );
        AbRequestParams params = new AbRequestParams();
        params.put("State",State);
        params.put("Token",SharedPreferencesSava.getInstance().getStringValue(SendGoodsActivity.this,"Token"));
        mAbHttpUtil.post(FinalURL.URL + "/CarSign",params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.getBoolean("Suc")) {
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SendGoodsActivity.this, object.getString("Msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Toast.makeText(SendGoodsActivity.this, "无网络连接，改变车辆状态失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    /**
     * 调用照相机之后，缩小比例，裁剪，等相关操作
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 102:
                if (resultCode == RESULT_OK){
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
                            textBitmap  = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);


                        try {
                            saveFile(textBitmap, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file1 = myCaptureFile;
                                params.put("file1", file1);
                                String img1 =file1.getAbsolutePath();
                                SharedPreferencesSava.getInstance().savaStringValue(SendGoodsActivity.this,"img1",img1);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        IV_installUpImg1.setImageBitmap(textBitmap);//时间水印
                        IV_installUpImg1.setVisibility(View.VISIBLE);
                        count=2;
                        }
                        break;
                    case 2:
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time = format.format(new Date());

                        if (bitmap!=null){

                            textBitmap = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);


                        try {
                            saveFile(textBitmap, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file2 = myCaptureFile;
                                params.put("file2", file2);
                                String img2 =file2.getAbsolutePath();
                                SharedPreferencesSava.getInstance().savaStringValue(SendGoodsActivity.this,"img2",img2);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        IV_installUpImg2.setImageBitmap(textBitmap);//时间水印
                        IV_installUpImg2.setVisibility(View.VISIBLE);
                        count=3;
                        }
                        break;
                    case 3:
                        file3 = tempFile;
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time = format.format(new Date());
                        if (bitmap!=null){

                            textBitmap = ImageUtil.drawTextToRightBottom(this, bitmap, time, 10, Color.RED, 0, 0);


                        try {
                            saveFile(textBitmap, getPhotoFileName());//bitmap转换file
                            if (myCaptureFile.exists()) {
                                file3 = myCaptureFile;
                                params.put("file3", file3);
                                String img3 =file3.getAbsolutePath();
                                SharedPreferencesSava.getInstance().savaStringValue(SendGoodsActivity.this,"img3",img3);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        IV_installUpImg3.setImageBitmap(textBitmap);//时间水印
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
        tempFile = new File(Environment.getExternalStorageDirectory()+"/BJDLogistics",
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
        tempFile = new File(Environment.getExternalStorageDirectory()+"/BJDLogistics",
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


    public boolean isLoadImage() {
        if (IV_installUpImg1.getDrawable()==null&&IV_installUpImg2.getDrawable()==null&&IV_installUpImg3.getDrawable()==null){
            AbToastUtil.showToast(SendGoodsActivity.this,"最少上传一张照片哦~");
            return false;
        }
        return true;
    }
}
