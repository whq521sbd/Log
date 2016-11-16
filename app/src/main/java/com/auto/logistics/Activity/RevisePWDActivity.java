package com.auto.logistics.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

/**
 * Created by Administrator on 2016/11/14.
 */
public class RevisePWDActivity extends AbActivity {
    @AbIocView(id = R.id.ed_RevOldPwd)
    EditText ed_RevOldPwd;
    @AbIocView(id = R.id.ed_RevNewPwd)
    EditText ed_RevNewPwd;
    @AbIocView(id = R.id.ed_RevConfirmPwd)
    EditText ed_RevConfirmPwd;

    @AbIocView(id = R.id.IV_cleanold, click = "click")
    ImageView IV_cleanold;
    @AbIocView(id = R.id.IV_cleanNew, click = "click")
    ImageView IV_cleanNew;
    @AbIocView(id = R.id.IV_cleanConfirm, click = "click")
    ImageView IV_cleanConfirm;

    @AbIocView(id = R.id.IV_revisegoback, click = "click")
    ImageView IV_revisegoback;
    @AbIocView(id = R.id.tv_revise, click = "click")
    TextView tv_revise;

    private AbHttpUtil mAbHttpUtil;
    private AbRequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.revisepwdlayout);
        setView();
        mAbHttpUtil = AbHttpUtil.getInstance(this);
        mAbHttpUtil.setTimeout(10000);
        params = new AbRequestParams();
    }



    /*
    *
    * 设置控件
    *
    * */

    private void setView() {
//       旧密码观察者
        ed_RevOldPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    IV_cleanold.setVisibility(View.VISIBLE);
                } else {
                    IV_cleanold.setVisibility(View.GONE);
                }
            }
        });

//          新密码观察者
        ed_RevNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    IV_cleanNew.setVisibility(View.VISIBLE);
                } else {
                    IV_cleanNew.setVisibility(View.GONE);
                }
            }
        });

//          确认密码观察者
        ed_RevConfirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {

                    IV_cleanConfirm.setVisibility(View.VISIBLE);
                } else {
                    IV_cleanConfirm.setVisibility(View.GONE);
                }

            }
        });


    }

    /*
    * 点击事件处理
    *
    *
    * */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_revisegoback:
                finish();
                break;
            case R.id.tv_revise:
                if (checkLogin()) {
//                    TODO:修改密码接口
                    params.put("Token", SharedPreferencesSava.getInstance().getStringValue(this, "Token"));
                    mAbHttpUtil.post(FinalURL.URL + "/", params, new AbStringHttpResponseListener() {
                        @Override
                        public void onSuccess(int i, String s) {

                        }

                        @Override
                        public void onStart() {
                            AbDialogUtil.showProgressDialog(RevisePWDActivity.this, -1, "正在提交数据");
                        }

                        @Override
                        public void onFinish() {
                            AbDialogUtil.removeDialog(RevisePWDActivity.this);
                        }

                        @Override
                        public void onFailure(int i, String s, Throwable throwable) {
                            AbDialogUtil.removeDialog(RevisePWDActivity.this);
                            AbToastUtil.showToast(RevisePWDActivity.this, "提交数据失败，请重试~");

                        }
                    });
                }
                break;

//            旧密码复位
            case R.id.IV_cleanold:
                ed_RevOldPwd.setText("");
                break;
//            新密码复位
            case R.id.IV_cleanNew:
                ed_RevNewPwd.setText("");
                break;
//            确认密码复位
            case R.id.IV_cleanConfirm:
                ed_RevConfirmPwd.setText("");
                break;

        }
    }

    /*
    * 登录前密码判断
    *
    * */
    private Boolean checkLogin() {
        String oldpwd = ed_RevOldPwd.getText().toString().trim();
        String oldMD5 = AbMd5.MD5(oldpwd);
        if (AbStrUtil.isEmpty(oldpwd)) {
            AbToastUtil.showToast(this, "旧密码必须填写哦~");
            return false;
        }

        if (!oldMD5.equals(SharedPreferencesSava.getInstance().getStringValue(RevisePWDActivity.this, "MDpwd"))) {
            AbToastUtil.showToast(RevisePWDActivity.this, "两次旧码不一致哦~");
            return false;
        }

        String newpd = ed_RevNewPwd.getText().toString().trim();
        String newmd5 = AbMd5.MD5(newpd);
        if (AbStrUtil.isEmpty(newpd)) {
            AbToastUtil.showToast(this, "新密码必须填写哦~");
            return false;
        }

        String confirmPwd = ed_RevConfirmPwd.getText().toString().trim();
        String confirmMD5 = AbMd5.MD5(confirmPwd);
        if (AbStrUtil.isEmpty(confirmPwd)) {
            AbToastUtil.showToast(this, "确认密码必须填写哦~");
            return false;
        }

        if (!newmd5.equals(confirmMD5)) {
            AbToastUtil.showToast(RevisePWDActivity.this, "两次新密码不一致哦~");
            return false;
        }


//      判断完成后并符合条件后，将数据放置到参数中
        params.put("oldMD5", oldMD5);//原MD密码
        params.put("newmd5", newmd5);//新MD密码
        params.put("confirmMD5", confirmMD5);//确认MD
        return true;
    }


    /*
    * 重写返回键
    *
    * */
//    返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }


}