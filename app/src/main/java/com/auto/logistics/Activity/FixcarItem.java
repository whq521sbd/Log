package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.auto.logistics.JavaBean.CarSign;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/22.
 */
public class FixcarItem extends AbActivity {


    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tv_fixdate)
    TextView tvFixdate;
    @BindView(R.id.Area1)
    LinearLayout Area1;
    @BindView(R.id.tv_consoler)
    TextView tvConsoler;
    @BindView(R.id.Area2)
    LinearLayout Area2;
    @BindView(R.id.tv_marks)
    TextView tvMarks;
    @BindView(R.id.Area3)
    LinearLayout Area3;
    @BindView(R.id.tv_fixcarstate)
    TextView tvFixcarstate;
    @BindView(R.id.Area4)
    LinearLayout Area4;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.Area5)
    LinearLayout Area5;
    @BindView(R.id.IV_fixcarimg0)
    ImageView IVFixcarimg0;
    @BindView(R.id.IV_fixcarimg1)
    ImageView IVFixcarimg1;
    @BindView(R.id.IV_fixcarimg2)
    ImageView IVFixcarimg2;
    @BindView(R.id.ImagArea)
    LinearLayout ImagArea;
    @BindView(R.id.tv_return)
    TextView tvreturn;
    @BindView(R.id.IV_Rback)
    ImageView IVRback;
    @BindView(R.id.fixcaritem_title)
    FrameLayout fixcaritemTitle;
    private CarSign.DataBean.SignCarsBean signCarsBean;
    private ArrayList<ImageView> imagelist = new ArrayList<>();
    private String[] ImgURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.fixcaritem);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        signCarsBean = (CarSign.DataBean.SignCarsBean) intent.getSerializableExtra("itemBean");
        Log.d("FixcarItem", "onCreate: " + signCarsBean);
        setView();
    }

    private void setView() {

        imagelist.add(IVFixcarimg0);
        imagelist.add(IVFixcarimg1);
        imagelist.add(IVFixcarimg2);

        tvFixdate.setText(signCarsBean.getSignTime());
        tvConsoler.setText(signCarsBean.getFullName());
        tvMarks.setText(signCarsBean.getSignText());
        tvFixcarstate.setText(signCarsBean.getState());
        String ImgURL = signCarsBean.getSignImg();

        if (!ImgURL.toString().equals("")){

        ImgURLs = ImgURL.split(",");
            for (int i = 0; i < ImgURLs.length; i++) {
                Glide.with(FixcarItem.this).load(FinalURL.IMGURL + ImgURLs[i]).into(imagelist.get(i));
                imagelist.get(i).setVisibility(View.VISIBLE);
            }

        }


    }


    @OnClick(R.id.IV_fixcarimg0)
    public void click() {
        Intent intent = new Intent(this, DetailShow.class);
        intent.putExtra("path", FinalURL.IMGURL + ImgURLs[0]);
        startActivity(intent);
    }

    @OnClick(R.id.IV_fixcarimg1)
    public void click1() {
        Intent intent = new Intent(this, DetailShow.class);
        intent.putExtra("path", FinalURL.IMGURL + ImgURLs[1]);
        startActivity(intent);
    }


    @OnClick(R.id.IV_fixcarimg2)
    public void click2() {
        Intent intent = new Intent(this, DetailShow.class);
        intent.putExtra("path", FinalURL.IMGURL + ImgURLs[2]);
        startActivity(intent);
    }

    @OnClick(R.id.IV_Rback)
    public void setIVRback(){
        finish();
    }

    @OnClick(R.id.tv_return)
    public void tvreturn(){
        finish();
    }

}
