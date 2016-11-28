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
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.JavaBean.DispatchBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;

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
    @AbIocView(id = R.id.IV_pickPic0)
    ImageView IV_pickPic0;
    @AbIocView(id = R.id.IV_pickPic1)
    ImageView IV_pickPic1;
    @AbIocView(id = R.id.IV_pickPic2)
    ImageView IV_pickPic2;
    @AbIocView(id= R.id.IV_sendPic0)
    ImageView IV_sendPic0;
    @AbIocView(id= R.id.IV_sendPic1)
    ImageView IV_sendPic1;
    @AbIocView(id= R.id.IV_sendPic2)
    ImageView IV_sendPic2;
    @AbIocView(id = R.id.IV_depPics0)
    ImageView IV_depPics0;
    @AbIocView(id = R.id.IV_depPics1)
    ImageView IV_depPics1;
    @AbIocView(id = R.id.IV_depPics2)
    ImageView IV_depPics2;



    private DispatchBean.DataBean.LogsListBean logsListBean;
    private String[] pickPics;
    private String[] sendPics;
    private String[] depPics;
    private AbImageLoader abImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.dispatchdetaillayout);
        Intent intent = getIntent();
        logsListBean = (DispatchBean.DataBean.LogsListBean) intent.getSerializableExtra("itembean");
        abImageLoader = AbImageLoader.getInstance(this);

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

        pickPics = pickPic.split(",,");//装车图片地址
//AbStrUtil.isEmpty(pickPic)
        if (!pickPic.equals("")){
            for (int i=0;i<pickPics.length;i++){
                if (pickPics.length==1){
                    abImageLoader.display(IV_pickPic0,FinalURL.IMGURL+pickPics[0].substring(0,pickPics[0].length()-1));
                    IV_pickPic0.setVisibility(View.VISIBLE);
                    startAction(IV_pickPic0);
                    break;

                }else if (pickPics.length==2){
                    abImageLoader.display(IV_pickPic0,FinalURL.IMGURL+pickPics[0]);
                    abImageLoader.display(IV_pickPic1,FinalURL.IMGURL+pickPics[1].substring(0,pickPics[1].length()-1));
                    IV_pickPic0.setVisibility(View.VISIBLE);
                    IV_pickPic1.setVisibility(View.VISIBLE);

                    startAction(IV_pickPic0);
                    startAction(IV_pickPic1);
                    break;

                }else if (pickPics.length==3){
                    abImageLoader.display(IV_pickPic0,FinalURL.IMGURL+pickPics[0]);
                    abImageLoader.display(IV_pickPic1,FinalURL.IMGURL+pickPics[1]);
                    abImageLoader.display(IV_pickPic2,FinalURL.IMGURL+pickPics[2].substring(0,pickPics[2].length()-1));

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

        if (!sendPic.equals("")){
            for (int i=0;i<sendPics.length;i++){
                if (sendPics.length==1){
                    abImageLoader.display(IV_sendPic0,FinalURL.IMGURL+sendPics[0].substring(0,sendPics[0].length()-1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    startAction(IV_sendPic0);
                    break;
                }else if (pickPics.length==2){
                    abImageLoader.display(IV_sendPic0,FinalURL.IMGURL+sendPics[0]);
                    abImageLoader.display(IV_sendPic1,FinalURL.IMGURL+sendPics[1].substring(0,sendPics[1].length()-1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    IV_sendPic1.setVisibility(View.VISIBLE);

                    startAction(IV_sendPic0);
                    startAction(IV_sendPic1);
                    break;
                }else if (pickPics.length==3){
                    abImageLoader.display(IV_sendPic0,FinalURL.IMGURL+sendPics[0]);
                    abImageLoader.display(IV_sendPic1,FinalURL.IMGURL+sendPics[1]);
                    abImageLoader.display(IV_sendPic2,FinalURL.IMGURL+sendPics[2].substring(0,sendPics[2].length()-1));
                    IV_sendPic0.setVisibility(View.VISIBLE);
                    IV_sendPic1.setVisibility(View.VISIBLE);
                    IV_sendPic2.setVisibility(View.VISIBLE);

                    startAction(IV_sendPic0);
                    startAction(IV_sendPic1);
                    startAction(IV_sendPic2);
                    break;

                }
            }
        }else {}

        depPics = depPic.split(",,");//到达图片地址
        if (!depPic.equals("")){
            for (int i=0;i<depPics.length;i++){
                if (depPics.length==1){
                    abImageLoader.display(IV_depPics0,FinalURL.IMGURL+depPics[0].substring(0,depPics[0].length()-1));
                    IV_depPics0.setVisibility(View.VISIBLE);

                    startAction(IV_depPics0);
                    break;

                }else if (depPics.length==2){
                    abImageLoader.display(IV_depPics0,FinalURL.IMGURL+depPics[0]);
                    abImageLoader.display(IV_depPics1,FinalURL.IMGURL+depPics[1].substring(0,depPics[1].length()-1));
                    IV_depPics0.setVisibility(View.VISIBLE);
                    IV_depPics1.setVisibility(View.VISIBLE);

                    startAction(IV_depPics0);
                    startAction(IV_depPics1);
                    break;
                }else if (depPics.length==3){
                    abImageLoader.display(IV_depPics0,FinalURL.IMGURL+depPics[0]);
                    abImageLoader.display(IV_depPics1,FinalURL.IMGURL+depPics[1]);
                    abImageLoader.display(IV_depPics2,FinalURL.IMGURL+depPics[2].substring(0,depPics[2].length()-1));
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
