package com.tencent.android.tpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import com.tencent.android.tpush.b.d;

/* compiled from: ProGuard */
public class XGNotifaction {
    private int a = 0;
    private Notification b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private Context f = null;

    public XGNotifaction(Context context, int i, Notification notification, d dVar) {
        this.f = context.getApplicationContext();
        this.a = i;
        this.b = notification;
        this.c = dVar.e();
        this.d = dVar.f();
        this.e = dVar.g();
    }

    public void setNotifyId(int i) {
        this.a = i;
    }

    public String toString() {
        return "XGNotifaction [notifyId=" + this.a + ", title=" + this.c + ", content=" + this.d + ", customContent=" + this.e + "]";
    }

    public boolean doNotify() {
        if (!(this.b == null || this.f == null)) {
            NotificationManager notificationManager = (NotificationManager) this.f.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(this.a, this.b);
                return true;
            }
        }
        return false;
    }

    public int getNotifyId() {
        return this.a;
    }

    public Notification getNotifaction() {
        return this.b;
    }

    public String getTitle() {
        return this.c;
    }

    public String getContent() {
        return this.d;
    }

    public String getCustomContent() {
        return this.e;
    }
}
