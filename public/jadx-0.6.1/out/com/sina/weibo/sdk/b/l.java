package com.sina.weibo.sdk.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

/* compiled from: WbUtils */
public class l {
    public static Bundle a(String str) {
        try {
            return b(new URI(str).getQuery());
        } catch (Exception e) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                try {
                    bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static String a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.MANUFACTURER).append("-").append(Build.MODEL);
        stringBuilder.append("_");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("_");
        stringBuilder.append("weibosdk");
        stringBuilder.append("_");
        stringBuilder.append("0031405000");
        stringBuilder.append("_android");
        return stringBuilder.toString();
    }
}
