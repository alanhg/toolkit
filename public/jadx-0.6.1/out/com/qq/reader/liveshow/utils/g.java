package com.qq.reader.liveshow.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* compiled from: DeviceNetWorkUtil */
public class g {
    private static int a = 0;

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.isAvailable() : false;
    }

    public static int b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 0;
            }
            int i;
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                i = 1;
            } else {
                if (type == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (!((TelephonyManager) context.getSystemService("phone")).isNetworkRoaming()) {
                        switch (subtype) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                i = 2;
                                break;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                                i = 3;
                                break;
                            case 13:
                                i = 4;
                                break;
                        }
                    }
                    i = 2;
                }
                i = 0;
            }
            return i;
        } catch (Exception e) {
            SxbLog.c("NETWORK_STATUS", e.toString());
            return 0;
        } catch (Throwable th) {
            SxbLog.c("NETWORK_STATUS", th.toString());
            return 0;
        }
    }
}