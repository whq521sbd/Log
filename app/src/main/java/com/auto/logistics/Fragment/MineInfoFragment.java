package com.auto.logistics.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.cache.AbDiskBaseCache;
import com.ab.fragment.AbAlertDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.image.AbImageLoader;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.auto.logistics.Activity.DispatchNotesActivity;
import com.auto.logistics.Activity.RevisePWDActivity;
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
 * Created by Administrator on 2016/11/12.
 */

public class MineInfoFragment extends Fragment implements View.OnClickListener {
    private TextView TV_exit;
    private ImageView IV_Headimg;
    private AbRequestParams params;
    private AbHttpUtil mHttpUtil;
    private TextView TV_MineUserName;
    private File tempFile;
    private Uri tempUri;
    private LinearLayout LL_revisePWD, LL_dispatchNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mineinfolayout, null);
        params = new AbRequestParams();
        mHttpUtil = AbHttpUtil.getInstance(getActivity());
        initView(view);
        setView();
//       模拟接口，网络获取图片
        String url = "http://img.woyaogexing.com/2016/11/15/c5716e080796558e!200x200.jpg";
        AbImageLoader loader = AbImageLoader.getInstance(getActivity());
        loader.display(IV_Headimg, url);
        return view;
    }

    //退出登录
    private void exit() {
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(getActivity(), "Token"));
        mHttpUtil.post(FinalURL.URL + "/LogOut", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                if (s != null) {
                    try {
                        JSONObject object = new JSONObject(s);
                        if (object.getBoolean("Suc")) {
//                            sp密码设为空，判断自动登录，设置状态不通过
                            SharedPreferencesSava.getInstance().savaStringValue(getActivity(), "MDpwd", "");
                            getActivity().finish();
                            System.exit(0);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

            }
        });
    }

    private void initView(View view) {
        TV_exit = (TextView) view.findViewById(R.id.TV_exit);
        IV_Headimg = (ImageView) view.findViewById(R.id.IV_Headimg);
        TV_MineUserName = (TextView) view.findViewById(R.id.TV_MineUserName);
        LL_revisePWD = (LinearLayout) view.findViewById(R.id.LL_revisePWD);
        LL_dispatchNotes = (LinearLayout) view.findViewById(R.id.LL_dispatchNotes);

    }

    private void setView() {
//        tv_phone.setText(LogsBean.getRecTel());
        TV_exit.setOnClickListener(this);
        IV_Headimg.setOnClickListener(this);
        TV_MineUserName.setText(SharedPreferencesSava.getInstance().getStringValue(getActivity(), "username"));
        LL_revisePWD.setOnClickListener(this);
        LL_dispatchNotes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LL_dispatchNotes:
                startActivity(new Intent(getActivity(), DispatchNotesActivity.class));

                break;
            case R.id.LL_revisePWD:
                startActivity(new Intent(getActivity(), RevisePWDActivity.class));
                break;
            case R.id.TV_exit:
                AbDialogUtil.showAlertDialog(getActivity(), -1, "退出", "您确定要退出程序并注销用户吗？", new AbAlertDialogFragment.AbDialogOnClickListener() {
                    @Override
                    public void onPositiveClick() {
                        exit();
                    }

                    @Override
                    public void onNegativeClick() {
                        AbDialogUtil.removeDialog(getActivity());
                    }
                });
                break;


            case R.id.IV_Headimg:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择头像")
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
                                            AbToastUtil.showToast(getActivity(), "SD卡不存在，请检查SD卡！");
                                        }
                                        break;
                                    case 1:
                                        startPick();
                                        break;
                                }
                            }
                        }).show();

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
                if (resultCode == getActivity().RESULT_OK) {
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
                IV_Headimg.setImageBitmap(bitmap);
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
            bitmap = BitmapFactory.decodeStream(getContext().getContentResolver()
                    .openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


}
