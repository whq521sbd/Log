package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.image.AbImageLoader;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/11/18.
 */
public class DispatchDetailActivity extends AbActivity {
    @AbIocView(id = R.id.IV_dispatchdetaillback, click = "click")
    ImageView IV_dispatchdetaillback;
    @AbIocView(id = R.id.TV_TaskNum)
    TextView TV_TaskNum;
    @AbIocView(id = R.id.TV_Serial)
    TextView TV_Serial;
    @AbIocView(id = R.id.TV_TransNum)
    TextView TV_TransNum;
    @AbIocView(id = R.id.TV_Area)
    TextView TV_Area;
    @AbIocView(id = R.id.TV_Street)
    TextView TV_Street;
    @AbIocView(id = R.id.TV_GoodsTitle)
    TextView TV_GoodsTitle;
    @AbIocView(id = R.id.TV_Weight)
    TextView TV_Weight;
    @AbIocView(id = R.id.TV_RecPerson)
    TextView TV_RecPerson;
    @AbIocView(id = R.id.TV_RecTel)
    TextView TV_RecTel;
    @AbIocView(id = R.id.TV_RecAddr)
    TextView TV_RecAddr;
    @AbIocView(id = R.id.TV_AccTime)
    TextView TV_AccTime;
    @AbIocView(id = R.id.TV_AccUser)
    TextView TV_AccUser;
    @AbIocView(id = R.id.TV_PackTime)
    TextView TV_PackTime;
    @AbIocView(id = R.id.TV_PackUser)
    TextView TV_PackUser;
    @AbIocView(id = R.id.TV_DepTime)
    TextView TV_DepTime;
    @AbIocView(id = R.id.TV_DepUser)
    TextView TV_DepUser;
    @AbIocView(id = R.id.TV_SendTime)
    TextView TV_SendTime;
    @AbIocView(id = R.id.TV_SendUser)
    TextView TV_SendUser;
    @AbIocView(id = R.id.TV_DeliTime)
    TextView TV_DeliTime;
    @AbIocView(id = R.id.TV_DeliUser)
    TextView TV_DeliUser;
    @AbIocView(id = R.id.IV_pickPic0,click = "click")
    ImageView IV_pickPic0;
    @AbIocView(id = R.id.IV_pickPic1,click = "click")
    ImageView IV_pickPic1;
    @AbIocView(id = R.id.IV_pickPic2,click = "click")
    ImageView IV_pickPic2;
    @AbIocView(id = R.id.IV_sendPic0,click = "click")
    ImageView IV_sendPic0;
    @AbIocView(id = R.id.IV_sendPic1,click = "click")
    ImageView IV_sendPic1;
    @AbIocView(id = R.id.IV_sendPic2,click = "click")
    ImageView IV_sendPic2;
    @AbIocView(id = R.id.IV_depPics0,click = "click")
    ImageView IV_depPics0;
    @AbIocView(id = R.id.IV_depPics1,click = "click")
    ImageView IV_depPics1;
    @AbIocView(id = R.id.IV_depPics2,click = "click")
    ImageView IV_depPics2;
    @AbIocView(id = R.id.tv_next, click = "click")
    TextView tv_next;

    private LogTaskBean.DataBean.LogsBean logsListBean;
    private String[] pickPics;
    private String[] sendPics;
    private String[] depPics;
    private AbImageLoader abImageLoader;
    private int state;
    private  Intent intent;
    private Intent imageintent;
    private String pickPic0path , pickPic1path, pickPic2path ;
    private String sendPic0path , sendPic1path, sendPic2path ;
    private String depPics0path , depPics1path, depPics2path ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.dispatchdetaillayout);
        intent = getIntent();
        logsListBean = (LogTaskBean.DataBean.LogsBean) intent.getSerializableExtra("itembean");
        state = intent.getIntExtra("state", -1);
//        abImageLoader = AbImageLoader.getInstance(this);
        imageintent = new Intent(this,DetailShow.class);
        if (state ==6 ){//如果状态为6时，改变按钮颜色及文字
            tv_next.setText("返回首页");
            tv_next.setBackground(getResources().getDrawable(R.drawable.buttonstyle));
        }

        setView();
    }


    private void setView() {
        TV_TaskNum.setText(logsListBean.getTaskNum());
        TV_Serial.setText(logsListBean.getSerial());
        TV_TransNum.setText(logsListBean.getTransNum());
        TV_Area.setText(logsListBean.getArea());
        TV_Street.setText(logsListBean.getStreet());
        TV_GoodsTitle.setText(logsListBean.getGoodsTitle());
        TV_Weight.setText(logsListBean.getWeight());
        TV_RecPerson.setText(logsListBean.getRecPerson());
        TV_RecTel.setText(logsListBean.getRecTel());
        TV_RecAddr.setText(logsListBean.getRecAddr());
        TV_AccTime.setText(logsListBean.getAccTime());
        TV_AccUser.setText(logsListBean.getAccUser());
        TV_PackTime.setText(logsListBean.getPackTime());
        TV_PackUser.setText(logsListBean.getPackUser());
        TV_DepTime.setText(logsListBean.getDepTime());
        TV_DepUser.setText(logsListBean.getDepUser());
        TV_SendTime.setText(logsListBean.getSendTime());
        TV_SendUser.setText(logsListBean.getSendUser());
        TV_DeliTime.setText(logsListBean.getDeliTime());
        TV_DeliUser.setText(logsListBean.getDeliUser());
        String pickPic = logsListBean.getPackPic();
        String depPic = logsListBean.getDepPic();
        String sendPic = logsListBean.getSendPic();

        pickPics = pickPic.split(",");//装车图片地址
        if (!pickPic.equals("")) {
            for (int i = 0; i < pickPics.length; i++) {
                if (pickPics.length == 1) {
                  pickPic0path = FinalURL.IMGURL + pickPics[0].substring(0, pickPics[0].length() );
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[0].substring(0, pickPics[0].length() - 1)).into(IV_pickPic0);
                   // abImageLoader.display(IV_pickPic0, FinalURL.IMGURL + pickPics[0].substring(0, pickPics[0].length() - 1));
                    IV_pickPic0.setVisibility(View.VISIBLE);
                    startAction(IV_pickPic0);
                    break;

                } else if (pickPics.length == 2) {
                    pickPic0path = FinalURL.IMGURL + pickPics[0];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[0]).into(IV_pickPic0);
                    //abImageLoader.display(IV_pickPic0, FinalURL.IMGURL + pickPics[0]);
                    pickPic1path = FinalURL.IMGURL + pickPics[1].substring(0, pickPics[1].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[1].substring(0, pickPics[1].length() - 1)).into(IV_pickPic1);
                    //abImageLoader.display(IV_pickPic1, FinalURL.IMGURL + pickPics[1].substring(0, pickPics[1].length() - 1));
                    IV_pickPic0.setVisibility(View.VISIBLE);
                    IV_pickPic1.setVisibility(View.VISIBLE);
                    startAction(IV_pickPic0);
                    startAction(IV_pickPic1);
                    break;

                } else if (pickPics.length == 3) {
                    pickPic0path = FinalURL.IMGURL + pickPics[0];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[0]).into(IV_pickPic0);
                    //abImageLoader.display(IV_pickPic0, FinalURL.IMGURL + pickPics[0]);
                    pickPic1path = FinalURL.IMGURL + pickPics[1];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[1]).into(IV_pickPic1);
                    //abImageLoader.display(IV_pickPic1, FinalURL.IMGURL + pickPics[1]);
                    pickPic2path =FinalURL.IMGURL + pickPics[2].substring(0, pickPics[2].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + pickPics[2].substring(0, pickPics[2].length() - 1)).into(IV_pickPic2);
                    //abImageLoader.display(IV_pickPic2, FinalURL.IMGURL + pickPics[2].substring(0, pickPics[2].length() - 1));

                    IV_pickPic0.setVisibility(View.VISIBLE);
                    IV_pickPic1.setVisibility(View.VISIBLE);
                    IV_pickPic2.setVisibility(View.VISIBLE);

                    startAction(IV_pickPic0);
                    startAction(IV_pickPic1);
                    startAction(IV_pickPic2);
                    break;

                }
            }
        }

        sendPics = sendPic.split(",,");//送货图片地址

        if (!sendPic.equals("")) {
            for (int i = 0; i < sendPics.length; i++) {
                if (sendPics.length == 1) {
                    sendPic0path = FinalURL.IMGURL + sendPics[0].substring(0, sendPics[0].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + sendPics[0].substring(0, sendPics[0].length() - 1)).into(IV_sendPic0);
                    //abImageLoader.display(IV_sendPic0, FinalURL.IMGURL + sendPics[0].substring(0, sendPics[0].length() - 1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    startAction(IV_sendPic0);
                    break;
                } else if (pickPics.length == 2) {
                    sendPic0path = FinalURL.IMGURL + sendPics[0];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + sendPics[0]).into(IV_sendPic0);
                    //abImageLoader.display(IV_sendPic0, FinalURL.IMGURL + sendPics[0]);
                    sendPic1path =  sendPics[1].substring(0, sendPics[1].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + sendPics[1].substring(0, sendPics[1].length() - 1)).into(IV_sendPic1);
                   // abImageLoader.display(IV_sendPic1, FinalURL.IMGURL + sendPics[1].substring(0, sendPics[1].length() - 1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    IV_sendPic1.setVisibility(View.VISIBLE);

                    startAction(IV_sendPic0);
                    startAction(IV_sendPic1);
                    break;
                } else if (pickPics.length == 3) {
                    sendPic0path = FinalURL.IMGURL + sendPics[0];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + sendPics[0]).into(IV_sendPic0);
                    //abImageLoader.display(IV_sendPic0, FinalURL.IMGURL + sendPics[0]);
                    sendPic1path = FinalURL.IMGURL + sendPics[1];
                    Glide.with(DispatchDetailActivity.this).load( FinalURL.IMGURL + sendPics[1]).into(IV_sendPic1);
                    //abImageLoader.display(IV_sendPic1, FinalURL.IMGURL + sendPics[1]);
                    sendPic2path = FinalURL.IMGURL + sendPics[2].substring(0, sendPics[2].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + sendPics[2].substring(0, sendPics[2].length() - 1)).into(IV_sendPic2);
                    //abImageLoader.display(IV_sendPic2, FinalURL.IMGURL + sendPics[2].substring(0, sendPics[2].length() - 1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    IV_sendPic1.setVisibility(View.VISIBLE);
                    IV_sendPic2.setVisibility(View.VISIBLE);
                    startAction(IV_sendPic0);
                    startAction(IV_sendPic1);
                    startAction(IV_sendPic2);
                    break;

                }
            }
        }

        depPics = depPic.split(",,");//到达图片地址
        if (!depPic.equals("")) {
            for (int i = 0; i < depPics.length; i++) {
                if (depPics.length == 1) {
                    depPics0path = depPics[0].substring(0, depPics[0].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + depPics[0].substring(0, depPics[0].length() - 1)).into(IV_depPics0);
                    //abImageLoader.display(IV_depPics0, FinalURL.IMGURL + depPics[0].substring(0, depPics[0].length() - 1));
                    IV_depPics0.setVisibility(View.VISIBLE);
                    startAction(IV_depPics0);
                    break;

                } else if (depPics.length == 2) {
                    depPics0path =  FinalURL.IMGURL + depPics[0];
                    Glide.with(DispatchDetailActivity.this).load( FinalURL.IMGURL + depPics[0]).into(IV_depPics0);
                    //abImageLoader.display(IV_depPics0, FinalURL.IMGURL + depPics[0]);
                    depPics1path = FinalURL.IMGURL + depPics[1].substring(0, depPics[1].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + depPics[1].substring(0, depPics[1].length() - 1)).into(IV_depPics1);
                    //abImageLoader.display(IV_depPics1, FinalURL.IMGURL + depPics[1].substring(0, depPics[1].length() - 1));
                    IV_depPics0.setVisibility(View.VISIBLE);
                    IV_depPics1.setVisibility(View.VISIBLE);

                    startAction(IV_depPics0);
                    startAction(IV_depPics1);
                    break;
                } else if (depPics.length == 3) {
                    depPics0path = FinalURL.IMGURL + depPics[0];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + depPics[0]).into(IV_depPics0);
                    //abImageLoader.display(IV_depPics0, FinalURL.IMGURL + depPics[0]);
                    depPics1path = FinalURL.IMGURL + depPics[1];
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + depPics[1]).into(IV_depPics1);
                    //abImageLoader.display(IV_depPics1, FinalURL.IMGURL + depPics[1]);
                    depPics2path = FinalURL.IMGURL + depPics[2].substring(0, depPics[2].length() - 1);
                    Glide.with(DispatchDetailActivity.this).load(FinalURL.IMGURL + depPics[2].substring(0, depPics[2].length() - 1)).into(IV_depPics2);
                    //abImageLoader.display(IV_depPics2, FinalURL.IMGURL + depPics[2].substring(0, depPics[2].length() - 1));
                    IV_depPics0.setVisibility(View.VISIBLE);
                    IV_depPics1.setVisibility(View.VISIBLE);
                    IV_depPics2.setVisibility(View.VISIBLE);

                    startAction(IV_depPics0);
                    startAction(IV_depPics1);
                    startAction(IV_depPics2);
                    break;
                }
            }

        }


    }

    private void startAction(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(3000);
        animation.setRepeatMode(Animation.REVERSE);
        view.setAnimation(animation);
        animation.startNow();
    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.IV_pickPic0:
                String path = pickPic0path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_pickPic1:
                 path = pickPic1path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_pickPic2:
                path = pickPic2path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_sendPic0:
                path = sendPic0path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_sendPic1:
                path = sendPic1path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_sendPic2:
                path = sendPic2path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_depPics0:
                path = depPics0path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_depPics1:
                path = depPics1path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.IV_depPics2:
                path = depPics2path;
                imageintent.putExtra("path",path);
                startActivity(imageintent);
                break;
            case R.id.tv_next:
                switch (state){
                    case -1:
                        AbToastUtil.showToast(DispatchDetailActivity.this,"-1111111111");
                        break;
                    case 3:
                        Intent intent = new Intent();
                        intent.putExtra("logsBean",logsListBean);
                        intent.setClass(DispatchDetailActivity.this,InstallCarActivity.class);
                        startActivity(intent);
                        AbToastUtil.showToast(DispatchDetailActivity.this,"33333");
                        break;
                    case 4:
                        intent = new Intent();
                        intent.putExtra("logsBean",logsListBean);
                        intent.setClass(DispatchDetailActivity.this,SendGoodsActivity.class);
                        startActivity(intent);
                        AbToastUtil.showToast(DispatchDetailActivity.this,"44444");
                        break;
                    case 5:
                        intent = new Intent();
                        intent.putExtra("logsBean",logsListBean);
                        intent.setClass(DispatchDetailActivity.this,ReachAcitvity.class);
                        startActivity(intent);
                        AbToastUtil.showToast(DispatchDetailActivity.this,"55555");
                        break;
                    case 6:
                        startActivity(new Intent(this,MainActivity.class));
                        AbToastUtil.showToast(DispatchDetailActivity.this,"66666");
                        break;
                }


                break;
            case R.id.IV_dispatchdetaillback:
                finish();
                break;
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


}
