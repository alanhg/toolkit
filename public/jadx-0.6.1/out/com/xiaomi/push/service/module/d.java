package com.xiaomi.push.service.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.qq.reader.common.download.task.f;
import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.l;
import com.xiaomi.push.service.k;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class d {
    private static d a = null;
    private Map<String, c> b = new HashMap();
    private List<b> c = new ArrayList();
    private Context d;
    private SharedPreferences e;
    private boolean f;

    private static class a implements Runnable {
        private String a = null;
        private String b = null;
        private String c;
        private boolean d;
        private Context e;
        private boolean f = false;

        public a(Context context, String str, String str2, String str3, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = context.getApplicationContext();
        }

        public void run() {
            Exception e;
            Throwable th;
            OutputStream outputStream = null;
            if (com.xiaomi.channel.commonutils.d.d.f(this.e)) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
                    httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        byte[] b = com.xiaomi.channel.commonutils.a.a.b(httpURLConnection.getInputStream());
                        if (!TextUtils.isEmpty(this.b)) {
                            if (!this.b.equalsIgnoreCase(com.xiaomi.channel.commonutils.g.d.a(b))) {
                                b = null;
                            }
                        }
                        if (b != null) {
                            c.b("download apk success.");
                            try {
                                File file = new File(this.c + f.DOWNLOAD_FILE_TMP);
                                file.delete();
                                OutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    fileOutputStream.write(b);
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    if (com.xiaomi.channel.commonutils.android.d.a(com.xiaomi.channel.commonutils.android.b.e(this.e, file.getPath()))) {
                                        c.b("verify signature success");
                                        file.renameTo(new File(this.c));
                                        this.f = true;
                                        if (this.d && !com.xiaomi.channel.commonutils.android.b.b(this.e)) {
                                            Process.killProcess(Process.myPid());
                                        }
                                    } else {
                                        c.d("verify signature failed");
                                        file.delete();
                                    }
                                    com.xiaomi.channel.commonutils.a.a.a(null);
                                } catch (Exception e2) {
                                    e = e2;
                                    outputStream = fileOutputStream;
                                    try {
                                        e.printStackTrace();
                                        com.xiaomi.channel.commonutils.a.a.a(outputStream);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        com.xiaomi.channel.commonutils.a.a.a(outputStream);
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    outputStream = fileOutputStream;
                                    com.xiaomi.channel.commonutils.a.a.a(outputStream);
                                    throw th;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                e.printStackTrace();
                                com.xiaomi.channel.commonutils.a.a.a(outputStream);
                            }
                        }
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public interface b {
        void a(f fVar);
    }

    private d(Context context) {
        this.d = context.getApplicationContext();
        this.e = this.d.getSharedPreferences("mipush_extra", 0);
    }

    private c a(a aVar, DexClassLoader dexClassLoader) {
        if (dexClassLoader == null) {
            return null;
        }
        return new c(aVar.e(), aVar.f(), dexClassLoader, aVar.b(), aVar.a());
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    private void a(e eVar, int i) {
        this.e.edit().putInt("plugin_version_" + eVar.b, i).commit();
    }

    private synchronized void a(f fVar) {
        for (b a : this.c) {
            a.a(fVar);
        }
    }

    private int b(e eVar) {
        return this.e.getInt("plugin_version_" + eVar.b, 0);
    }

    private List<f> b() {
        List<f> arrayList = new ArrayList();
        k a = k.a(this.d);
        f fVar = new f();
        fVar.a = e.MODULE_CDATA;
        fVar.b = a.a(com.xiaomi.xmpush.thrift.f.ad.a(), 0);
        fVar.c = a.a(com.xiaomi.xmpush.thrift.f.ae.a(), "");
        fVar.d = a.a(com.xiaomi.xmpush.thrift.f.af.a(), "");
        fVar.e = a.a(com.xiaomi.xmpush.thrift.f.ag.a(), false);
        arrayList.add(fVar);
        return arrayList;
    }

    public c a(e eVar) {
        l.a();
        if (eVar == null) {
            return null;
        }
        a();
        c.b("loadModule " + eVar.b);
        String str = eVar.b;
        if (this.b.containsKey(str)) {
            return (c) this.b.get(str);
        }
        a aVar = new a(this.d, str);
        DexClassLoader c = aVar.c();
        if (c == null) {
            return null;
        }
        c a = a(aVar, c);
        a.a(this.d);
        this.b.put(str, a);
        c.b("module load success.");
        return a;
    }

    public synchronized void a() {
        if (!this.f) {
            this.f = true;
            for (f fVar : b()) {
                if (b(fVar.a) < fVar.b && !TextUtils.isEmpty(fVar.c)) {
                    a aVar = new a(this.d, fVar.c, fVar.d, a.a(this.d, fVar.a.b), fVar.e);
                    aVar.run();
                    if (aVar.f) {
                        a(fVar.a, fVar.b);
                        a(fVar);
                    }
                }
            }
            this.f = false;
        }
    }
}
