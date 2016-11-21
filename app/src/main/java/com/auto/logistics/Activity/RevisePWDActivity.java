package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.ab.util.AbMd5;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.auto.logistics.Utills.SharedPreferencesSava;

import org.json.JSONException;
import org.json.JSONObject;

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


    /**
     * 注册控件
     */
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

//          新密码观察者模式
        ed_RevNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    IV_cleanNew.setVisibility(View.VISIBLE);//如果输入后的数字大于1之后，就显示图标
                } else {
                    IV_cleanNew.setVisibility(View.GONE);// 否则就隐藏图标
                }
            }
        });

//          确认密码观察者模式
        ed_RevConfirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {

                    IV_cleanConfirm.setVisibility(View.VISIBLE);//如果输入后的数字大于1之后，就显示图标
                } else {
                    IV_cleanConfirm.setVisibility(View.GONE);// 否则就隐藏图标
                }

            }
        });


    }

    /**
     * @param view 点击事件
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_revisegoback:
                finish();
                break;
            case R.id.tv_revise:
                if (checkLogin()) {
                    params.put("Token", SharedPreferencesSava.getInstance().getStringValue(this, "Token"));
                    mAbHttpUtil.post(FinalURL.URL + "/EditPwd", params, new AbStringHttpResponseListener() {
                        @Override
                        public void onSuccess(int i, String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                if (object.getBoolean("Suc")){
                                    AbToastUtil.showToast(RevisePWDActivity.this,"修改成功!请重新登录！");
                                    SharedPreferencesSava.getInstance().savaStringValue(RevisePWDActivity.this,"MDpwd","");//将密码清空，返回登录时，判读条件为不通过
                                    finish();
                                    startActivity(new Intent(RevisePWDActivity.this,LoginActivity.class));
                                }else {
                                    String msg = object.getString("Msg");
                                    Log.e("RevisePWDActivity", msg);//如果登录不成功则打印日志
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

    /**
     * @return false 提交数据前检验密码,如果不能满足条件返回false,
     *
     * 如果满足条件 返回ture,并将数据添加到参数中
     */
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

        if (newpd.length()<6||newpd.length()>12) {
            AbToastUtil.showToast(RevisePWDActivity.this,"密码长度不能小于6未且不能超过12位哦~");
            return false;
        }

        if (AbStrUtil.isChinese(newpd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"密码不能为中文字符哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(newpd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"新密码不能为空哦~");
            return false;
        }
        if (AbStrUtil.isContainChinese(newpd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"密码中不能包含中文字符哦~");
        }


        if (confirmPwd.length()<6||confirmPwd.length()>12) {
            AbToastUtil.showToast(RevisePWDActivity.this,"确认密码长度不能小于6未且不能超过12位哦~");
            return false;
        }

        if (AbStrUtil.isChinese(confirmPwd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"确认密码不能为中文字符哦~");
            return false;
        }

        if (AbStrUtil.isEmpty(confirmPwd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"确认新密码不能为空哦~");
            return false;
        }
        if (AbStrUtil.isContainChinese(confirmPwd)) {
            AbToastUtil.showToast(RevisePWDActivity.this,"确认密码中不能包含中文字符哦~");
        }


//      判断完成后并符合条件后，将数据放置到参数中
        params.put("oldPwd", oldMD5);//原MD密码
        params.put("pwd", newmd5);//新MD密码
        return true;
    }

    /**
     * 重写Back返回键
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


}
