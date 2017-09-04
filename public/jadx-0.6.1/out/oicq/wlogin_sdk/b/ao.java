package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t144 */
public class ao extends b {
    public int a;

    public ao() {
        this.a = 0;
        this.h = ErrorCode.ERROR_SDKENGINE_CANLOADTBS;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        int i;
        int i2;
        if (bArr == null || bArr.length <= 0) {
            i = 0;
            i2 = 0;
        } else {
            i2 = 0 + bArr.length;
            i = 1;
        }
        if (bArr2 != null && bArr2.length > 0) {
            i2 += bArr2.length;
            i++;
        }
        if (bArr3 != null && bArr3.length > 0) {
            i2 += bArr3.length;
            i++;
        }
        if (bArr4 != null && bArr4.length > 0) {
            i2 += bArr4.length;
            i++;
        }
        Object obj = new byte[(i2 + 2)];
        util.int16_to_buf(obj, 0, i);
        i = 2;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(bArr, 0, obj, 2, bArr.length);
            i = bArr.length + 2;
        }
        if (bArr2 != null && bArr2.length > 0) {
            System.arraycopy(bArr2, 0, obj, i, bArr2.length);
            i += bArr2.length;
        }
        if (bArr3 != null && bArr3.length > 0) {
            System.arraycopy(bArr3, 0, obj, i, bArr3.length);
            i += bArr3.length;
        }
        if (bArr4 != null && bArr4.length > 0) {
            System.arraycopy(bArr4, 0, obj, i, bArr4.length);
            i += bArr4.length;
        }
        byte[] encrypt = cryptor.encrypt(obj, 0, obj.length, bArr5);
        this.a = encrypt.length;
        b(this.h);
        c(encrypt, encrypt.length);
        e();
        return b();
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8) {
        int i;
        int i2;
        if (bArr == null || bArr.length <= 0) {
            i = 0;
            i2 = 0;
        } else {
            i2 = 0 + bArr.length;
            i = 1;
        }
        if (bArr2 != null && bArr2.length > 0) {
            i2 += bArr2.length;
            i++;
        }
        if (bArr3 != null && bArr3.length > 0) {
            i2 += bArr3.length;
            i++;
        }
        if (bArr4 != null && bArr4.length > 0) {
            i2 += bArr4.length;
            i++;
        }
        if (bArr5 != null && bArr5.length > 0) {
            i2 += bArr5.length;
            i++;
        }
        if (bArr6 != null && bArr6.length > 0) {
            i2 += bArr6.length;
            i++;
        }
        if (bArr7 != null && bArr7.length > 0) {
            i2 += bArr7.length;
            i++;
        }
        Object obj = new byte[(i2 + 2)];
        util.int16_to_buf(obj, 0, i);
        i = 2;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(bArr, 0, obj, 2, bArr.length);
            i = bArr.length + 2;
        }
        if (bArr2 != null && bArr2.length > 0) {
            System.arraycopy(bArr2, 0, obj, i, bArr2.length);
            i += bArr2.length;
        }
        if (bArr3 != null && bArr3.length > 0) {
            System.arraycopy(bArr3, 0, obj, i, bArr3.length);
            i += bArr3.length;
        }
        if (bArr4 != null && bArr4.length > 0) {
            System.arraycopy(bArr4, 0, obj, i, bArr4.length);
            i += bArr4.length;
        }
        if (bArr5 != null && bArr5.length > 0) {
            System.arraycopy(bArr5, 0, obj, i, bArr5.length);
            i += bArr5.length;
        }
        if (bArr6 != null && bArr6.length > 0) {
            System.arraycopy(bArr6, 0, obj, i, bArr6.length);
            i += bArr6.length;
        }
        if (bArr7 != null && bArr7.length > 0) {
            System.arraycopy(bArr7, 0, obj, i, bArr7.length);
            i += bArr7.length;
        }
        byte[] encrypt = cryptor.encrypt(obj, 0, obj.length, bArr8);
        this.a = encrypt.length;
        b(this.h);
        c(encrypt, encrypt.length);
        e();
        return b();
    }
}
