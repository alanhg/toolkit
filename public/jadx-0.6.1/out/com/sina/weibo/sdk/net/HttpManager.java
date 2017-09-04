package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.sina.weibo.sdk.b.d;
import com.sina.weibo.sdk.b.k;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.Set;
import qalsdk.o;

public class HttpManager {
    private static final String a = a();
    private static final String b = ("--" + a);
    private static final String c = ("--" + a + "--");

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    public static String a(Context context, String str, String str2, f fVar) throws WeiboException {
        String c = c(context, str, str2, fVar);
        d.a("HttpManager", "Response : " + c);
        return c;
    }

    private static String c(Context context, String str, String str2, f fVar) {
        try {
            HttpURLConnection a;
            a(context, fVar);
            if (Constants.HTTP_GET.equals(str2)) {
                a = b.a(str + "?" + fVar.c(), context);
                a.setRequestMethod(Constants.HTTP_GET);
                a.setInstanceFollowRedirects(true);
                a.connect();
            } else {
                a = b.a(str, context);
                a.setInstanceFollowRedirects(true);
                a.connect();
                if (fVar.d()) {
                    OutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                    a(dataOutputStream, fVar);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } else {
                    DataOutputStream dataOutputStream2 = new DataOutputStream(a.getOutputStream());
                    dataOutputStream2.write(fVar.c().getBytes("UTF-8"));
                    dataOutputStream2.flush();
                    dataOutputStream2.close();
                }
            }
            int responseCode = a.getResponseCode();
            if (responseCode == 200) {
                return a(a);
            }
            throw new WeiboHttpException(a(a), responseCode);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new WeiboException(e);
        }
    }

    private static String a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable e;
        InputStream inputStream2 = null;
        try {
            byte[] bArr = new byte[8192];
            inputStream = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (IOException e2) {
                        e = e2;
                        inputStream2 = inputStream;
                    } catch (Throwable th) {
                        e = th;
                    }
                }
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                return str;
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = null;
                inputStream2 = inputStream;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    e = th2;
                    inputStream = inputStream2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th3) {
                e = th3;
                byteArrayOutputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
        } catch (IOException e8) {
            e = e8;
            byteArrayOutputStream = null;
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            byteArrayOutputStream = null;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e;
        }
    }

    private static void a(Context context, f fVar) {
        String str = "";
        if (!TextUtils.isEmpty(fVar.a())) {
            str = k.b(context, fVar.a());
            if (!TextUtils.isEmpty(str)) {
                fVar.a("aid", str);
            }
        }
        String str2 = str;
        String b = b();
        fVar.a("oauth_timestamp", b);
        String str3 = "";
        Object a = fVar.a(Constants.PARAM_ACCESS_TOKEN);
        Object a2 = fVar.a("refresh_token");
        Object a3 = fVar.a("phone");
        str = (a == null || !(a instanceof String)) ? (a2 == null || !(a2 instanceof String)) ? (a3 == null || !(a3 instanceof String)) ? str3 : (String) a3 : (String) a2 : (String) a;
        fVar.a("oauth_sign", a(context, str2, str, fVar.a(), b));
    }

    public static String b(Context context, String str, String str2, f fVar) {
        HttpURLConnection a;
        if (str2.equals(Constants.HTTP_GET)) {
            str = str + "?" + fVar.c();
            a = b.a(str, context);
        } else {
            a = b.a(str, context);
        }
        String str3 = "";
        try {
            a.setInstanceFollowRedirects(false);
            a.connect();
            int responseCode = a.getResponseCode();
            if (responseCode == 302 || responseCode == 301) {
                return a.getHeaderField("Location");
            }
            if (responseCode != 200) {
                return str3;
            }
            return str;
        } catch (Exception e) {
            return str3;
        }
    }

    public static synchronized String a(Context context, String str, String str2, String str3) throws WeiboException {
        String path;
        synchronized (HttpManager.class) {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str3);
            if (file2.exists()) {
                path = file2.getPath();
            } else if (URLUtil.isValidUrl(str)) {
                File file3 = new File(str2, str3 + "_temp");
                HttpURLConnection a = b.a(str, context);
                a.setConnectTimeout(o.c);
                a.setReadTimeout(o.c);
                try {
                    a.setRequestMethod(Constants.HTTP_GET);
                } catch (Exception e) {
                }
                try {
                    long length;
                    if (file3.exists()) {
                        length = file3.length();
                    } else {
                        file3.createNewFile();
                        length = 0;
                    }
                    a.setRequestProperty("RANGE", "bytes=" + length);
                    int responseCode = a.getResponseCode();
                    if (responseCode == 206) {
                        length = 0;
                    } else if (responseCode == 200) {
                        length = (long) a.getContentLength();
                    } else {
                        throw new WeiboHttpException(a(a), responseCode);
                    }
                    InputStream inputStream = a.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                    randomAccessFile.seek(0);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, read);
                    }
                    randomAccessFile.close();
                    inputStream.close();
                    if (length == 0 || file3.length() < length) {
                        file3.delete();
                        path = "";
                    } else {
                        file3.renameTo(file2);
                        path = file2.getPath();
                    }
                } catch (Exception e2) {
                }
            } else {
                path = "";
            }
        }
        return path;
    }

    public static void a(OutputStream outputStream, f fVar) throws WeiboException {
        try {
            StringBuilder stringBuilder;
            Set<String> b = fVar.b();
            for (String str : b) {
                if (fVar.a(str) instanceof String) {
                    stringBuilder = new StringBuilder(100);
                    stringBuilder.setLength(0);
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
                    stringBuilder.append(fVar.a(str)).append("\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                }
            }
            for (String str2 : b) {
                Object a = fVar.a(str2);
                if (a instanceof Bitmap) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    Bitmap bitmap = (Bitmap) a;
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.write("\r\n".getBytes());
                } else if (a instanceof ByteArrayOutputStream) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) a;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write("\r\n".getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write(("\r\n" + c).getBytes());
        } catch (Throwable e) {
            throw new WeiboException(e);
        }
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis % 3 == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % 9);
            } else if (currentTimeMillis % 3 == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }

    private static String b() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(str3);
        }
        return calcOauthSignNative(context, stringBuilder.toString(), str4);
    }
}
