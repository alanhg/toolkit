package com.bumptech.glide.load.a;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: ByteArrayFetcher */
public class b implements c<InputStream> {
    private final byte[] a;
    private final String b;

    public /* synthetic */ Object b(Priority priority) throws Exception {
        return a(priority);
    }

    public b(byte[] bArr, String str) {
        this.a = bArr;
        this.b = str;
    }

    public InputStream a(Priority priority) {
        return new ByteArrayInputStream(this.a);
    }

    public void a() {
    }

    public String b() {
        return this.b;
    }

    public void c() {
    }
}
