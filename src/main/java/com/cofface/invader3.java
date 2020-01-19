package com.cofface;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import defpackage.zza;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class invader3 implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    public static final int BACK = 4;
    private static final String FAKE_APPLICATION = "FAKE.JUNK.APPLICATION";
    private static final String FAKE_COMMAND = "FAKEJUNKCOMMAND";
    private static final String FAKE_FILE = "FAKEJUNKFILE";
    private static final String FAKE_PACKAGE = "FAKE.JUNK.PACKAGE";
    public static final int LONG_PRESS_TIMEOUT = 1100;
    public static final int METHOD_LONG_PRESS = 0;
    public static final int METHOD_TEST = 2;
    public static final int METHOD_THREE_KEYS = 5;
    public static final int METHOD_TIMETEST = 3;
    public static final int METHOD_TOUCH = 4;
    public static final int METHOD_TWO_KEYS = 1;
    public static final int TYPE_APP = 1;
    public static final int TYPE_SYSTEM = 0;
    private static final String VIBRATOR_SERVICE = "com.android.server.VibratorService";
    public static final int VOL_DOWN = 25;
    public static final int VOL_UP = 24;
    /* access modifiers changed from: private */
    public static boolean[] activeKeyPressed = new boolean[3];
    /* access modifiers changed from: private */
    public static int[] activeKeys = new int[3];
    /* access modifiers changed from: private */
    public static List<String> alwaysOnPackages = null;
    /* access modifiers changed from: private */
    public static String applicationLabel = null;
    /* access modifiers changed from: private */
    public static Activity currentActivity = null;
    /* access modifiers changed from: private */
    public static int currentMethode = -1;
    public static final boolean debug = false;
    /* access modifiers changed from: private */
    public static boolean flagKeepScreenOn;
    /* access modifiers changed from: private */
    public static boolean isTouch;
    /* access modifiers changed from: private */
    public static long lastDown;
    /* access modifiers changed from: private */
    public static long[] lastKeyDown = new long[3];
    /* access modifiers changed from: private */
    public static long lastUp;
    private static long lastUpdate;
    private static Context mContext;
    private static float mVibStrength;
    /* access modifiers changed from: private */
    public static String packageName;
    private static XSharedPreferences prefs;
    /* access modifiers changed from: private */
    public static boolean systemwideScreenOn;
    private Set<String> appSet;
    /* access modifiers changed from: private */
    public Set<String> commandSet;
    /* access modifiers changed from: private */
    public boolean debugPref;
    /* access modifiers changed from: private */
    public boolean isRootCloakLoadingPref = false;
    /* access modifiers changed from: private */
    public Set<String> keywordSet;
    /* access modifiers changed from: private */
    public Set<String> libnameSet;
    /* access modifiers changed from: private */
    public String mSdcard;

    /* access modifiers changed from: private */
    public Boolean anyWordEndingWithKeyword(String str, String[] strArr) {
        for (String endsWith : strArr) {
            if (endsWith.endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public String[] buildGrepArraySingle(String[] strArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add("sh");
            arrayList.add("-c");
        }
        for (String append : strArr) {
            sb.append(" ");
            sb.append(append);
        }
        for (String append2 : this.keywordSet) {
            sb.append(" | grep -v ");
            sb.append(append2);
        }
        arrayList.add(sb.toString());
        return (String[]) arrayList.toArray(new String[0]);
    }

    private void f(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("java.lang.Class", loadPackageParam.classLoader, "forName", new Object[]{String.class, Boolean.TYPE, ClassLoader.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str == null) {
                    return;
                }
                if (str.equals("de.robv.android.xposed.XposedBridge") || str.equals("de.robv.android.xposed.XC_MethodReplacement")) {
                    methodHookParam.setThrowable(new ClassNotFoundException());
                }
            }
        }});
        XposedHelpers.findAndHookMethod("java.lang.Class", loadPackageParam.classLoader, "forName", new Object[]{String.class, Boolean.TYPE, ClassLoader.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str == null) {
                    return;
                }
                if (str.equals("de.robv.android.xposed.XposedBridge") || str.equals("de.robv.android.xposed.XC_MethodReplacement")) {
                    methodHookParam.setThrowable(new ClassNotFoundException());
                }
            }
        }});
        XposedHelpers.findAndHookMethod(StringWriter.class, "toString", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (((String) methodHookParam.getResult()).toLowerCase().contains("de.robv.android.xposed")) {
                    methodHookParam.setResult("");
                }
            }
        }});
    }

    private void fa(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledApplications", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                List list = (List) methodHookParam.getResult();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((ApplicationInfo) it.next()).packageName != null) {
                        it.remove();
                    }
                }
                methodHookParam.setResult(list);
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledPackages", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                List list = (List) methodHookParam.getResult();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((PackageInfo) it.next()).packageName != null) {
                        it.remove();
                    }
                }
                methodHookParam.setResult(list);
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getPackageInfo", new Object[]{String.class, Integer.TYPE, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                    methodHookParam.args[0] = "FAKE.JUNK.PACKAGE";
                }
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getApplicationInfo", new Object[]{String.class, Integer.TYPE, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                    methodHookParam.args[0] = "FAKE.JUNK.APPLICATION";
                }
            }
        }});
    }

    private void gps(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "getAccuracy", new Object[]{new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                invader3.super.beforeHookedMethod(methodHookParam);
                methodHookParam.setResult(Float.valueOf(0.01f));
            }
        }});
    }

    private void hml(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPackageParam.classLoader, "getString", new Object[]{ContentResolver.class, String.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (methodHookParam.args[1].equals("mock_location")) {
                    methodHookParam.setResult("0");
                }
            }
        }});
        if (Build.VERSION.SDK_INT >= 17) {
            XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPackageParam.classLoader, "getStringForUser", new Object[]{ContentResolver.class, String.class, Integer.TYPE, new XC_MethodHook() {
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    if (methodHookParam.args[1].equals("mock_location")) {
                        methodHookParam.setResult("0");
                    }
                }
            }});
        }
        if (Build.VERSION.SDK_INT >= 18) {
            XposedHelpers.findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "isFromMockProvider", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    methodHookParam.setResult(false);
                }
            }});
        }
    }

    private void initActivityManager(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.app.ActivityManager", loadPackageParam.classLoader, "getRunningServices", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked getRunningServices");
                }
                List list = (List) methodHookParam.getResult();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String str = ((ActivityManager.RunningServiceInfo) it.next()).process;
                    if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                        it.remove();
                        if (invader3.this.debugPref) {
                            XposedBridge.log("Found and hid service: " + str);
                        }
                    }
                }
                methodHookParam.setResult(list);
            }
        }});
    }

    private void initFile(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(File.class, new Class[]{String.class}), new XC_MethodHook(10000) {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (methodHookParam.args[0] != null && invader3.this.debugPref) {
                    XposedBridge.log("File: Found a File constructor: " + ((String) methodHookParam.args[0]));
                }
                if (!invader3.this.isRootCloakLoadingPref) {
                    if (((String) methodHookParam.args[0]).endsWith("su")) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor ending with su");
                        }
                        methodHookParam.args[0] = "/system/xbin/FAKEJUNKFILE";
                    } else if (((String) methodHookParam.args[0]).endsWith("busybox")) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor ending with busybox");
                        }
                        methodHookParam.args[0] = "/system/xbin/FAKEJUNKFILE";
                    } else if (invader3.this.stringContainsFromSet((String) methodHookParam.args[0], invader3.this.keywordSet)) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor with word super, noshufou, or chainfire");
                        }
                        methodHookParam.args[0] = "/system/app/FAKEJUNKFILE.apk";
                    }
                }
            }
        });
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(File.class, new Class[]{String.class, String.class}), new XC_MethodHook(10000) {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (!(methodHookParam.args[0] == null || methodHookParam.args[1] == null || !invader3.this.debugPref)) {
                    XposedBridge.log("File: Found a File constructor: " + ((String) methodHookParam.args[0]) + ", with: " + ((String) methodHookParam.args[1]));
                }
                if (!invader3.this.isRootCloakLoadingPref) {
                    if (((String) methodHookParam.args[1]).equalsIgnoreCase("su")) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor with filename su");
                        }
                        methodHookParam.args[1] = "FAKEJUNKFILE";
                    } else if (((String) methodHookParam.args[1]).contains("busybox")) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor ending with busybox");
                        }
                        methodHookParam.args[1] = "FAKEJUNKFILE";
                    } else if (invader3.this.stringContainsFromSet((String) methodHookParam.args[1], invader3.this.keywordSet)) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("File: Found a File constructor with word super, noshufou, or chainfire");
                        }
                        methodHookParam.args[1] = "FAKEJUNKFILE.apk";
                    }
                }
            }
        });
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(File.class, new Class[]{URI.class}), new XC_MethodHook(10000) {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (methodHookParam.args[0] != null && invader3.this.debugPref) {
                    XposedBridge.log("File: Found a URI File constructor: " + ((URI) methodHookParam.args[0]).toString());
                }
            }
        });
    }

    private void initOther(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.os.Debug", loadPackageParam.classLoader, "isDebuggerConnected", new Object[]{XC_MethodReplacement.returnConstant(false)});
        if (!Build.TAGS.equals("release-keys")) {
            if (this.debugPref) {
                XposedBridge.log("Original build tags: " + Build.TAGS);
            }
            XposedHelpers.setStaticObjectField(Build.class, "TAGS", "release-keys");
            if (this.debugPref) {
                XposedBridge.log("New build tags: " + Build.TAGS);
            }
        } else if (this.debugPref) {
            XposedBridge.log("No need to change build tags: " + Build.TAGS);
        }
        XposedHelpers.findAndHookMethod("android.os.SystemProperties", loadPackageParam.classLoader, "get", new Object[]{String.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (((String) methodHookParam.args[0]).equals("ro.build.selinux")) {
                    methodHookParam.setResult("1");
                    if (invader3.this.debugPref) {
                        XposedBridge.log("SELinux is enforced.");
                    }
                }
            }
        }});
        XposedHelpers.findAndHookMethod("java.lang.Class", loadPackageParam.classLoader, "forName", new Object[]{String.class, Boolean.TYPE, ClassLoader.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str == null) {
                    return;
                }
                if (str.equals("de.robv.android.xposed.XposedBridge") || str.equals("de.robv.android.xposed.XC_MethodReplacement")) {
                    methodHookParam.setThrowable(new ClassNotFoundException());
                    if (invader3.this.debugPref) {
                        XposedBridge.log("Found and hid Xposed class name: " + str);
                    }
                }
            }
        }});
    }

    private void initPackageManager(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledApplications", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked getInstalledApplications");
                }
                List list = (List) methodHookParam.getResult();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String str = ((ApplicationInfo) it.next()).packageName;
                    if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                        it.remove();
                        if (invader3.this.debugPref) {
                            XposedBridge.log("Found and hid package: " + str);
                        }
                    }
                }
                methodHookParam.setResult(list);
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledPackages", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked getInstalledPackages");
                }
                List list = (List) methodHookParam.getResult();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String str = ((PackageInfo) it.next()).packageName;
                    if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                        it.remove();
                        if (invader3.this.debugPref) {
                            XposedBridge.log("Found and hid package: " + str);
                        }
                    }
                }
                methodHookParam.setResult(list);
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getPackageInfo", new Object[]{String.class, Integer.TYPE, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked getPackageInfo");
                }
                String str = (String) methodHookParam.args[0];
                if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                    methodHookParam.args[0] = "FAKE.JUNK.PACKAGE";
                    if (invader3.this.debugPref) {
                        XposedBridge.log("Found and hid package: " + str);
                    }
                }
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getApplicationInfo", new Object[]{String.class, Integer.TYPE, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked getApplicationInfo : " + str);
                }
                if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.keywordSet)) {
                    methodHookParam.args[0] = "FAKE.JUNK.APPLICATION";
                    if (invader3.this.debugPref) {
                        XposedBridge.log("Found and hid application: " + str);
                    }
                }
            }
        }});
    }

    private void initProcessBuilder(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(ProcessBuilder.class, new Class[]{String[].class}), new XC_MethodHook(10000) {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                XposedBridge.log("Hooked ProcessBuilder");
                if (methodHookParam.args[0] != null) {
                    String[] strArr = (String[]) methodHookParam.args[0];
                    if (invader3.this.debugPref) {
                        String str = "ProcessBuilder Command:";
                        for (String str2 : strArr) {
                            str = str + " " + str2;
                        }
                        XposedBridge.log(str);
                    }
                    if (invader3.this.stringEndsWithFromSet(strArr[0], invader3.this.commandSet)) {
                        strArr[0] = "FAKEJUNKCOMMAND";
                        methodHookParam.args[0] = strArr;
                    }
                    if (invader3.this.debugPref) {
                        String str3 = "New ProcessBuilder Command:";
                        for (String str4 : (String[]) methodHookParam.args[0]) {
                            str3 = str3 + " " + str4;
                        }
                        XposedBridge.log(str3);
                    }
                }
            }
        });
    }

    private void initRuntime(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("java.lang.Runtime", loadPackageParam.classLoader, "exec", new Object[]{String[].class, String[].class, File.class, new XC_MethodHook() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked Runtime.exec");
                }
                String[] strArr = (String[]) methodHookParam.args[0];
                if (strArr != null && strArr.length >= 1) {
                    String str = strArr[0];
                    if (invader3.this.debugPref) {
                        String str2 = "Exec Command:";
                        for (String str3 : strArr) {
                            str2 = str2 + " " + str3;
                        }
                        XposedBridge.log(str2);
                    }
                    if (invader3.this.stringEndsWithFromSet(str, invader3.this.commandSet)) {
                        if (invader3.this.debugPref) {
                            XposedBridge.log("Found blacklisted command at the end of the string: " + str);
                        }
                        if (str.equals("su") || str.endsWith("/su")) {
                            methodHookParam.setThrowable(new IOException());
                        } else if (!invader3.this.commandSet.contains("pm") || (!str.equals("pm") && !str.endsWith("/pm"))) {
                            if (invader3.this.commandSet.contains("ps") && (str.equals("ps") || str.endsWith("/ps"))) {
                                methodHookParam.args[0] = invader3.this.buildGrepArraySingle(strArr, true);
                            } else if (invader3.this.commandSet.contains("which") && (str.equals("which") || str.endsWith("/which"))) {
                                methodHookParam.setThrowable(new IOException());
                            } else if (invader3.this.commandSet.contains("busybox") && invader3.this.anyWordEndingWithKeyword("busybox", strArr).booleanValue()) {
                                methodHookParam.setThrowable(new IOException());
                            } else if (!invader3.this.commandSet.contains("sh") || (!str.equals("sh") && !str.endsWith("/sh"))) {
                                methodHookParam.setThrowable(new IOException());
                            } else {
                                methodHookParam.setThrowable(new IOException());
                            }
                        } else if (strArr.length >= 3 && strArr[1].equalsIgnoreCase("list") && strArr[2].equalsIgnoreCase("packages")) {
                            methodHookParam.args[0] = invader3.this.buildGrepArraySingle(strArr, true);
                        } else if (strArr.length >= 3 && ((strArr[1].equalsIgnoreCase("dump") || strArr[1].equalsIgnoreCase("path")) && invader3.this.stringContainsFromSet(strArr[2], invader3.this.keywordSet))) {
                            methodHookParam.args[0] = new String[]{strArr[0], strArr[1], "FAKE.JUNK.PACKAGE"};
                        }
                        if (invader3.this.debugPref && methodHookParam.getThrowable() == null) {
                            String str4 = "New Exec Command:";
                            for (String str5 : (String[]) methodHookParam.args[0]) {
                                str4 = str4 + " " + str5;
                            }
                            XposedBridge.log(str4);
                        }
                    }
                } else if (invader3.this.debugPref) {
                    XposedBridge.log("Null or empty array on exec");
                }
            }
        }});
        XposedHelpers.findAndHookMethod("java.lang.Runtime", loadPackageParam.classLoader, "loadLibrary", new Object[]{String.class, ClassLoader.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (invader3.this.debugPref) {
                    XposedBridge.log("Hooked loadLibrary");
                }
                String str = (String) methodHookParam.args[0];
                if (str != null && invader3.this.stringContainsFromSet(str, invader3.this.libnameSet)) {
                    methodHookParam.setResult((Object) null);
                    if (invader3.this.debugPref) {
                        XposedBridge.log("Loading of library " + str + " disabled.");
                    }
                }
            }
        }});
    }

    private void initSettingsGlobal(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod(Settings.Global.class, "getInt", new Object[]{ContentResolver.class, String.class, Integer.TYPE, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[1];
                if (str != null && "adb_enabled".equals(str)) {
                    methodHookParam.setResult(0);
                    if (invader3.this.debugPref) {
                        XposedBridge.log("Hooked ADB debugging info, adb status is off");
                    }
                }
            }
        }});
    }

    private void l() {
        ClassLoader classLoader = null;
        XposedHelpers.findAndHookMethod("java.security.MessageDigest", classLoader, "isEqual", new Object[]{byte[].class, byte[].class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
        XposedHelpers.findAndHookMethod("java.security.Signature", classLoader, "verify", new Object[]{byte[].class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
        XposedHelpers.findAndHookMethod("java.security.Signature", classLoader, "verify", new Object[]{byte[].class, Integer.TYPE, Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
    }

    private static Set<String> loadSetFromPrefs(zza.e eVar) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
        HashSet hashSet = new HashSet();
        try {
            XSharedPreferences xSharedPreferences = new XSharedPreferences("com.cofface", eVar.b());
            xSharedPreferences.makeWorldReadable();
            boolean z = xSharedPreferences.getBoolean("com.coffaceIS_FIRST_RUN", true);
            Set stringSet = xSharedPreferences.getStringSet(eVar.c(), (Set) null);
            if (stringSet != null) {
                hashSet.addAll(stringSet);
            } else if (z) {
                hashSet.addAll(eVar.a());
            }
            return hashSet;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    private void natv(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isDetectedDevKeys", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isDetectedTestKeys", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isNotFoundReleaseKeys", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundDangerousProps", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isPermissiveSelinux", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isSuExists", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isAccessedSuperuserApk", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundSuBinary", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundBusyboxBinary", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundXposed", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundResetprop", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundWrongPathPermission", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.kozhevin.rootchecks.util.MeatGrinder", loadPackageParam.classLoader, "isFoundHooks", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
    }

    private void next(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Class<?> cls;
        XposedBridge.log("XposedHider - Protected - " + loadPackageParam.packageName);
        AnonymousClass44 r0 = new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str.matches("de\\.robv\\.android\\.xposed\\.Xposed+.+")) {
                    methodHookParam.setThrowable(new ClassNotFoundException(str));
                }
            }
        };
        XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", new Object[]{String.class, Boolean.TYPE, r0});
        XposedHelpers.findAndHookMethod(Class.class, "forName", new Object[]{String.class, Boolean.TYPE, ClassLoader.class, r0});
        XposedHelpers.findAndHookConstructor(File.class, new Object[]{String.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String str = (String) methodHookParam.args[0];
                if (str.matches("/proc/[0-9]+/maps") || (str.toLowerCase().contains("xposed") && !str.startsWith(invader3.this.mSdcard) && !str.contains("fkzhang"))) {
                    methodHookParam.args[0] = "/system/build.prop";
                }
            }
        }});
        AnonymousClass47 r02 = new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) methodHookParam.getResult();
                ArrayList arrayList = new ArrayList();
                for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                    if (!stackTraceElement.getClassName().toLowerCase().contains("xposed")) {
                        arrayList.add(stackTraceElement);
                    }
                }
                methodHookParam.setResult(arrayList.toArray(new StackTraceElement[0]));
            }
        };
        XposedHelpers.findAndHookMethod(Throwable.class, "getStackTrace", new Object[]{r02});
        XposedHelpers.findAndHookMethod(Thread.class, "getStackTrace", new Object[]{r02});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledPackages", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                List list = (List) methodHookParam.getResult();
                ArrayList arrayList = new ArrayList();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    PackageInfo packageInfo = (PackageInfo) list.get(i);
                    if (!packageInfo.packageName.toLowerCase().contains("xposed")) {
                        arrayList.add(packageInfo);
                    }
                }
                methodHookParam.setResult(arrayList);
            }
        }});
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledApplications", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                List list = (List) methodHookParam.getResult();
                ArrayList arrayList = new ArrayList();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ApplicationInfo applicationInfo = (ApplicationInfo) list.get(i);
                    if (!((applicationInfo.metaData != null && applicationInfo.metaData.getBoolean("xposedmodule")) || (applicationInfo.packageName != null && applicationInfo.packageName.toLowerCase().contains("xposed")) || ((applicationInfo.className != null && applicationInfo.className.toLowerCase().contains("xposed")) || (applicationInfo.processName != null && applicationInfo.processName.toLowerCase().contains("xposed"))))) {
                        arrayList.add(applicationInfo);
                    }
                }
                methodHookParam.setResult(arrayList);
            }
        }});
        XposedHelpers.findAndHookMethod(Modifier.class, "isNative", new Object[]{Integer.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod(System.class, "getProperty", new Object[]{String.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if ("vxp".equals(methodHookParam.args[0])) {
                    methodHookParam.setResult((Object) null);
                }
            }
        }});
        XposedHelpers.findAndHookMethod(File.class, "list", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                String[] strArr = (String[]) methodHookParam.getResult();
                if (strArr != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : strArr) {
                        if (!str.toLowerCase().contains("xposed") && !str.equals("su")) {
                            arrayList.add(str);
                        }
                    }
                    methodHookParam.setResult(arrayList.toArray(new String[0]));
                }
            }
        }});
        try {
            cls = Runtime.getRuntime().exec("echo").getClass();
        } catch (IOException unused) {
            XposedBridge.log("[W/XposedHider] Cannot hook Process#getInputStream");
            cls = null;
        }
        if (cls != null) {
            XposedHelpers.findAndHookMethod(cls, "getInputStream", new Object[]{new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    methodHookParam.setResult((InputStream) methodHookParam.getResult());
                }
            }});
            XposedBridge.hookAllMethods(System.class, "getenv", new XC_MethodHook() {
                private String a(String str) {
                    List asList = Arrays.asList(str.split(":"));
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < asList.size(); i++) {
                        if (!((String) asList.get(i)).toLowerCase().contains("xposed")) {
                            arrayList.add(asList.get(i));
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        sb.append(arrayList);
                        if (i2 != arrayList.size() - 1) {
                            sb.append(":");
                        }
                    }
                    return sb.toString();
                }

                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    if (methodHookParam.args.length == 0) {
                        methodHookParam.setResult(a((String) ((Map) methodHookParam.getResult()).get("CLASSPATH")));
                    } else if ("CLASSPATH".equals(methodHookParam.args[0])) {
                        methodHookParam.setResult(a((String) methodHookParam.getResult()));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void readPrefs() {
        prefs = new XSharedPreferences("com.cofface.invader", "gps");
        prefs.reload();
        LinkedList linkedList = new LinkedList();
        if (prefs.getBoolean("goj", true)) {
            linkedList.add("com.gojek.driver.bike");
        }
        if (prefs.getBoolean("goc", true)) {
            linkedList.add("com.gojek.driver.car");
        }
        alwaysOnPackages = linkedList;
        systemwideScreenOn = prefs.getBoolean("systemwide", true);
        lastUpdate = System.currentTimeMillis();
    }

    private void rootbeer(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "detectRootManagementApps", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "detectPotentiallyDangerousApps", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "detectTestKeys", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkForBusyBoxBinary", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkForSuBinary", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkSuExists", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkForRWPaths", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkForDangerousProps", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "checkForRootNative", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "detectRootCloakingApps", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "isSelinuxFlagInEnabled", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "isRooted", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "isRootedWithoutBusyBoxCheck", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeer", loadPackageParam.classLoader, "canLoadNativeLibrary", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("com.scottyab.rootbeer.RootBeerNative", loadPackageParam.classLoader, "wasNativeLibraryLoaded", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
    }

    /* access modifiers changed from: private */
    public void setApplicationLabel() {
        packageName = currentActivity.getPackageName();
        try {
            PackageManager packageManager = currentActivity.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(currentActivity.getPackageName(), 0);
            applicationLabel = (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : packageName);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            XposedBridge.log(e);
            applicationLabel = packageName;
        }
        if (applicationLabel == null) {
            applicationLabel = "App";
        }
    }

    /* access modifiers changed from: private */
    public boolean setFlagKeepScreenOn(boolean z, int i) {
        if (i == 0) {
            flagKeepScreenOn = z;
            systemwideScreenOn = z;
            if (z) {
                currentActivity.getWindow().addFlags(128);
            } else {
                currentActivity.getWindow().clearFlags(128);
            }
        } else if (i == 1) {
            flagKeepScreenOn = z;
            if (z) {
                currentActivity.getWindow().addFlags(128);
            } else {
                currentActivity.getWindow().clearFlags(128);
            }
            if (flagKeepScreenOn) {
            }
        }
        return isFlagKeepScreenOn();
    }

    /* access modifiers changed from: private */
    public void showToast(String str) {
        if (currentActivity != null) {
            Toast makeText = Toast.makeText(currentActivity, str, 0);
            TextView textView = (TextView) makeText.getView().findViewById(16908299);
            if (textView != null) {
                textView.setGravity(17);
            }
            makeText.show();
        }
    }

    private void xgp(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastLocation", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Location location = new Location("network");
                location.setLatitude(Double.valueOf(cku.a().a).doubleValue());
                location.setLongitude(Double.valueOf(cku.a().b).doubleValue());
                location.setAccuracy(0.01f);
                location.setTime(System.currentTimeMillis());
                if (Build.VERSION.SDK_INT >= 17) {
                    location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                }
                methodHookParam.setResult(location);
            }
        }});
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastKnownLocation", new Object[]{String.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Location location = new Location("network");
                location.setLatitude(Double.valueOf(cku.a().a).doubleValue());
                location.setLongitude(Double.valueOf(cku.a().b).doubleValue());
                location.setAccuracy(0.01f);
                location.setTime(System.currentTimeMillis());
                if (Build.VERSION.SDK_INT >= 17) {
                    location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                }
                methodHookParam.setResult(location);
            }
        }});
        XposedBridge.hookAllMethods(LocationManager.class, "getProviders", new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("gps");
                methodHookParam.setResult(arrayList);
            }
        });
        XposedHelpers.findAndHookMethod(LocationManager.class, "getBestProvider", new Object[]{Criteria.class, Boolean.TYPE, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult("gps");
            }
        }});
        XposedHelpers.findAndHookMethod(LocationManager.class, "addGpsStatusListener", new Object[]{GpsStatus.Listener.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (methodHookParam.args[0] != null) {
                    XposedHelpers.callMethod(methodHookParam.args[0], "onGpsStatusChanged", new Object[]{1});
                    XposedHelpers.callMethod(methodHookParam.args[0], "onGpsStatusChanged", new Object[]{3});
                }
            }
        }});
        XposedHelpers.findAndHookMethod(LocationManager.class, "addNmeaListener", new Object[]{GpsStatus.NmeaListener.class, new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                methodHookParam.setResult(false);
            }
        }});
        XposedHelpers.findAndHookMethod("android.location.LocationManager", loadPackageParam.classLoader, "getGpsStatus", new Object[]{GpsStatus.class, new XC_MethodHook() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: java.lang.Object[]} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                XC_MethodHook.MethodHookParam methodHookParam2 = methodHookParam;
                Object obj = (GpsStatus) methodHookParam.getResult();
                if (obj != null) {
                    Method method = null;
                    Method[] declaredMethods = GpsStatus.class.getDeclaredMethods();
                    int length = declaredMethods.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Method method2 = declaredMethods[i];
                        if (method2.getName().equals("setStatus") && method2.getParameterTypes().length > 1) {
                            method = method2;
                            break;
                        }
                        i++;
                    }
                    if (method != null) {
                        method.setAccessible(true);
                        int[] iArr = {1, 2, 3, 4, 5};
                        float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                        float[] fArr2 = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                        float[] fArr3 = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
                        XposedHelpers.callMethod(obj, "setStatus", new Object[]{5, iArr, fArr, fArr2, fArr3, 31, 31, 31});
                        methodHookParam2.args[0] = obj;
                        methodHookParam2.setResult(obj);
                        try {
                            method.invoke(obj, new Object[]{5, iArr, fArr, fArr2, fArr3, 31, 31, 31});
                            methodHookParam2.setResult(obj);
                        } catch (Exception e) {
                            XposedBridge.log(e);
                        }
                    }
                }
            }
        }});
        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates") && !Modifier.isAbstract(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                        if (methodHookParam.args.length >= 4 && (methodHookParam.args[3] instanceof LocationListener)) {
                            LocationListener locationListener = (LocationListener) methodHookParam.args[3];
                            Method method = null;
                            Method[] declaredMethods = LocationListener.class.getDeclaredMethods();
                            int length = declaredMethods.length;
                            int i = 0;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                Method method2 = declaredMethods[i];
                                if (method2.getName().equals("onLocationChanged") && !Modifier.isAbstract(method2.getModifiers())) {
                                    method = method2;
                                    break;
                                }
                                i++;
                            }
                            Location location = new Location("network");
                            location.setLatitude(Double.valueOf(cku.a().a).doubleValue());
                            location.setLongitude(Double.valueOf(cku.a().b).doubleValue());
                            location.setAccuracy(0.01f);
                            location.setTime(System.currentTimeMillis());
                            if (Build.VERSION.SDK_INT >= 17) {
                                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                            }
                            XposedHelpers.callMethod(locationListener, "onLocationChanged", new Object[]{location});
                            if (method != null) {
                                try {
                                    method.invoke(locationListener, new Object[]{location});
                                } catch (Exception e) {
                                    XposedBridge.log(e);
                                }
                            }
                        }
                    }
                });
            }
            if (method.getName().equals("requestSingleUpdate ") && !Modifier.isAbstract(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                        if (methodHookParam.args.length >= 3 && (methodHookParam.args[1] instanceof LocationListener)) {
                            LocationListener locationListener = (LocationListener) methodHookParam.args[3];
                            Method method = null;
                            Method[] declaredMethods = LocationListener.class.getDeclaredMethods();
                            int length = declaredMethods.length;
                            int i = 0;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                Method method2 = declaredMethods[i];
                                if (method2.getName().equals("onLocationChanged") && !Modifier.isAbstract(method2.getModifiers())) {
                                    method = method2;
                                    break;
                                }
                                i++;
                            }
                            if (method != null) {
                                try {
                                    Location location = new Location("network");
                                    location.setLatitude(Double.valueOf(cku.a().a).doubleValue());
                                    location.setLongitude(Double.valueOf(cku.a().b).doubleValue());
                                    location.setAccuracy(0.01f);
                                    location.setTime(System.currentTimeMillis());
                                    if (Build.VERSION.SDK_INT >= 17) {
                                        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                                    }
                                    method.invoke(locationListener, new Object[]{location});
                                } catch (Exception e) {
                                    XposedBridge.log(e);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Set<String> loadSetFromPrefs = loadSetFromPrefs(zza.a);
        prefs = new XSharedPreferences("com.cofface.invader", "com.cofface.invader");
        prefs.makeWorldReadable();
        prefs.reload();
        if (loadPackageParam.packageName.equals("com.kozhevin.rootchecks")) {
            natv(loadPackageParam);
        }
        if (loadPackageParam.packageName.equals("com.scottyab.rootbeer.sample")) {
            rootbeer(loadPackageParam);
        }
        if (loadSetFromPrefs.contains(loadPackageParam.packageName)) {
            this.appSet = loadSetFromPrefs;
            this.keywordSet = loadSetFromPrefs(zza.c);
            this.commandSet = loadSetFromPrefs(zza.b);
            this.libnameSet = loadSetFromPrefs(zza.d);
            if (prefs.getBoolean("as", true)) {
                hml(loadPackageParam);
            }
            if (prefs.getBoolean("ab", true)) {
                fa(loadPackageParam);
            }
            if (prefs.getBoolean("ac", true)) {
                l();
                next(loadPackageParam);
                f(loadPackageParam);
            }
            if (prefs.getBoolean("ad", true)) {
            }
            if (prefs.getBoolean("ab", true)) {
                gps(loadPackageParam);
            }
            XposedHelpers.findAndHookMethod("android.os.SystemProperties", loadPackageParam.classLoader, "get", new Object[]{String.class, new XC_MethodHook() {
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    if (((String) methodHookParam.args[0]).equals("ro.build.selinux")) {
                        methodHookParam.setResult("1");
                        if (invader3.this.debugPref) {
                            XposedBridge.log("SELinux is enforced.");
                        }
                    }
                }
            }});
        }
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) {
        XposedHelpers.findAndHookMethod(Instrumentation.class, "newActivity", new Object[]{ClassLoader.class, String.class, Intent.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                Activity unused = invader3.currentActivity = (Activity) methodHookParam.getResult();
                boolean unused2 = invader3.systemwideScreenOn = false;
                boolean unused3 = invader3.flagKeepScreenOn = false;
                int unused4 = invader3.currentMethode = 1;
                invader3.this.readPrefs();
            }
        }});
        XposedHelpers.findAndHookMethod(Activity.class, "onResume", new Object[]{new XC_MethodHook() {
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                invader3.this.readPrefs();
                invader3.this.setApplicationLabel();
                if (invader3.systemwideScreenOn) {
                    boolean unused = invader3.this.setFlagKeepScreenOn(invader3.systemwideScreenOn, 0);
                } else if (invader3.alwaysOnPackages.contains(invader3.packageName)) {
                    String str = "Get - Screen - " + invader3.applicationLabel + "AON";
                    XposedBridge.log(str);
                    invader3.this.showToast(str);
                    boolean unused2 = invader3.this.setFlagKeepScreenOn(true, 1);
                } else if (invader3.systemwideScreenOn) {
                    boolean unused3 = invader3.this.setFlagKeepScreenOn(true, 1);
                }
            }
        }});
        XposedHelpers.findAndHookMethod(Activity.class, "onKeyDown", new Object[]{Integer.TYPE, KeyEvent.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (!(methodHookParam.args[0] instanceof Integer)) {
                    methodHookParam.setResult(false);
                }
                int intValue = ((Integer) methodHookParam.args[0]).intValue();
                KeyEvent keyEvent = (KeyEvent) methodHookParam.args[1];
                if (invader3.currentMethode == 0) {
                    for (int i = 0; i <= 2; i++) {
                        if (intValue == invader3.activeKeys[i]) {
                            invader3.lastKeyDown[i] = System.currentTimeMillis();
                            keyEvent.startTracking();
                        }
                    }
                } else if (invader3.currentMethode == 1) {
                    if (intValue == invader3.activeKeys[1]) {
                        if (invader3.activeKeyPressed[2]) {
                            invader3.activeKeyPressed[2] = false;
                            boolean unused = invader3.this.setFlagKeepScreenOn(!invader3.flagKeepScreenOn, 1);
                            methodHookParam.setResult(true);
                            return;
                        }
                        invader3.activeKeyPressed[1] = true;
                        methodHookParam.setResult(false);
                    } else if (intValue != invader3.activeKeys[2]) {
                    } else {
                        if (invader3.activeKeyPressed[1]) {
                            invader3.activeKeyPressed[1] = false;
                            boolean unused2 = invader3.this.setFlagKeepScreenOn(!invader3.flagKeepScreenOn, 1);
                            methodHookParam.setResult(true);
                            return;
                        }
                        invader3.activeKeyPressed[2] = true;
                        methodHookParam.setResult(false);
                    }
                } else if (invader3.currentMethode == 5) {
                    if (intValue == invader3.activeKeys[0]) {
                        if (invader3.activeKeyPressed[1]) {
                            invader3.activeKeyPressed[1] = false;
                            boolean unused3 = invader3.this.setFlagKeepScreenOn(!invader3.flagKeepScreenOn, 1);
                            methodHookParam.setResult(true);
                        } else if (invader3.activeKeyPressed[2]) {
                            invader3.activeKeyPressed[2] = false;
                            boolean unused4 = invader3.this.setFlagKeepScreenOn(!invader3.systemwideScreenOn, 0);
                            methodHookParam.setResult(true);
                        } else {
                            invader3.activeKeyPressed[0] = true;
                            methodHookParam.setResult(false);
                        }
                    } else if (intValue == invader3.activeKeys[1]) {
                        if (invader3.activeKeyPressed[0]) {
                            invader3.activeKeyPressed[0] = false;
                            boolean unused5 = invader3.this.setFlagKeepScreenOn(!invader3.flagKeepScreenOn, 1);
                            methodHookParam.setResult(true);
                            return;
                        }
                        invader3.activeKeyPressed[1] = true;
                        methodHookParam.setResult(false);
                    } else if (intValue != invader3.activeKeys[2]) {
                    } else {
                        if (invader3.activeKeyPressed[0]) {
                            invader3.activeKeyPressed[0] = false;
                            boolean unused6 = invader3.this.setFlagKeepScreenOn(!invader3.systemwideScreenOn, 0);
                            methodHookParam.setResult(true);
                            return;
                        }
                        invader3.activeKeyPressed[2] = true;
                        methodHookParam.setResult(false);
                    }
                } else if (invader3.currentMethode == 4) {
                    if (intValue == invader3.activeKeys[0]) {
                        invader3.activeKeyPressed[0] = true;
                        if (invader3.isTouch) {
                            methodHookParam.setResult(true);
                        }
                    } else if (intValue == invader3.activeKeys[1]) {
                        invader3.activeKeyPressed[1] = true;
                        if (invader3.isTouch) {
                            methodHookParam.setResult(true);
                        }
                    } else if (intValue == invader3.activeKeys[2]) {
                        invader3.activeKeyPressed[2] = true;
                        if (invader3.isTouch) {
                            methodHookParam.setResult(true);
                        }
                    }
                } else if (invader3.currentMethode == 2) {
                    Toast.makeText(invader3.currentActivity, "test", 0).show();
                    if (intValue != invader3.activeKeys[0]) {
                        if (intValue == invader3.activeKeys[1] && invader3.isTouch) {
                            boolean unused7 = invader3.this.setFlagKeepScreenOn(!invader3.flagKeepScreenOn, 1);
                            methodHookParam.setResult(true);
                        } else if (intValue == invader3.activeKeys[2] && invader3.isTouch) {
                            boolean unused8 = invader3.this.setFlagKeepScreenOn(!invader3.systemwideScreenOn, 0);
                            methodHookParam.setResult(true);
                        }
                    }
                } else if (invader3.currentMethode == 3) {
                    long j = 0;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (intValue != invader3.activeKeys[0]) {
                        if (intValue == invader3.activeKeys[1]) {
                            j = currentTimeMillis - invader3.lastUp;
                            long unused9 = invader3.lastDown = currentTimeMillis;
                        } else if (intValue == invader3.activeKeys[2]) {
                            j = currentTimeMillis - invader3.lastDown;
                            long unused10 = invader3.lastUp = currentTimeMillis;
                        }
                    }
                    if (j < 300) {
                        Activity access$300 = invader3.currentActivity;
                        Toast.makeText(access$300, "distance: " + j, 0).show();
                    }
                }
            }
        }});
        XposedHelpers.findAndHookMethod(Activity.class, "onKeyUp", new Object[]{Integer.TYPE, KeyEvent.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (!(methodHookParam.args[0] instanceof Integer)) {
                    methodHookParam.setResult(false);
                }
                int intValue = ((Integer) methodHookParam.args[0]).intValue();
                if (invader3.currentMethode == 0) {
                    if (intValue == invader3.activeKeys[0] && invader3.lastKeyDown[0] != -1 && System.currentTimeMillis() - invader3.lastKeyDown[0] > 1100) {
                        return;
                    }
                    if (intValue == invader3.activeKeys[1] && invader3.lastKeyDown[1] != -1 && System.currentTimeMillis() - invader3.lastKeyDown[1] > 1100) {
                        invader3.lastKeyDown[1] = -1;
                        Toast.makeText(invader3.currentActivity, "woopwoop long key press", 0).show();
                    } else if (intValue == invader3.activeKeys[2] && invader3.lastKeyDown[2] != -1 && System.currentTimeMillis() - invader3.lastKeyDown[2] > 1100) {
                        invader3.lastKeyDown[1] = -1;
                        Toast.makeText(invader3.currentActivity, "woopwoop long key press", 0).show();
                    }
                } else if (invader3.currentMethode == 1 || invader3.currentMethode == 4 || invader3.currentMethode == 5) {
                    for (int i = 0; i <= 2; i++) {
                        if (intValue == invader3.activeKeys[i]) {
                            invader3.activeKeyPressed[i] = false;
                            methodHookParam.setResult(true);
                        }
                    }
                } else {
                    int unused = invader3.currentMethode;
                }
            }
        }});
    }

    public boolean isFlagKeepScreenOn() {
        return (currentActivity.getWindow().getAttributes().flags & 128) != 0;
    }

    public boolean stringContainsFromSet(String str, Set<String> set) {
        if (str == null || set == null) {
            return false;
        }
        for (String str2 : set) {
            if (str.matches(".*(\\W|^)" + str2 + "(\\W|$).*")) {
                return true;
            }
        }
        return false;
    }

    public boolean stringEndsWithFromSet(String str, Set<String> set) {
        if (str == null || set == null) {
            return false;
        }
        for (String endsWith : set) {
            if (str.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }
}
