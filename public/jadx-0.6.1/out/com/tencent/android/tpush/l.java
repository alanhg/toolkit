package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;

/* compiled from: ProGuard */
class l implements OnCancelListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    l(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.putExtra("action", 6);
        XGPushActivity.access$000(this.b, this.a);
        this.b.finish();
    }
}