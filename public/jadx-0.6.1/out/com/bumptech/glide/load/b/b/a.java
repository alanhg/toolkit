package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.a.f;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.d;
import com.bumptech.glide.load.b.k;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.tencent.qalsdk.im_open.http;
import java.io.InputStream;

/* compiled from: HttpUrlGlideUrlLoader */
public class a implements l<d, InputStream> {
    private final k<d, d> a;

    /* compiled from: HttpUrlGlideUrlLoader */
    public static class a implements m<d, InputStream> {
        private final k<d, d> a = new k(http.Internal_Server_Error);

        public l<d, InputStream> a(Context context, c cVar) {
            return new a(this.a);
        }

        public void a() {
        }
    }

    public a() {
        this(null);
    }

    public a(k<d, d> kVar) {
        this.a = kVar;
    }

    public com.bumptech.glide.load.a.c<InputStream> a(d dVar, int i, int i2) {
        if (this.a != null) {
            d dVar2 = (d) this.a.a(dVar, 0, 0);
            if (dVar2 == null) {
                this.a.a(dVar, 0, 0, dVar);
            } else {
                dVar = dVar2;
            }
        }
        return new f(dVar);
    }
}
