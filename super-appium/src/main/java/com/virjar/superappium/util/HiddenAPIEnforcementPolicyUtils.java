package com.virjar.superappium.util;

import android.util.Log;

import java.lang.reflect.Method;


//
// Created by Swift Gan on 2019/3/15.
//
//bypass hidden api on Android 9 - 10
public class HiddenAPIEnforcementPolicyUtils {

    private static Method forNameMethod;
    private static Method getMethodMethod;

    static Class vmRuntimeClass;
    static Method addWhiteListMethod;

    static Object vmRuntime;

    static {
        try {
            getMethodMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            forNameMethod = Class.class.getDeclaredMethod("forName", String.class);
            vmRuntimeClass = (Class) forNameMethod.invoke(null, "dalvik.system.VMRuntime");
            addWhiteListMethod = (Method) getMethodMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            Method getVMRuntimeMethod = (Method) getMethodMethod.invoke(vmRuntimeClass, "getRuntime", null);
            vmRuntime = getVMRuntimeMethod.invoke(null);
        } catch (Exception e) {
            Log.e("ReflectionUtils", "error get methods", e);
        }
    }

    public static boolean passApiCheck() {
        try {
            addReflectionWhiteList("Landroid/",
                    "Lcom/android/",
                    "Ljava/lang/",
                    "Ldalvik/system/",
                    "Llibcore/io/",
                    "Lsun/misc/"
            );
            return true;
        } catch (Throwable throwable) {
            Log.w(Constants.TAG, "pass Hidden API enforcement policy failed", throwable);
            return false;
        }
    }

    //methidSigs like Lcom/swift/sandhook/utils/ReflectionUtils;->vmRuntime:java/lang/Object; (from hidden policy list)
    private static void addReflectionWhiteList(String... memberSigs) throws Throwable {
        addWhiteListMethod.invoke(vmRuntime, new Object[]{memberSigs});
    }
}
