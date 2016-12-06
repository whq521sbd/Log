package com.auto.logistics.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ab.activity.AbActivity;
import com.ab.image.AbImageLoader;
import com.ab.view.ioc.AbIocView;
import com.auto.logistics.R;
import com.auto.logistics.Utills.SharedPreferencesSava;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/12/5.
 */
public class ImageShowActivity extends AbActivity {
    @AbIocView(id = R.id.IV_showImage)
    private ImageView IV_showImage;
    private AbImageLoader loader = AbImageLoader.getInstance(ImageShowActivity.this);
    @AbIocView(id = R.id.ImageShow_goback,click = "click")
    ImageView ImageShow_goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.imageshowlayout);
        Intent intent = getIntent();
        int state = intent.getIntExtra("state", -1);
        switch (state) {
            case 1:
                String img1path = SharedPreferencesSava.getInstance().getStringValue(ImageShowActivity.this, "img1");
                Glide.with(ImageShowActivity.this).load(img1path).into(IV_showImage);
                break;
            case 2:
                String img2path = SharedPreferencesSava.getInstance().getStringValue(ImageShowActivity.this, "img2");
                Glide.with(ImageShowActivity.this).load(img2path).into(IV_showImage);
                break;
            case 3:
                String img3path = SharedPreferencesSava.getInstance().getStringValue(ImageShowActivity.this, "img3");
                Glide.with(ImageShowActivity.this).load(img3path).into(IV_showImage);
                break;
        }

    }

    public void click(View view){
        switch (view.getId()){
            case R.id.ImageShow_goback:
                finish();
                break;
        }

    }

}
