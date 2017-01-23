package com.auto.logistics.ContentObserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 短信验证码截取
 *
 * @author yangfan
 *
 */
public class SMSContentObserver extends ContentObserver
{

    private Context mContext; // 上下文
    private Handler mHandler; // 更新UI线程
    private String code; // 验证码
    private  Pattern pattern;
    private Matcher matcher;

    public SMSContentObserver(Context context, Handler handler)
    {
        super(handler);
        mContext = context;
        mHandler = handler;
    }

    /**
     * 回调函数, 当所监听的Uri发生改变时，就会回调此方法
     * 注意当收到短信的时候会回调两次
     * @param selfChange
     *此值意义不大 一般情况下该回调值false
     */
    @Override
    public void onChange(boolean selfChange, Uri uri)
    {

        Log.e("XXXXXXXXXXXXXXXX", uri.toString());

        // 第一次回调 不是我们想要的 直接返回
        if (uri.toString().equals("content://sms/raw"))
        {
            return;
        }

        // 第二次回调 查询收件箱里的内容
        Uri inboxUri = Uri.parse("content://sms/inbox");

        // 按时间顺序排序短信数据库
        Cursor c = mContext.getContentResolver().query(inboxUri, null, null,
                null, "date desc");
        if (c != null)
        {
            if (c.moveToFirst())
            {

                // 获取手机号
                String address = c.getString(c.getColumnIndex("address"));
                // 获取短信内容
                String body = c.getString(c.getColumnIndex("body"));

                /**
                 * 可以有2种方式获取短信内容中验证码：
                 * 第一种：通过判断手机号码，固定号码要加 +86 这3个字符，服务号码不固定，可以用正则表达式或者其他判定
                 * 第二种：通过短信内容获取，判定在短信内容中是否包含某几个字符
                 * */

//                if (!address.equals(address.startsWith("106")))
//                {
//                    return;
//                }


                if (!body.contains("德松考勤")){
                    return;
                }
                // 正则表达式截取短信中的6位验证码
                pattern = Pattern.compile("(\\d{6})");
                matcher = pattern.matcher(body);

                // 如果找到通过Handler发送给主线程 ，并将验证码填写到输入框内
                if (matcher.find())
                {
                    code = matcher.group(0);
                    mHandler.obtainMessage(1, code).sendToTarget();
                }
            }

        }
        c.close();

    }

}