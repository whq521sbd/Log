package com.auto.logistics.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbJsonUtil;
import com.ab.util.AbMd5;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.ab.view.progress.AbCircleProgressBar;
import com.auto.logistics.JavaBean.LoginRequset;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.NetworkUtils;
import com.auto.logistics.Utills.SharedPreferencesSava;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/20.
 */
public class LoginActivity extends AbActivity {
    /*
    * 初始化控件，设置点击事件
    * */
    @AbIocView(id = R.id.tv_missPWD, click = "click")
    TextView tv_missPWD;
    @AbIocView(id = R.id.tv_login, click = "click")
    TextView tv_login;
    @AbIocView(id = R.id.ed_user, click = "click")
    EditText ed_user;
    @AbIocView(id = R.id.ed_pwd, click = "click")
    EditText ed_pwd;
    @AbIocView(id = R.id.IV_cleanall, click = "click")
    ImageView IV_cleanall;
    @AbIocView(id = R.id.IV_showpwd, click = "click")
    ImageView IV_showpwd;
    private AbHttpUtil mHttpUtil;
    private String username;


    //返回键监听相关参数
    private static Boolean isQuit = false;
    private long mExitTime = 0;
    Timer timer = new Timer();
    //    IV_showpwd的状态值
    private int mSata = 0;
    private String MDpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.loginlayout);
        // netManager =  new NetManager(this);
        mHttpUtil = AbHttpUtil.getInstance(this);
        mHttpUtil.setTimeout(10000);
        setview();
//        记住用户名
        ed_user.setText(SharedPreferencesSava.getInstance().getStringValue(LoginActivity.this, "username"));
//        自动登录功能，判断sp中的密码是否为空，并且是否联网状态
        if ((!SharedPreferencesSava.getInstance().getStringValue(LoginActivity.this, "MDpwd").equals("")) && NetworkUtils.isNetworkAvailable(this)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    /**
     * 注册控件
     */
    private void setview() {
        //输入文本使用观察者模式，一旦有变动，即实现回调方法
        ed_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                IV_cleanall.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    IV_cleanall.setVisibility(View.INVISIBLE);
                } else {
                    IV_cleanall.setVisibility(View.VISIBLE);
                }
            }
        });

        //密码文本框输入设置，当检测到有文本输入时，设置可见或者隐藏密码、
        ed_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//当有本文输入之后，设置图标的可见性
                if (s.toString().length() > 0) {
                    IV_showpwd.setVisibility(View.VISIBLE);
                } else {
                    IV_showpwd.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * @param v 点击方法
     */
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_missPWD:
                startActivity(new Intent(LoginActivity.this, MissPwdAcitvity.class));
                break;
            case R.id.tv_login:
                checkUser();
            case R.id.IV_cleanall://输入文字之后，如果有误可以全部清除
                ed_pwd.setText("");
                ed_user.setText("");
                ed_user.requestFocus();//重新获得焦点
                break;
            case R.id.IV_showpwd:
                if (mSata == 0) {
                    ed_pwd.setInputType(0x90);//文本可见
                    mSata = 1;
                    ed_user.clearFocus();//清除焦点，失去焦点后转交给父布局
                    ed_pwd.clearFocus();
                } else {
                    ed_pwd.setInputType(0x81);//文本不可见
                    mSata = 0;
                    ed_pwd.clearFocus();
                    ed_user.clearFocus();
                }
                break;
        }
    }

    /**
     * 跳转前判断用户名和密码
     */
    private void checkUser() {
        username = ed_user.getText().toString().trim();
        String userpwd = ed_pwd.getText().toString().trim();
        if (AbStrUtil.isEmpty(username) || AbStrUtil.isEmpty(userpwd)) {
            AbToastUtil.showToast(LoginActivity.this, "用户名或密码不能为空哦~");
        } else if (username.equals(userpwd)) {
            AbToastUtil.showToast(LoginActivity.this, "用户名和密码不能相同哦~");
        } else {
            MDpwd = AbMd5.MD5(userpwd);
            AbRequestParams params = new AbRequestParams();
            params.put("UserName", username);
            params.put("Pwd", MDpwd);

            //异步访问网络，使用回调方法
            mHttpUtil.post(FinalURL.URL + "/Login", params, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {//访问成功
                    //返回json ，放入JavaBean并解析
                    LoginRequset loginRequset = AbJsonUtil.fromJson(s, LoginRequset.class);
                    if (loginRequset.isSuc()) {
//                       头像路径保存
                        SharedPreferencesSava.getInstance().savaStringValue(LoginActivity.this, "Avatar", loginRequset.getData().getAvatar());
                        //将用户令牌记录sp
                        SharedPreferencesSava.getInstance().savaStringValue(LoginActivity.this, "Token", loginRequset.getData().getToken());
                        //  记住用户名，密码，判断自动登录
                        Log.d("Token", "login: " + loginRequset.getData().getToken());
                        SharedPreferencesSava.getInstance().savaStringValue(LoginActivity.this, "username", username);
                        SharedPreferencesSava.getInstance().savaStringValue(LoginActivity.this, "MDpwd", MDpwd);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        AbToastUtil.showToast(LoginActivity.this, loginRequset.getMsg());
                    }
                }

                @Override
                public void onStart() {//开始访问
                    AbDialogUtil.showProgressDialog(LoginActivity.this, -1, "正在验证您的身份信息");
                }

                @Override
                public void onFinish() {//访问结束
                    AbDialogUtil.removeDialog(LoginActivity.this);
                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {//访问失败
                    if (i != 200) {
                        AbDialogUtil.removeDialog(LoginActivity.this);
                        AbToastUtil.showToast(LoginActivity.this, "无法访问网络，请您检查网络连接~");
                    }
                }
            });

        }
    }


    /**
     * 重写onstart方法已监听网络状态
     */
    @Override
    protected void onStart() {
        goToMain();
        super.onStart();
    }

    /*
    * 此方法是判断网络是否打开(wifi、移动蜂窝)
    * */
    private void goToMain() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            // 如果网络不可用，则弹出对话框，对网络进行设置
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("网络提醒")
                    .setMessage("没有开启网络连接哦，点击确定去打开吧。")
                    .setPositiveButton("确定",
                            new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        String sdkVersion = android.os.Build.VERSION.SDK;
                                        if (Integer.valueOf(sdkVersion) > 10) {
                                            startActivity(new Intent(
                                                    android.provider.Settings.ACTION_SETTINGS));
                                        } else {
                                            startActivity(new Intent(
                                                    android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                        }
                                        dialog.cancel();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                    .setNegativeButton("取消", null);
            builder.create().show();
        }
        super.onStart();
    }


    /**
     * @param keyCode
     * @param event 返回键监听
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isQuit == false) {
                isQuit = true;
                AbToastUtil.showToast(this, "再按一次返回键退出程序哦~");
                TimerTask task = null;
                task = new TimerTask() {
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }


}
