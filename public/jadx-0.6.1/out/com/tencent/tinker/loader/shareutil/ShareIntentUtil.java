package com.tencent.tinker.loader.shareutil;

import android.content.Intent;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ShareIntentUtil {
    public static final String INTENT_PATCH_COST_TIME = "intent_patch_cost_time";
    public static final String INTENT_PATCH_DEXES_PATH = "intent_patch_dexes_path";
    public static final String INTENT_PATCH_EXCEPTION = "intent_patch_exception";
    public static final String INTENT_PATCH_LIBS_PATH = "intent_patch_libs_path";
    public static final String INTENT_PATCH_MISMATCH_DEX_PATH = "intent_patch_mismatch_dex_path";
    public static final String INTENT_PATCH_MISMATCH_LIB_PATH = "intent_patch_mismatch_lib_path";
    public static final String INTENT_PATCH_MISSING_DEX_PATH = "intent_patch_missing_dex_path";
    public static final String INTENT_PATCH_MISSING_LIB_PATH = "intent_patch_missing_lib_path";
    public static final String INTENT_PATCH_NEW_VERSION = "intent_patch_new_version";
    public static final String INTENT_PATCH_OLD_VERSION = "intent_patch_old_version";
    public static final String INTENT_PATCH_PACKAGE_CONFIG = "intent_patch_package_config";
    public static final String INTENT_PATCH_PACKAGE_PATCH_CHECK = "intent_patch_package_patch_check";
    public static final String INTENT_PATCH_SYSTEM_OTA = "intent_patch_system_ota";
    public static final String INTENT_RETURN_CODE = "intent_return_code";
    private static final String TAG = "ShareIntentUtil";

    public static void setIntentReturnCode(Intent intent, int i) {
        intent.putExtra(INTENT_RETURN_CODE, i);
    }

    public static int getIntentReturnCode(Intent intent) {
        return getIntExtra(intent, INTENT_RETURN_CODE, -10000);
    }

    public static void setIntentPatchCostTime(Intent intent, long j) {
        intent.putExtra(INTENT_PATCH_COST_TIME, j);
    }

    public static long getIntentPatchCostTime(Intent intent) {
        return intent.getLongExtra(INTENT_PATCH_COST_TIME, 0);
    }

    public static Exception getIntentPatchException(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_EXCEPTION);
        if (serializableExtra != null) {
            return (Exception) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPatchDexPaths(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_DEXES_PATH);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPatchLibsPaths(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_LIBS_PATH);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static HashMap<String, String> getIntentPackageConfig(Intent intent) {
        Serializable serializableExtra = getSerializableExtra(intent, INTENT_PATCH_PACKAGE_CONFIG);
        if (serializableExtra != null) {
            return (HashMap) serializableExtra;
        }
        return null;
    }

    public static ArrayList<String> getStringArrayListExtra(Intent intent, String str) {
        ArrayList<String> arrayList = null;
        if (intent != null) {
            try {
                arrayList = intent.getStringArrayListExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getStringExtra exception:" + e.getMessage());
            }
        }
        return arrayList;
    }

    public static String getStringExtra(Intent intent, String str) {
        String str2 = null;
        if (intent != null) {
            try {
                str2 = intent.getStringExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getStringExtra exception:" + e.getMessage());
            }
        }
        return str2;
    }

    public static Serializable getSerializableExtra(Intent intent, String str) {
        Serializable serializable = null;
        if (intent != null) {
            try {
                serializable = intent.getSerializableExtra(str);
            } catch (Exception e) {
                Log.e(TAG, "getSerializableExtra exception:" + e.getMessage());
            }
        }
        return serializable;
    }

    public static int getIntExtra(Intent intent, String str, int i) {
        if (intent != null) {
            try {
                i = intent.getIntExtra(str, i);
            } catch (Exception e) {
                Log.e(TAG, "getIntExtra exception:" + e.getMessage());
            }
        }
        return i;
    }

    public static boolean getBooleanExtra(Intent intent, String str, boolean z) {
        if (intent != null) {
            try {
                z = intent.getBooleanExtra(str, z);
            } catch (Exception e) {
                Log.e(TAG, "getBooleanExtra exception:" + e.getMessage());
            }
        }
        return z;
    }

    public static long getLongExtra(Intent intent, String str, long j) {
        if (intent != null) {
            try {
                j = intent.getLongExtra(str, j);
            } catch (Exception e) {
                Log.e(TAG, "getIntExtra exception:" + e.getMessage());
            }
        }
        return j;
    }
}
