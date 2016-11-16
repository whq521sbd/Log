package com.auto.logistics.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ab.activity.AbActivity;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MissPwdAcitvity extends AbActivity {

    @AbIocView(id =  R.id.tv_Rback,click = "click")
    private ImageView tv_Rback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.misspwdlayout);

    }


    public void click(View view){
        switch (view.getId()){
            case R.id.tv_Rback:
                finish();
                break;
        }
    }
}
