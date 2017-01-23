package com.auto.logistics.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.ab.util.AbMd5;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.ContentObserver.SMSContentObserver;
import com.auto.logistics.R;
import com.auto.logistics.Utills.CountDownTimerUtils;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

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
    @AbIocView(id = R.id.ed_IdNumber)
    EditText ed_IdNumber;
    @AbIocView(id = R.id.imgclean0, click = "click")
    ImageView imgclean0;
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
    private AbRequestParams params;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ed_Identifyingcode.setText(msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.misspwdlayout);
        SMSContentObserver smsContentObserver = new SMSContentObserver(
                MissPwdAcitvity.this, handler);

        MissPwdAcitvity.this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms/"), true, smsContentObserver);

        mAbHttpUtil = AbHttpUtil.getInstance(this);
        params = new AbRequestParams();
        setView();

    }

    private void setView() {
//        身份证输入观察者
        ed_IdNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imgclean0.setVisibility(View.VISIBLE);
                } else {
                    imgclean0.setVisibility(View.GONE);
                }
            }
        });

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
                if (checkphone() && checkPwd()) {
                    getData();
                }
                break;
            case R.id.imgclean0:
                ed_IdNumber.setText("");
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
        if (checkphone()) {

            mAbHttpUtil.post(FinalURL.URL + "/ForgetPwdCode", params, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {
                    if (s!= null) {
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getBoolean("Suc")) {
                                //                点击后获取验证码并设置60秒倒计时，倒计时期间，设置按键不可用
                                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tv_getcode, 60000, 1000);
                                countDownTimerUtils.start();
                                AbToastUtil.showToast(MissPwdAcitvity.this, "获取验证码成功！");
                            } else {
                                AbToastUtil.showToast(MissPwdAcitvity.this, object.getString("Msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        AbToastUtil.showToast(MissPwdAcitvity.this, "无网络连接！");
                    }
                }

                @Override
                public void onStart() {
                    AbDialogUtil.showProgressDialog(MissPwdAcitvity.this,-1,"正在获取验证码");
                }

                @Override
                public void onFinish() {
                    AbDialogUtil.removeDialog(MissPwdAcitvity.this);
                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {
                    AbDialogUtil.removeDialog(MissPwdAcitvity.this);
                }
            });
        }
    }


    /**
     * 请求网络，修改密码
     */
    public void getData() {
        mAbHttpUtil.post(FinalURL.URL + "/NewPwd", params, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                if (s != null) {
                    try {
                        JSONObject object = new JSONObject(s);
                        if (object.getBoolean("Suc")) {
//                            提示
                            AbToastUtil.showToast(MissPwdAcitvity.this, "密码修改成功，重新登录吧~");
//                            返回登录页面
                            finish();
                        } else {
                            AbToastUtil.showToast(MissPwdAcitvity.this, object.getString("Msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    AbToastUtil.showToast(MissPwdAcitvity.this, "无网络连接！");

                }
            }

            @Override
            public void onStart() {
                AbDialogUtil.showProgressDialog(MissPwdAcitvity.this,-1,"正在提交数据");

            }

            @Override
            public void onFinish() {
                AbDialogUtil.removeDialog(MissPwdAcitvity.this);

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                AbDialogUtil.removeDialog(MissPwdAcitvity.this);

            }
        });
    }


    /**
     * 检查手机号，是否正确
     *
     * @return
     */
    private boolean checkphone() {
        String phoneNum = ed_phoneNumber.getText().toString().trim();
        if (AbStrUtil.isChinese(phoneNum)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "手机号不能为中文哦~");
            return false;
        }
        if (AbStrUtil.isEmpty(phoneNum)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "手机号不能为空哦~");
            return false;
        }

        if (phoneNum.length() < 11 || phoneNum.length() > 11) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "手机号不正确哦~");
            return false;
        }
        params.put("PhoneNum", phoneNum);
        return true;
    }


    /**
     * 重置密码前检查 密码，确认密码，身份证，验证码等信息
     *
     * @return true 验证通过，否则验证失败
     */
    private Boolean checkPwd() {

        String newpwd = ed_NewMregisterPwd.getText().toString().trim();
        String confirpwd = ed_confirmPwd.getText().toString().trim();

        String idcode = ed_Identifyingcode.getText().toString().trim();
        String IdNumber = ed_IdNumber.getText().toString().trim();

        if (AbStrUtil.isEmpty(IdNumber)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "身份证号码不能为空哦~");
            return false;
        }

        if (AbStrUtil.isChinese(IdNumber)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "身份证号码不能为中文哦~");
            return false;
        }

        if (IdNumber.length() < 18 || IdNumber.length() > 18) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "请输入正确的身份证号码哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(idcode)) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "验证码不能为空哦~");
            return false;
        }

        if (idcode.length() < 6 || idcode.length() > 6) {
            AbToastUtil.showToast(MissPwdAcitvity.this, "验证码输入位数不正确哦~");
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

        String confirMd5 = AbMd5.MD5(confirpwd);
        params.put("pwd", confirMd5);
        params.put("IDNum", IdNumber);
        return true;
    }



    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }


}
