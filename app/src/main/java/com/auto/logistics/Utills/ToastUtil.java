package com.auto.logistics.Utills;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/22.
 */

public class ToastUtil {
    public static void showToast(Context context ,String msg,int tag){
        if (tag==0){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }else if (tag==1){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        }
    }
}
