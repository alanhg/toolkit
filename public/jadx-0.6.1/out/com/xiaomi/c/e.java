package com.xiaomi.c;

import com.xiaomi.smack.a;
import com.xiaomi.smack.l;
import com.xiaomi.smack.packet.d;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class e {
    private XmlPullParser a;

    e() {
        try {
            this.a = XmlPullParserFactory.newInstance().newPullParser();
            this.a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException e) {
        }
    }

    d a(byte[] bArr, a aVar) {
        this.a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.a.next();
        int eventType = this.a.getEventType();
        String name = this.a.getName();
        if (eventType == 2) {
            if (name.equals("message")) {
                return com.xiaomi.smack.d.a.a(this.a);
            }
            if (name.equals("iq")) {
                return com.xiaomi.smack.d.a.a(this.a, aVar);
            }
            if (name.equals("presence")) {
                return com.xiaomi.smack.d.a.b(this.a);
            }
            if (!this.a.getName().equals("stream")) {
                if (this.a.getName().equals("error")) {
                    throw new l(com.xiaomi.smack.d.a.c(this.a));
                } else if (this.a.getName().equals("warning")) {
                    this.a.next();
                    if (this.a.getName().equals("multi-login")) {
                    }
                } else if (this.a.getName().equals("bind")) {
                }
            }
        }
        return null;
    }
}
