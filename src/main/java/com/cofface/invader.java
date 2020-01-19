package com.cofface;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.io.File;
import org.json.JSONObject;

/* compiled from: hiddencore */
public class invader implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if ("android".equals(loadPackageParam.packageName)) {
            XposedHelpers.findAndHookMethod(File.class, "exists", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                    File file = (File) methodHookParam.thisObject;
                    if (new File("/sys/fs/selinux/enforce").equals(file)) {
                        methodHookParam.setResult(true);
                    } else if (new File("/system/bin/su").equals(file) || new File("/system/xbin/su").equals(file)) {
                        methodHookParam.setResult(false);
                    }
                }
            }});
        }
        XposedHelpers.findAndHookMethod(JSONObject.class, "getBoolean", new Object[]{String.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                String str = (String) methodHookParam.args[0];
                if ("ctsProfileMatch".equals(str) || "basicIntegrity".equals(str) || "isValidSignature".equals(str)) {
                    methodHookParam.setResult(true);
                }
            }
        }});
    }
}
