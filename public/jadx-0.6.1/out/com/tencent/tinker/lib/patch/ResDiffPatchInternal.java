package com.tencent.tinker.lib.patch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;
import com.tencent.tinker.bsdiff.BSPatch;
import com.tencent.tinker.commons.resutil.ResUtil;
import com.tencent.tinker.commons.ziputil.TinkerZipEntry;
import com.tencent.tinker.commons.ziputil.TinkerZipFile;
import com.tencent.tinker.commons.ziputil.TinkerZipOutputStream;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo.LargeModeInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResDiffPatchInternal extends BasePatchInternal {
    protected static final String TAG = "Tinker.ResDiffPatchInternal";

    protected static boolean tryRecoverResourceFiles(Tinker tinker, ShareSecurityCheck shareSecurityCheck, Context context, String str, File file) {
        if (tinker.isEnabledForResource()) {
            String str2 = (String) shareSecurityCheck.getMetaContentMap().get(ShareConstants.RES_META_FILE);
            if (str2 == null || str2.length() == 0) {
                TinkerLog.w(TAG, "patch recover, resource is not contained", new Object[0]);
                return true;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
            TinkerLog.i(TAG, "recover resource result:%b, cost:%d", Boolean.valueOf(patchResourceExtractViaResourceDiff(context, str, str2, file)), Long.valueOf(elapsedRealtime));
            return patchResourceExtractViaResourceDiff(context, str, str2, file);
        }
        TinkerLog.w(TAG, "patch recover, resource is not enabled", new Object[0]);
        return true;
    }

    private static boolean patchResourceExtractViaResourceDiff(Context context, String str, String str2, File file) {
        if (extractResourceDiffInternals(context, str + "/" + "res" + "/", str2, file, 6)) {
            return true;
        }
        TinkerLog.w(TAG, "patch recover, extractDiffInternals fail", new Object[0]);
        return false;
    }

    private static boolean extractResourceDiffInternals(Context context, String str, String str2, File file, int i) {
        Throwable th;
        TinkerZipOutputStream tinkerZipOutputStream;
        TinkerZipFile tinkerZipFile;
        ShareResPatchInfo shareResPatchInfo = new ShareResPatchInfo();
        ShareResPatchInfo.parseAllResPatchInfo(str2, shareResPatchInfo);
        TinkerLog.i(TAG, "res dir: %s, meta: %s", str, shareResPatchInfo.toString());
        Tinker with = Tinker.with(context);
        if (SharePatchFileUtil.checkIfMd5Valid(shareResPatchInfo.resArscMd5)) {
            File file2 = new File(str);
            File file3 = new File(file2, ShareConstants.RES_NAME);
            if (!file3.exists()) {
                file3.getParentFile().mkdirs();
            } else if (SharePatchFileUtil.checkResourceArscMd5(file3, shareResPatchInfo.resArscMd5)) {
                TinkerLog.w(TAG, "resource file %s is already exist, and md5 match, just return true", file3.getPath());
                return true;
            } else {
                TinkerLog.w(TAG, "have a mismatch corrupted resource " + file3.getPath(), new Object[0]);
                file3.delete();
            }
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                if (applicationInfo == null) {
                    TinkerLog.w(TAG, "applicationInfo == null!!!!", new Object[0]);
                    return false;
                }
                String str3 = applicationInfo.sourceDir;
                if (!checkAndExtractResourceLargeFile(context, str3, file2, file, shareResPatchInfo, i)) {
                    return false;
                }
                TinkerZipFile tinkerZipFile2 = null;
                LargeModeInfo largeModeInfo;
                try {
                    TinkerZipOutputStream tinkerZipOutputStream2 = new TinkerZipOutputStream(new BufferedOutputStream(new FileOutputStream(file3)));
                    try {
                        TinkerZipFile tinkerZipFile3 = new TinkerZipFile(str3);
                        try {
                            TinkerZipEntry tinkerZipEntry;
                            tinkerZipFile2 = new TinkerZipFile(file);
                            Enumeration entries = tinkerZipFile3.entries();
                            int i2 = 0;
                            while (entries.hasMoreElements()) {
                                tinkerZipEntry = (TinkerZipEntry) entries.nextElement();
                                if (tinkerZipEntry == null) {
                                    throw new TinkerRuntimeException("zipEntry is null when get from oldApk");
                                }
                                try {
                                    String name = tinkerZipEntry.getName();
                                    if (!name.contains("../")) {
                                        int i3;
                                        if (!ShareResPatchInfo.checkFileInPattern(shareResPatchInfo.patterns, name) || shareResPatchInfo.deleteRes.contains(name) || shareResPatchInfo.modRes.contains(name) || shareResPatchInfo.largeModRes.contains(name) || name.equals(ShareConstants.RES_MANIFEST)) {
                                            i3 = i2;
                                        } else {
                                            ResUtil.extractTinkerEntry(tinkerZipFile3, tinkerZipEntry, tinkerZipOutputStream2);
                                            i3 = i2 + 1;
                                        }
                                        i2 = i3;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    tinkerZipOutputStream = tinkerZipOutputStream2;
                                    tinkerZipFile = tinkerZipFile2;
                                    tinkerZipFile2 = tinkerZipFile3;
                                }
                            }
                            tinkerZipEntry = tinkerZipFile3.getEntry(ShareConstants.RES_MANIFEST);
                            if (tinkerZipEntry == null) {
                                TinkerLog.w(TAG, "manifest patch entry is null. path:AndroidManifest.xml", new Object[0]);
                                with.getPatchReporter().onPatchTypeExtractFail(file, file3, ShareConstants.RES_MANIFEST, i);
                                if (tinkerZipOutputStream2 != null) {
                                    tinkerZipOutputStream2.close();
                                }
                                if (tinkerZipFile3 != null) {
                                    tinkerZipFile3.close();
                                }
                                if (tinkerZipFile2 != null) {
                                    tinkerZipFile2.close();
                                }
                                for (LargeModeInfo largeModeInfo2 : shareResPatchInfo.largeModMap.values()) {
                                    SharePatchFileUtil.safeDeleteFile(largeModeInfo2.file);
                                }
                                return false;
                            }
                            String str4;
                            TinkerZipEntry entry;
                            ResUtil.extractTinkerEntry(tinkerZipFile3, tinkerZipEntry, tinkerZipOutputStream2);
                            i2++;
                            Iterator it = shareResPatchInfo.largeModRes.iterator();
                            while (it.hasNext()) {
                                str4 = (String) it.next();
                                entry = tinkerZipFile3.getEntry(str4);
                                if (entry == null) {
                                    TinkerLog.w(TAG, "large patch entry is null. path:" + str4, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, str4, i);
                                    if (tinkerZipOutputStream2 != null) {
                                        tinkerZipOutputStream2.close();
                                    }
                                    if (tinkerZipFile3 != null) {
                                        tinkerZipFile3.close();
                                    }
                                    if (tinkerZipFile2 != null) {
                                        tinkerZipFile2.close();
                                    }
                                    for (LargeModeInfo largeModeInfo22 : shareResPatchInfo.largeModMap.values()) {
                                        SharePatchFileUtil.safeDeleteFile(largeModeInfo22.file);
                                    }
                                    return false;
                                }
                                largeModeInfo22 = (LargeModeInfo) shareResPatchInfo.largeModMap.get(str4);
                                ResUtil.extractLargeModifyFile(entry, largeModeInfo22.file, largeModeInfo22.crc, tinkerZipOutputStream2);
                                i2++;
                            }
                            it = shareResPatchInfo.addRes.iterator();
                            while (it.hasNext()) {
                                str4 = (String) it.next();
                                entry = tinkerZipFile2.getEntry(str4);
                                if (entry == null) {
                                    TinkerLog.w(TAG, "add patch entry is null. path:" + str4, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, str4, i);
                                    if (tinkerZipOutputStream2 != null) {
                                        tinkerZipOutputStream2.close();
                                    }
                                    if (tinkerZipFile3 != null) {
                                        tinkerZipFile3.close();
                                    }
                                    if (tinkerZipFile2 != null) {
                                        tinkerZipFile2.close();
                                    }
                                    for (LargeModeInfo largeModeInfo222 : shareResPatchInfo.largeModMap.values()) {
                                        SharePatchFileUtil.safeDeleteFile(largeModeInfo222.file);
                                    }
                                    return false;
                                }
                                ResUtil.extractTinkerEntry(tinkerZipFile2, entry, tinkerZipOutputStream2);
                                i2++;
                            }
                            it = shareResPatchInfo.modRes.iterator();
                            while (it.hasNext()) {
                                str4 = (String) it.next();
                                entry = tinkerZipFile2.getEntry(str4);
                                if (entry == null) {
                                    TinkerLog.w(TAG, "mod patch entry is null. path:" + str4, new Object[0]);
                                    with.getPatchReporter().onPatchTypeExtractFail(file, file3, str4, i);
                                    if (tinkerZipOutputStream2 != null) {
                                        tinkerZipOutputStream2.close();
                                    }
                                    if (tinkerZipFile3 != null) {
                                        tinkerZipFile3.close();
                                    }
                                    if (tinkerZipFile2 != null) {
                                        tinkerZipFile2.close();
                                    }
                                    for (LargeModeInfo largeModeInfo2222 : shareResPatchInfo.largeModMap.values()) {
                                        SharePatchFileUtil.safeDeleteFile(largeModeInfo2222.file);
                                    }
                                    return false;
                                }
                                ResUtil.extractTinkerEntry(tinkerZipFile2, entry, tinkerZipOutputStream2);
                                i2++;
                            }
                            if (tinkerZipOutputStream2 != null) {
                                tinkerZipOutputStream2.close();
                            }
                            if (tinkerZipFile3 != null) {
                                tinkerZipFile3.close();
                            }
                            if (tinkerZipFile2 != null) {
                                tinkerZipFile2.close();
                            }
                            for (LargeModeInfo largeModeInfo22222 : shareResPatchInfo.largeModMap.values()) {
                                SharePatchFileUtil.safeDeleteFile(largeModeInfo22222.file);
                            }
                            if (SharePatchFileUtil.checkResourceArscMd5(file3, shareResPatchInfo.resArscMd5)) {
                                TinkerLog.i(TAG, "final new resource file:%s, entry count:%d, size:%d", file3.getAbsolutePath(), Integer.valueOf(i2), Long.valueOf(file3.length()));
                                return true;
                            }
                            TinkerLog.i(TAG, "check final new resource file fail path:%s, entry count:%d, size:%d", file3.getAbsolutePath(), Integer.valueOf(i2), Long.valueOf(file3.length()));
                            SharePatchFileUtil.safeDeleteFile(file3);
                            with.getPatchReporter().onPatchTypeExtractFail(file, file3, ShareConstants.RES_NAME, i);
                            return false;
                        } catch (Throwable th22) {
                            th = th22;
                            tinkerZipFile2 = tinkerZipFile3;
                            tinkerZipFile = null;
                            tinkerZipOutputStream = tinkerZipOutputStream2;
                            if (tinkerZipOutputStream != null) {
                                tinkerZipOutputStream.close();
                            }
                            if (tinkerZipFile2 != null) {
                                tinkerZipFile2.close();
                            }
                            if (tinkerZipFile != null) {
                                tinkerZipFile.close();
                            }
                            for (LargeModeInfo largeModeInfo222222 : shareResPatchInfo.largeModMap.values()) {
                                SharePatchFileUtil.safeDeleteFile(largeModeInfo222222.file);
                            }
                            throw th;
                        }
                    } catch (Throwable th222) {
                        th = th222;
                        tinkerZipFile = null;
                        tinkerZipOutputStream = tinkerZipOutputStream2;
                        if (tinkerZipOutputStream != null) {
                            tinkerZipOutputStream.close();
                        }
                        if (tinkerZipFile2 != null) {
                            tinkerZipFile2.close();
                        }
                        if (tinkerZipFile != null) {
                            tinkerZipFile.close();
                        }
                        while (r4.hasNext()) {
                            SharePatchFileUtil.safeDeleteFile(largeModeInfo222222.file);
                        }
                        throw th;
                    }
                } catch (Throwable th2222) {
                    th = th2222;
                    tinkerZipFile = null;
                    tinkerZipOutputStream = null;
                    if (tinkerZipOutputStream != null) {
                        tinkerZipOutputStream.close();
                    }
                    if (tinkerZipFile2 != null) {
                        tinkerZipFile2.close();
                    }
                    if (tinkerZipFile != null) {
                        tinkerZipFile.close();
                    }
                    while (r4.hasNext()) {
                        SharePatchFileUtil.safeDeleteFile(largeModeInfo222222.file);
                    }
                    throw th;
                }
            } catch (Throwable th22222) {
                TinkerRuntimeException tinkerRuntimeException = new TinkerRuntimeException("patch " + ShareTinkerInternals.getTypeString(i) + " extract failed (" + th22222.getMessage() + ").", th22222);
            }
        } else {
            TinkerLog.w(TAG, "resource meta file md5 mismatch, type:%s, md5: %s", ShareTinkerInternals.getTypeString(i), shareResPatchInfo.resArscMd5);
            with.getPatchReporter().onPatchPackageCheckFail(file, BasePatchInternal.getMetaCorruptedCode(i));
            return false;
        }
    }

    private static boolean checkAndExtractResourceLargeFile(Context context, String str, File file, File file2, ShareResPatchInfo shareResPatchInfo, int i) {
        ZipFile zipFile;
        Closeable closeable;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        Tinker with = Tinker.with(context);
        ZipFile zipFile2 = null;
        ZipFile zipFile3 = null;
        ZipFile zipFile4;
        try {
            zipFile = new ZipFile(str);
            try {
                Closeable closeable2;
                ZipEntry entry = zipFile.getEntry(ShareConstants.RES_ARSC);
                File file3 = new File(file, ShareConstants.RES_ARSC);
                if (entry == null) {
                    TinkerLog.w(TAG, "resources apk entry is null. path:resources.arsc", new Object[0]);
                    with.getPatchReporter().onPatchTypeExtractFail(file2, file3, ShareConstants.RES_ARSC, i);
                    SharePatchFileUtil.closeZip(zipFile);
                    SharePatchFileUtil.closeZip(null);
                    return false;
                }
                if (!String.valueOf(entry.getCrc()).equals(shareResPatchInfo.arscBaseCrc)) {
                    TinkerLog.e(TAG, "resources.arsc's crc is not equal, expect crc: %s, got crc: %s", shareResPatchInfo.arscBaseCrc, String.valueOf(entry.getCrc()));
                    with.getPatchReporter().onPatchTypeExtractFail(file2, file3, ShareConstants.RES_ARSC, i);
                    SharePatchFileUtil.closeZip(zipFile);
                    SharePatchFileUtil.closeZip(null);
                    return false;
                } else if (shareResPatchInfo.largeModRes.isEmpty()) {
                    TinkerLog.i(TAG, "no large modify resources, just return", new Object[0]);
                    SharePatchFileUtil.closeZip(zipFile);
                    SharePatchFileUtil.closeZip(null);
                    return true;
                } else {
                    Iterator it = shareResPatchInfo.largeModRes.iterator();
                    zipFile2 = null;
                    while (it.hasNext()) {
                        try {
                            String str2 = (String) it.next();
                            long currentTimeMillis2 = System.currentTimeMillis();
                            LargeModeInfo largeModeInfo = (LargeModeInfo) shareResPatchInfo.largeModMap.get(str2);
                            if (largeModeInfo == null) {
                                TinkerLog.w(TAG, "resource not found largeModeInfo, type:%s, name: %s", ShareTinkerInternals.getTypeString(i), str2);
                                with.getPatchReporter().onPatchPackageCheckFail(file2, BasePatchInternal.getMetaCorruptedCode(i));
                                SharePatchFileUtil.closeZip(zipFile);
                                SharePatchFileUtil.closeZip(zipFile2);
                                return false;
                            }
                            largeModeInfo.file = new File(file, str2);
                            SharePatchFileUtil.ensureFileDirectory(largeModeInfo.file);
                            if (SharePatchFileUtil.checkIfMd5Valid(largeModeInfo.md5)) {
                                zipFile4 = new ZipFile(file2);
                                try {
                                    ZipEntry entry2 = zipFile4.getEntry(str2);
                                    if (entry2 == null) {
                                        TinkerLog.w(TAG, "large mod patch entry is null. path:" + str2, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file2, largeModeInfo.file, str2, i);
                                        SharePatchFileUtil.closeZip(zipFile);
                                        SharePatchFileUtil.closeZip(zipFile4);
                                        return false;
                                    }
                                    ZipEntry entry3 = zipFile.getEntry(str2);
                                    if (entry3 == null) {
                                        TinkerLog.w(TAG, "resources apk entry is null. path:" + str2, new Object[0]);
                                        with.getPatchReporter().onPatchTypeExtractFail(file2, largeModeInfo.file, str2, i);
                                        SharePatchFileUtil.closeZip(zipFile);
                                        SharePatchFileUtil.closeZip(zipFile4);
                                        return false;
                                    }
                                    Closeable closeable3 = null;
                                    closeable = null;
                                    try {
                                        closeable3 = zipFile.getInputStream(entry3);
                                        try {
                                            closeable = zipFile4.getInputStream(entry2);
                                            try {
                                                BSPatch.patchFast(closeable3, closeable, largeModeInfo.file);
                                                SharePatchFileUtil.closeQuietly(closeable3);
                                                SharePatchFileUtil.closeQuietly(closeable);
                                                if (SharePatchFileUtil.verifyFileMd5(largeModeInfo.file, largeModeInfo.md5)) {
                                                    TinkerLog.w(TAG, "success recover large modify file:%s, file size:%d, use time:%d", largeModeInfo.file.getPath(), Long.valueOf(largeModeInfo.file.length()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                                                    zipFile2 = zipFile4;
                                                } else {
                                                    TinkerLog.w(TAG, "Failed to recover large modify file:%s", largeModeInfo.file.getPath());
                                                    SharePatchFileUtil.safeDeleteFile(largeModeInfo.file);
                                                    with.getPatchReporter().onPatchTypeExtractFail(file2, largeModeInfo.file, str2, i);
                                                    SharePatchFileUtil.closeZip(zipFile);
                                                    SharePatchFileUtil.closeZip(zipFile4);
                                                    return false;
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                closeable2 = closeable;
                                                closeable = closeable3;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            closeable2 = closeable;
                                            closeable = closeable3;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        closeable2 = closeable;
                                        closeable = closeable3;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } else {
                                TinkerLog.w(TAG, "resource meta file md5 mismatch, type:%s, name: %s, md5: %s", ShareTinkerInternals.getTypeString(i), str2, largeModeInfo.md5);
                                with.getPatchReporter().onPatchPackageCheckFail(file2, BasePatchInternal.getMetaCorruptedCode(i));
                                SharePatchFileUtil.closeZip(zipFile);
                                SharePatchFileUtil.closeZip(zipFile2);
                                return false;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            zipFile4 = zipFile2;
                        }
                    }
                    TinkerLog.w(TAG, "success recover all large modify use time:%d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    SharePatchFileUtil.closeZip(zipFile);
                    SharePatchFileUtil.closeZip(zipFile2);
                    return true;
                }
                SharePatchFileUtil.closeQuietly(closeable);
                SharePatchFileUtil.closeQuietly(closeable2);
                throw th;
            } catch (Throwable th7) {
                th = th7;
                zipFile4 = null;
                SharePatchFileUtil.closeZip(zipFile);
                SharePatchFileUtil.closeZip(zipFile4);
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            zipFile4 = null;
            zipFile = null;
            SharePatchFileUtil.closeZip(zipFile);
            SharePatchFileUtil.closeZip(zipFile4);
            throw th;
        }
    }
}
