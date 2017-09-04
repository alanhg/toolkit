package com.tencent.open.utils;

/* compiled from: ProGuard */
public final class ZipShort implements Cloneable {
    private int a;

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipShort(byte[] bArr, int i) {
        this.a = (bArr[i + 1] << 8) & 65280;
        this.a += bArr[i] & 255;
    }

    public ZipShort(int i) {
        this.a = i;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ZipShort) && this.a == ((ZipShort) obj).getValue()) {
            return true;
        }
        return false;
    }

    public byte[] getBytes() {
        return new byte[]{(byte) (this.a & 255), (byte) ((this.a & 65280) >> 8)};
    }

    public int getValue() {
        return this.a;
    }

    public int hashCode() {
        return this.a;
    }
}
