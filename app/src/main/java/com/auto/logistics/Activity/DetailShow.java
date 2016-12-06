package com.auto.logistics.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ab.activity.AbActivity;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/12/5.
 */
public class DetailShow extends AbActivity {
    @AbIocView(id = R.id.IV_detailshow)
    ImageView IV_detailshow;
    @AbIocView(id = R.id.detailShow_goback,click = "click")
    ImageView detailShow_goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.detailshowlayout);
        Intent imageintent =getIntent();
        Glide.with(DetailShow.this).load(imageintent.getStringExtra("path")).into(IV_detailshow);

    }
    public void click(View view){
        switch (view.getId()){
            case R.id.detailShow_goback:
                finish();
                break;
        }
    }
}
