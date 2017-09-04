package com.dynamicload.Lib;

import java.io.File;

public class DLConstants {
    public static final int ACTIVITY_TYPE_ACTIONBAR = 3;
    public static final int ACTIVITY_TYPE_FRAGMENT = 2;
    public static final int ACTIVITY_TYPE_NORMAL = 1;
    public static final int ACTIVITY_TYPE_UNKNOWN = -1;
    public static final String APK_CACHE_DIR = "apks";
    public static final String APK_LIB_DIR = "apk_libs";
    public static final String BRAND_SAMSUNG = "samsung";
    public static final String CPU_ARME64 = "arm64";
    public static final String CPU_ARMEABI = "armeabi";
    public static final String CPU_MIPS = "mips";
    public static final String CPU_X86 = "x86";
    public static final String DEPENDENCY_PACKAGE = "dependency_package";
    public static final String DEPENDENCY_PACKAGE_DIV = "|";
    public static final String DLINTENT_DATA_SCHEME = "testdl://";
    public static final String EXPORT_PACKAGE_VIEW = "com.tencent.news.dynamicload.exportView";
    public static final String EXTRA_ACTIVITY_INFO = "dl.extra.activity.info";
    public static final String EXTRA_CLASS = "dl.extra.class";
    public static final String EXTRA_CONNECTION = "dl.extra.conn";
    public static final String EXTRA_DEX_PATH = "dl.extra.dex.path";
    public static final String EXTRA_FROM_NOTIFY = "dl.notify";
    public static final String EXTRA_NOTIFICATION_TYPE = "dl.extra.notification.type";
    public static final String EXTRA_PACKAGE = "dl.extra.package";
    public static final String FROM = "dl.extra.from";
    public static final String FROM_AM = "dl.extra.from.am";
    public static final int FROM_EXTERNAL = 1;
    public static final int FROM_INTERNAL = 0;
    public static final String HOST_BROADCAST_EXTRA_PLUGIN_ID = "extra.pulgin.id";
    public static final String HOST_BROADCAST_EXTRA_PLUGIN_PKG = "extra.pulgin.pkg";
    public static final int HOST_BROADCAST_FORE_PROCESS_KILLED = 100;
    public static final int HOST_BROADCAST_PI_INSTALLED = 101;
    public static final int HOST_BROADCAST_PI_UNINSTALLED = 102;
    public static final int LOAD_ERR_APK_DATE = -106;
    public static final int LOAD_ERR_APK_SIZE = -108;
    public static final int LOAD_ERR_DIR_NOT_EXIST = -109;
    public static final int LOAD_ERR_FILE_NOT_EXIST = -104;
    public static final int LOAD_ERR_ILLEGAL_DEPENDENCY = -107;
    public static final int LOAD_ERR_INIT_FAIL = -110;
    public static final int LOAD_ERR_IO_FAIL = -103;
    public static final int LOAD_ERR_NAME_NOT_FOUND = -102;
    public static final int LOAD_ERR_SIGNATURES = -101;
    public static final int LOAD_ERR_SO_LOAD_FAIL = -105;
    public static final String NOTIFICATION_TYPE_ACTIVITY = "dl.notification.type.activity";
    public static final String NOTIFICATION_TYPE_BROADCAST = "dl.notification.type.broadcast";
    public static final String NOTIFICATION_TYPE_SERVICE = "dl.notification.type.service";
    public static final String PLUGIN = "plugin";
    public static final String PREFERENCE_NAME = "dynamic_load_configs";
    public static final String PROPERTIES = (PLUGIN + File.separator + "plugin.dat");
    public static final String PROXY_ACTIVITY_VIEW_ACTION = "com.ryg.dynamicload.proxy.activity.VIEW";
    public static final String PROXY_FRAGMENT_ACTIVITY_VIEW_ACTION = "com.ryg.dynamicload.proxy.fragmentactivity.VIEW";
    public static final int RESULT_CHECK_OK = 202;
    public static final int RESULT_IPC_OPERATOR_CALLBACKTYPE_ERROR = 504;
    public static final int RESULT_IPC_OPERATOR_NOSET = 501;
    public static final int RESULT_IPC_OPERATOR_REQUESTCODE_ERROR = 503;
    public static final int RESULT_IPC_OPERATOR_UNSUPPORT = 502;
    public static final int RESULT_NO_CLASS = 204;
    public static final int RESULT_NO_PKG = 203;
    public static final int RESULT_PKG_UNMOUNT = 206;
    public static final int RESULT_RECEIVER_AREADYREG = 401;
    public static final int RESULT_RECEIVER_EXCEPTION = 402;
    public static final int RESULT_RECEIVER_NEVERREG = 403;
    public static final int RESULT_SERVICE_AREADYBIND = 302;
    public static final int RESULT_SERVICE_LOADEXCEPTION = 303;
    public static final int RESULT_SERVICE_NEVERBIND = 304;
    public static final int RESULT_SERVICE_NOCONN = 301;
    public static final int RESULT_SUCCESS = 200;
    public static final int RESULT_TYPE_ERROR = 205;
    public static final int RESULT_UNKNOW = 201;
    public static final String TAG = "testdl";
}
