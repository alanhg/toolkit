package com.xiaomi.xmpush.thrift;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.meta_data.b;
import org.apache.thrift.meta_data.c;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.h;
import org.apache.thrift.protocol.j;

public class ac implements Serializable, Cloneable, org.apache.thrift.a<ac, a> {
    public static final Map<a, b> i;
    private static final j j = new j("XmPushActionContainer");
    private static final org.apache.thrift.protocol.b k = new org.apache.thrift.protocol.b("action", (byte) 8, (short) 1);
    private static final org.apache.thrift.protocol.b l = new org.apache.thrift.protocol.b("encryptAction", (byte) 2, (short) 2);
    private static final org.apache.thrift.protocol.b m = new org.apache.thrift.protocol.b("isRequest", (byte) 2, (short) 3);
    private static final org.apache.thrift.protocol.b n = new org.apache.thrift.protocol.b("pushAction", JceStruct.STRUCT_END, (short) 4);
    private static final org.apache.thrift.protocol.b o = new org.apache.thrift.protocol.b("appid", JceStruct.STRUCT_END, (short) 5);
    private static final org.apache.thrift.protocol.b p = new org.apache.thrift.protocol.b(Constants.FLAG_PACKAGE_NAME, JceStruct.STRUCT_END, (short) 6);
    private static final org.apache.thrift.protocol.b q = new org.apache.thrift.protocol.b("target", JceStruct.ZERO_TAG, (short) 7);
    private static final org.apache.thrift.protocol.b r = new org.apache.thrift.protocol.b("metaInfo", JceStruct.ZERO_TAG, (short) 8);
    public a a;
    public boolean b = true;
    public boolean c = true;
    public ByteBuffer d;
    public String e;
    public String f;
    public v g;
    public s h;
    private BitSet s = new BitSet(2);

    public enum a {
        ACTION((short) 1, "action"),
        ENCRYPT_ACTION((short) 2, "encryptAction"),
        IS_REQUEST((short) 3, "isRequest"),
        PUSH_ACTION((short) 4, "pushAction"),
        APPID((short) 5, "appid"),
        PACKAGE_NAME((short) 6, Constants.FLAG_PACKAGE_NAME),
        TARGET((short) 7, "target"),
        META_INFO((short) 8, "metaInfo");
        
        private static final Map<String, a> i = null;
        private final short j;
        private final String k;

        static {
            i = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                i.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.j = s;
            this.k = str;
        }

        public String a() {
            return this.k;
        }
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.ACTION, new b("action", (byte) 1, new org.apache.thrift.meta_data.a((byte) 16, a.class)));
        enumMap.put(a.ENCRYPT_ACTION, new b("encryptAction", (byte) 1, new c((byte) 2)));
        enumMap.put(a.IS_REQUEST, new b("isRequest", (byte) 1, new c((byte) 2)));
        enumMap.put(a.PUSH_ACTION, new b("pushAction", (byte) 1, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.APPID, new b("appid", (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.PACKAGE_NAME, new b(Constants.FLAG_PACKAGE_NAME, (byte) 2, new c(JceStruct.STRUCT_END)));
        enumMap.put(a.TARGET, new b("target", (byte) 1, new g(JceStruct.ZERO_TAG, v.class)));
        enumMap.put(a.META_INFO, new b("metaInfo", (byte) 2, new g(JceStruct.ZERO_TAG, s.class)));
        i = Collections.unmodifiableMap(enumMap);
        b.a(ac.class, i);
    }

    public a a() {
        return this.a;
    }

    public ac a(a aVar) {
        this.a = aVar;
        return this;
    }

    public ac a(s sVar) {
        this.h = sVar;
        return this;
    }

    public ac a(v vVar) {
        this.g = vVar;
        return this;
    }

    public ac a(String str) {
        this.e = str;
        return this;
    }

    public ac a(ByteBuffer byteBuffer) {
        this.d = byteBuffer;
        return this;
    }

    public ac a(boolean z) {
        this.b = z;
        b(true);
        return this;
    }

    public void a(e eVar) {
        eVar.g();
        while (true) {
            org.apache.thrift.protocol.b i = eVar.i();
            if (i.b == (byte) 0) {
                eVar.h();
                if (!d()) {
                    throw new f("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (e()) {
                    o();
                    return;
                } else {
                    throw new f("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (i.c) {
                case (short) 1:
                    if (i.b != (byte) 8) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.a = a.a(eVar.t());
                        break;
                    }
                case (short) 2:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.b = eVar.q();
                    b(true);
                    break;
                case (short) 3:
                    if (i.b != (byte) 2) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.c = eVar.q();
                    d(true);
                    break;
                case (short) 4:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.d = eVar.x();
                        break;
                    }
                case (short) 5:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.e = eVar.w();
                        break;
                    }
                case (short) 6:
                    if (i.b != JceStruct.STRUCT_END) {
                        h.a(eVar, i.b);
                        break;
                    } else {
                        this.f = eVar.w();
                        break;
                    }
                case (short) 7:
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.g = new v();
                    this.g.a(eVar);
                    break;
                case (short) 8:
                    if (i.b != JceStruct.ZERO_TAG) {
                        h.a(eVar, i.b);
                        break;
                    }
                    this.h = new s();
                    this.h.a(eVar);
                    break;
                default:
                    h.a(eVar, i.b);
                    break;
            }
            eVar.j();
        }
    }

    public boolean a(ac acVar) {
        if (acVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = acVar.b();
        if (((b || b2) && (!b || !b2 || !this.a.equals(acVar.a))) || this.b != acVar.b || this.c != acVar.c) {
            return false;
        }
        b = g();
        b2 = acVar.g();
        if ((b || b2) && (!b || !b2 || !this.d.equals(acVar.d))) {
            return false;
        }
        b = i();
        b2 = acVar.i();
        if ((b || b2) && (!b || !b2 || !this.e.equals(acVar.e))) {
            return false;
        }
        b = k();
        b2 = acVar.k();
        if ((b || b2) && (!b || !b2 || !this.f.equals(acVar.f))) {
            return false;
        }
        b = l();
        b2 = acVar.l();
        if ((b || b2) && (!b || !b2 || !this.g.a(acVar.g))) {
            return false;
        }
        b = n();
        b2 = acVar.n();
        return !(b || b2) || (b && b2 && this.h.a(acVar.h));
    }

    public int b(ac acVar) {
        if (!getClass().equals(acVar.getClass())) {
            return getClass().getName().compareTo(acVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(acVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, acVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(acVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, acVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(acVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, acVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(acVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.d, acVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(acVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.e, acVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(acVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.a(this.f, acVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(acVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.g, acVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(acVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.b.a(this.h, acVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public ac b(String str) {
        this.f = str;
        return this;
    }

    public void b(e eVar) {
        o();
        eVar.a(j);
        if (this.a != null) {
            eVar.a(k);
            eVar.a(this.a.a());
            eVar.b();
        }
        eVar.a(l);
        eVar.a(this.b);
        eVar.b();
        eVar.a(m);
        eVar.a(this.c);
        eVar.b();
        if (this.d != null) {
            eVar.a(n);
            eVar.a(this.d);
            eVar.b();
        }
        if (this.e != null && i()) {
            eVar.a(o);
            eVar.a(this.e);
            eVar.b();
        }
        if (this.f != null && k()) {
            eVar.a(p);
            eVar.a(this.f);
            eVar.b();
        }
        if (this.g != null) {
            eVar.a(q);
            this.g.b(eVar);
            eVar.b();
        }
        if (this.h != null && n()) {
            eVar.a(r);
            this.h.b(eVar);
            eVar.b();
        }
        eVar.c();
        eVar.a();
    }

    public void b(boolean z) {
        this.s.set(0, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public ac c(boolean z) {
        this.c = z;
        d(true);
        return this;
    }

    public boolean c() {
        return this.b;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((ac) obj);
    }

    public void d(boolean z) {
        this.s.set(1, z);
    }

    public boolean d() {
        return this.s.get(0);
    }

    public boolean e() {
        return this.s.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof ac)) ? a((ac) obj) : false;
    }

    public byte[] f() {
        a(org.apache.thrift.b.c(this.d));
        return this.d.array();
    }

    public boolean g() {
        return this.d != null;
    }

    public String h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.e != null;
    }

    public String j() {
        return this.f;
    }

    public boolean k() {
        return this.f != null;
    }

    public boolean l() {
        return this.g != null;
    }

    public s m() {
        return this.h;
    }

    public boolean n() {
        return this.h != null;
    }

    public void o() {
        if (this.a == null) {
            throw new f("Required field 'action' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new f("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new f("Required field 'target' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionContainer(");
        stringBuilder.append("action:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("encryptAction:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("isRequest:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("pushAction:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            org.apache.thrift.b.a(this.d, stringBuilder);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("appid:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("target:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("metaInfo:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
