package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;

class ao extends h {
    final /* synthetic */ String a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ XMPushService c;

    ao(XMPushService xMPushService, int i, String str, byte[] bArr) {
        this.c = xMPushService;
        this.a = str;
        this.b = bArr;
        super(i);
    }

    public void a() {
        try {
            d.a(this.c, this.a, this.b);
        } catch (Exception e) {
            c.a((Throwable) e);
            this.c.a(10, e);
        }
    }

    public String b() {
        return "send mi push message";
    }
}