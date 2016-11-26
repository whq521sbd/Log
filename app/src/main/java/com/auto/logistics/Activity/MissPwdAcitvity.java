package com.auto.logistics.Activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbMd5;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;
import com.auto.logistics.Utills.CountDownTimerUtils;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MissPwdAcitvity extends AbActivity {

    @AbIocView(id = R.id.tv_Rback, click = "click")
    private ImageView tv_Rback;
    @AbIocView(id = R.id.tv_getcode, click = "click")
    TextView tv_getcode;
    @AbIocView(id = R.id.ed_phoneNumber)
    EditText ed_phoneNumber;
    @AbIocView(id = R.id.ed_Identifyingcode)
    EditText ed_Identifyingcode;
    @AbIocView(id = R.id.ed_NewMregisterPwd)
    EditText ed_NewMregisterPwd;
    @AbIocView(id = R.id.ed_confirmPwd)
    EditText ed_confirmPwd;
    @AbIocView(id = R.id.imgclean1, click = "click")
    ImageView imgclean1;
    @AbIocView(id = R.id.imgclean2, click = "click")
    ImageView imgclean2;
    @AbIocView(id = R.id.imgclean3, click = "click")
    ImageView imgclean3;
    @AbIocView(id = R.id.imgclean4, click = "click")
    ImageView imgclean4;
    @AbIocView(id = R.id.tv_resetting, click = "click")
    TextView tv_resetting;
    private AbHttpUtil mAbHttpUtil;
    private AbRequestParams params ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.misspwdlayout);
        mAbHttpUtil =  AbHttpUtil.getInstance(this);
        params =  new AbRequestParams();
        setView();
    }

    private void setView() {
//        手机号输入观察者
        ed_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    imgclean1.setVisibility(View.VISIBLE);
                } else {
                    imgclean1.setVisibility(View.GONE);
                }

            }
        });

//      验证码输入观察者
        ed_Identifyingcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    imgclean2.setVisibility(View.VISIBLE);
                } else {
                    imgclean2.setVisibility(View.GONE);
                }

            }
        });

//      新密码输入观察者
        ed_NewMregisterPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    imgclean3.setVisibility(View.VISIBLE);
                } else {
                    imgclean3.setVisibility(View.GONE);
                }


            }
        });

//      确认密码观察者
        ed_confirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    imgclean4.setVisibility(View.VISIBLE);
                } else {
                    imgclean4.setVisibility(View.GONE);
                }


            }
        });


    }




    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_resetting:
                if (check()) {
                    getData();
                }
                break;
            case R.id.imgclean1:
                ed_phoneNumber.setText("");
                break;
            case R.id.imgclean2:
                ed_Identifyingcode.setText("");
                break;
            case R.id.imgclean3:
                ed_NewMregisterPwd.setText("");
                break;
            case R.id.imgclean4:
                ed_confirmPwd.setText("");
                break;

            case R.id.tv_getcode:
//                点击后获取验证码并设置60秒倒计时，倒计时期间，设置按键不可用
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tv_getcode, 60000, 1000);
                countDownTimerUtils.start();
               // getPhoneNumber();//获得手机号
                requestIDcode();//请求获取验证码
                break;
            case R.id.tv_Rback:
                finish();
                break;
        }
    }

    /**
     * 从服务器获取验证码
     */
    private void requestIDcode() {
        mAbHttpUtil.post(FinalURL.URL + "", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {

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

//    /**
//     * 获取手机号码
//     * @return telNumber 获得手机号
//     */
//    private String getPhoneNumber() {
//
//        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
////        String deviceid = tm.getDeviceId(); //获取智能设备唯一编号
//        String telNumber = tm.getLine1Number();//获取本机号码
////        String IMEI = tm.getSimSerialNumber();//获得SIM卡的序号
////        String IMSI = tm.getSubscriberId();//得到用户Id
//        return telNumber;
//    }


    private Boolean check() {

        String newpwd = ed_NewMregisterPwd.getText().toString().trim();
        String confirpwd = ed_confirmPwd.getText().toString().trim();
        String phoneNum = ed_phoneNumber.getText().toString().trim();
        String ed_idcode = ed_Identifyingcode.getText().toString().trim();


        if (AbStrUtil.isEmpty(phoneNum)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "手机号不能为空哦~");
            return false;
        }

        if (phoneNum.length() < 11 || phoneNum.length() > 11) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "手机号不正确哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(ed_idcode)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "验证码不能为空哦~");
            return false;
        }

        if (ed_idcode.length()<6||ed_idcode.length()>6){
            AbToastUtil.showToast(MissPwdAcitvity.this,"验证码输入位数不正确哦~");
            return false;
        }

        if (!newpwd.equals(confirpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "两次新密码不一致哦~");
            return false;
        }

        if (newpwd.length() < 6 || newpwd.length() > 12) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "密码长度不能小于6未且不能超过12位哦~");
            return false;
        }

        if (AbStrUtil.isChinese(newpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "密码不能为中文字符哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(newpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "新密码不能为空哦~");
            return false;
        }
        if (AbStrUtil.isContainChinese(newpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "密码中不能包含中文字符哦~");
        }


        if (confirpwd.length() < 6 || confirpwd.length() > 12) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "确认密码长度不能小于6未且不能超过12位哦~");
            return false;
        }

        if (AbStrUtil.isChinese(confirpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "确认密码不能为中文字符哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(confirpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "确认新密码不能为空哦~");
            return false;
        }
        if (AbStrUtil.isContainChinese(confirpwd)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "确认密码中不能包含中文字符哦~");
            return false;
        }

        return true;
    }

    public void getData() {
        params.put("Token",SharedPreferencesSava.getInstance().getStringValue(MissPwdAcitvity.this,"Token"));

        mAbHttpUtil.post(FinalURL.URL + "", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {

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
}
