package com.auto.logistics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.logistics.Activity.DetailShow;
import com.auto.logistics.JavaBean.LogTaskBean;
import com.auto.logistics.R;
import com.auto.logistics.Utills.FinalURL;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/8.
 */

public class DispatchDetailAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private ArrayList<LogTaskBean.DataBean.LogsBean> datalist;
    private String[] pickPics;
    private String[] sendPics;
    private String[] depPics;
    private ArrayList<ImageView> IV_pickPiclist = new ArrayList<ImageView>();
    private ArrayList<ImageView> IV_sendPiclist = new ArrayList<ImageView>();
    private ArrayList<ImageView> IV_depPicslist = new ArrayList<ImageView>();
    private Intent imageintent;

    public DispatchDetailAdapter(Context context, ArrayList<LogTaskBean.DataBean.LogsBean> datalist) {
        this.context = context;
        this.datalist = datalist;
        imageintent = new Intent(context, DetailShow.class);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VieweHolder vieweHolder = new VieweHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dispatchlistview_item, null);

            vieweHolder.TV_TaskNum = (TextView) convertView.findViewById(R.id.TV_TaskNum);
            vieweHolder.TV_Serial = (TextView) convertView.findViewById(R.id.TV_Serial);
            vieweHolder.TV_TransNum = (TextView) convertView.findViewById(R.id.TV_TransNum);
            vieweHolder.TV_Area = (TextView) convertView.findViewById(R.id.TV_Area);
            vieweHolder.TV_Street = (TextView) convertView.findViewById(R.id.TV_Street);
            vieweHolder.TV_GoodsTitle = (TextView) convertView.findViewById(R.id.TV_GoodsTitle);
            vieweHolder.TV_Weight = (TextView) convertView.findViewById(R.id.TV_Weight);
            vieweHolder.TV_RecPerson = (TextView) convertView.findViewById(R.id.TV_RecPerson);
            vieweHolder.TV_RecTel = (TextView) convertView.findViewById(R.id.TV_RecTel);
            vieweHolder.TV_AccTime = (TextView) convertView.findViewById(R.id.TV_AccTime);
            vieweHolder.TV_AccUser = (TextView) convertView.findViewById(R.id.TV_AccUser);
            vieweHolder.TV_PackTime = (TextView) convertView.findViewById(R.id.TV_PackTime);
            vieweHolder.TV_PackUser = (TextView) convertView.findViewById(R.id.TV_PackUser);
            vieweHolder.TV_DepTime = (TextView) convertView.findViewById(R.id.TV_DepTime);
            vieweHolder.TV_DepUser = (TextView) convertView.findViewById(R.id.TV_DepUser);
            vieweHolder.TV_SendTime = (TextView) convertView.findViewById(R.id.TV_SendTime);
            vieweHolder.TV_SendUser = (TextView) convertView.findViewById(R.id.TV_SendUser);
            vieweHolder.TV_DeliTime = (TextView) convertView.findViewById(R.id.TV_DeliTime);
            vieweHolder.TV_DeliUser = (TextView) convertView.findViewById(R.id.TV_DeliUser);
//            9张图片
            vieweHolder.IV_pickPic0 = (ImageView) convertView.findViewById(R.id.IV_pickPic0);
            vieweHolder.IV_pickPic1 = (ImageView) convertView.findViewById(R.id.IV_pickPic1);
            vieweHolder.IV_pickPic2 = (ImageView) convertView.findViewById(R.id.IV_pickPic2);
            vieweHolder.IV_sendPic0 = (ImageView) convertView.findViewById(R.id.IV_sendPic0);
            vieweHolder.IV_sendPic1 = (ImageView) convertView.findViewById(R.id.IV_sendPic1);
            vieweHolder.IV_sendPic2 = (ImageView) convertView.findViewById(R.id.IV_sendPic2);
            vieweHolder.IV_depPics0 = (ImageView) convertView.findViewById(R.id.IV_depPics0);
            vieweHolder.IV_depPics1 = (ImageView) convertView.findViewById(R.id.IV_depPics1);
            vieweHolder.IV_depPics2 = (ImageView) convertView.findViewById(R.id.IV_depPics2);


            vieweHolder.IV_pickPic0.setOnClickListener(this);
            vieweHolder.IV_pickPic1.setOnClickListener(this);
            vieweHolder.IV_pickPic2.setOnClickListener(this);
            vieweHolder.IV_sendPic0.setOnClickListener(this);
            vieweHolder.IV_sendPic1.setOnClickListener(this);
            vieweHolder.IV_sendPic2.setOnClickListener(this);
            vieweHolder.IV_depPics0.setOnClickListener(this);
            vieweHolder.IV_depPics1.setOnClickListener(this);
            vieweHolder.IV_depPics2.setOnClickListener(this);


            IV_pickPiclist.add(vieweHolder.IV_pickPic0);
            IV_pickPiclist.add(vieweHolder.IV_pickPic1);
            IV_pickPiclist.add(vieweHolder.IV_pickPic2);

            IV_sendPiclist.add(vieweHolder.IV_sendPic0);
            IV_sendPiclist.add(vieweHolder.IV_sendPic1);
            IV_sendPiclist.add(vieweHolder.IV_sendPic2);

            IV_depPicslist.add(vieweHolder.IV_depPics0);
            IV_depPicslist.add(vieweHolder.IV_depPics1);
            IV_depPicslist.add(vieweHolder.IV_depPics2);


            convertView.setTag(vieweHolder);
        } else {
            vieweHolder = (VieweHolder) convertView.getTag();
        }

        vieweHolder.TV_TaskNum.setText(datalist.get(position).getTaskNum());
        vieweHolder.TV_Serial.setText(datalist.get(position).getSerial());
        vieweHolder.TV_TransNum.setText(datalist.get(position).getTransNum());
        vieweHolder.TV_Area.setText(datalist.get(position).getArea());
        vieweHolder.TV_Street.setText(datalist.get(position).getStreet());
        vieweHolder.TV_GoodsTitle.setText(datalist.get(position).getGoodsTitle());
        vieweHolder.TV_Weight.setText(datalist.get(position).getWeight());
        vieweHolder.TV_RecPerson.setText(datalist.get(position).getRecPerson());
        vieweHolder.TV_RecTel.setText(datalist.get(position).getRecTel());
        // vieweHolder.TV_RecAddr.setText(datalist.get(position).getRecAddr());
        vieweHolder.TV_AccTime.setText(datalist.get(position).getAccTime());
        vieweHolder.TV_AccUser.setText(datalist.get(position).getAccUser());
        vieweHolder.TV_PackTime.setText(datalist.get(position).getPackTime());
        vieweHolder.TV_PackUser.setText(datalist.get(position).getPackUser());
        vieweHolder.TV_DepTime.setText(datalist.get(position).getDepTime());
        vieweHolder.TV_DepUser.setText(datalist.get(position).getDepUser());
        vieweHolder.TV_SendTime.setText(datalist.get(position).getSendTime());
        vieweHolder.TV_SendUser.setText(datalist.get(position).getSendUser());
        vieweHolder.TV_DeliTime.setText(datalist.get(position).getDeliTime());
        vieweHolder.TV_DeliUser.setText(datalist.get(position).getDeliUser());

        String pickPic = datalist.get(position).getPackPic();
        String depPic = datalist.get(position).getDepPic();
        String sendPic = datalist.get(position).getSendPic();
        pickPics = pickPic.split(",");//装车图片地址
        sendPics = sendPic.split(",");//送货图片地址
        depPics = depPic.split(",");//到达图片地址

        if (!pickPic.equals("")) {
            for (int i = 0; i < pickPics.length; i++) {
                Glide.with(context).load(FinalURL.IMGURL + pickPics[i]).into(IV_pickPiclist.get(i));
                IV_pickPiclist.get(i).setVisibility(View.VISIBLE);
                startAction(IV_pickPiclist.get(i));
            }

        }


        if (!sendPic.equals("")) {
            for (int i = 0; i < sendPics.length; i++) {
                Glide.with(context).load(FinalURL.IMGURL + sendPics[i]).into(IV_sendPiclist.get(i));
                IV_sendPiclist.get(i).setVisibility(View.VISIBLE);
                startAction(IV_sendPiclist.get(i));
            }
        }

        if (!depPic.equals("")) {
            for (int i = 0; i < depPics.length; i++) {
                Glide.with(context).load(FinalURL.IMGURL + depPics[i]).into(IV_depPicslist.get(i));
                IV_depPicslist.get(i).setVisibility(View.VISIBLE);
                startAction(IV_depPicslist.get(i));
            }

        }

        return convertView;
    }


    private void startAction(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(3000);
        animation.setRepeatMode(Animation.REVERSE);
        view.setAnimation(animation);
        animation.startNow();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IV_pickPic0:
                String path = FinalURL.IMGURL + pickPics[0];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_pickPic1:
                path = FinalURL.IMGURL + pickPics[1];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_pickPic2:
                path = FinalURL.IMGURL + pickPics[2];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_sendPic0:
                path = FinalURL.IMGURL + sendPics[0];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_sendPic1:
                path = FinalURL.IMGURL + sendPics[1];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_sendPic2:
                path = FinalURL.IMGURL + sendPics[2];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_depPics0:
                path = FinalURL.IMGURL + depPics[0];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_depPics1:
                path = FinalURL.IMGURL + depPics[1];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
            case R.id.IV_depPics2:
                path = FinalURL.IMGURL + depPics[2];
                imageintent.putExtra("path", path);
                context.startActivity(imageintent);
                break;
        }
    }


    private class VieweHolder {
        public TextView TV_TaskNum;
        public TextView TV_Serial;
        public TextView TV_TransNum;
        public TextView TV_Area;
        public TextView TV_Street;
        public TextView TV_GoodsTitle;
        public TextView TV_Weight;
        public TextView TV_RecPerson;
        public TextView TV_RecTel;
        public TextView TV_RecAddr;
        public TextView TV_AccTime;
        public TextView TV_AccUser;
        public TextView TV_PackTime;
        public TextView TV_PackUser;
        public TextView TV_DepTime;
        public TextView TV_DepUser;
        public TextView TV_SendTime;
        public TextView TV_SendUser;
        public TextView TV_DeliTime;
        public TextView TV_DeliUser;

        public ImageView IV_pickPic0;
        public ImageView IV_pickPic1;
        public ImageView IV_pickPic2;
        public ImageView IV_sendPic0;
        public ImageView IV_sendPic1;
        public ImageView IV_sendPic2;
        public ImageView IV_depPics0;
        public ImageView IV_depPics1;
        public ImageView IV_depPics2;

    }

}
