package com.auto.logistics.Activity;

import android.os.Bundle;
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
 * Created by Administrator on 2016/11/30.
 */

public class EditPhoneActivity extends AbActivity {

    @AbIocView(id = R.id.tv_Rback, click = "click")
    private ImageView tv_Rback;
    @AbIocView(id = R.id.tv_getcode, click = "click")
    TextView tv_getcode;
    @AbIocView(id = R.id.ed_phoneNumber)
    EditText ed_phoneNumber;
    @AbIocView(id = R.id.ed_Identifyingcode)
    EditText ed_Identifyingcode;
    @AbIocView(id = R.id.ed_NewPhone)
    EditText ed_NewPhone;

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

    @AbIocView(id = R.id.tv_resetting, click = "click")
    TextView tv_resetting;
    private AbHttpUtil mAbHttpUtil;
    private AbRequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.editphonelayout);
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
        ed_NewPhone.addTextChangedListener(new TextWatcher() {
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


    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_resetting:
                if (checkPwd()) {
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
                ed_NewPhone.setText("");
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
            //                点击后获取验证码并设置60秒倒计时，倒计时期间，设置按键不可用
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tv_getcode, 60000, 1000);
            countDownTimerUtils.start();
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

    /**
     * 检查手机号，是否正确
     * @return
     */
    private boolean checkphone() {
        String phoneNum = ed_phoneNumber.getText().toString().trim();
        if (AbStrUtil.isChinese(phoneNum)){
            AbToastUtil.showToast(EditPhoneActivity.this, "手机号不能为中文哦~");
            return false;
        }
        if (AbStrUtil.isEmpty(phoneNum)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "手机号不能为空哦~");
            return false;
        }

        if (phoneNum.length() < 11 || phoneNum.length() > 11) {
            AbToastUtil.showToast(EditPhoneActivity.this, "手机号不正确哦~");
            return false;
        }
        return true;
    }


    /**
     * 重置密码前检查 新手机好，身份证，验证码等信息
     * @return true 验证通过，否则验证失败
     */
    private Boolean checkPwd() {

        String newPhone = ed_NewPhone.getText().toString().trim();

        String idcode = ed_Identifyingcode.getText().toString().trim();
        String IdNumber = ed_IdNumber.getText().toString().trim();

        if (AbStrUtil.isEmpty(IdNumber)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "身份证号码不能为空哦~");
            return false;
        }

        if (AbStrUtil.isChinese(IdNumber)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "身份证号码不能为中文哦~");
            return false;
        }

        if (IdNumber.length() < 18 || IdNumber.length() > 18) {
            AbToastUtil.showToast(EditPhoneActivity.this, "请输入正确的身份证号码哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(idcode)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "验证码不能为空哦~");
            return false;
        }

        if (idcode.length() < 6 || idcode.length() > 6) {
            AbToastUtil.showToast(EditPhoneActivity.this, "验证码输入位数不正确哦~");
            return false;
        }


        if (newPhone.length() < 11 || newPhone.length() > 11) {
            AbToastUtil.showToast(EditPhoneActivity.this, "输入的新手机号不正确哦~");
            return false;
        }

        if (AbStrUtil.isChinese(newPhone)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "新手机号不能为中文字符哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(newPhone)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "新手机号不能为空哦~");
            return false;
        }
        if (AbStrUtil.isContainChinese(newPhone)) {
            AbToastUtil.showToast(EditPhoneActivity.this, "新手机中不能包含中文字符哦~");
        }




        return true;
    }

    /**
     * 请求网络，修改密码
     */
    public void getData() {
        params.put("Token", SharedPreferencesSava.getInstance().getStringValue(EditPhoneActivity.this, "Token"));

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
