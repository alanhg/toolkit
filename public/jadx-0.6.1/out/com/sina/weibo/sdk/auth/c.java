package com.sina.weibo.sdk.auth;

import android.text.TextUtils;

/* compiled from: WbAppInfo */
public class c {
    private String a = "com.sina.weibo";
    private String b = "com.sina.weibo.SSOActivity";
    private int c;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public boolean d() {
        if (TextUtils.isEmpty(this.a) || this.c <= 0) {
            return false;
        }
        return true;
    }
}