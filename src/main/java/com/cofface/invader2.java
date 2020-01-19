package com.cofface;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class invader2 implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    /* access modifiers changed from: private */
    public static Activity c;
    private a a;
    private a b;
    /* access modifiers changed from: private */
    public Set<String> d;
    /* access modifiers changed from: private */
    public Set<String> e;
    /* access modifiers changed from: private */
    public Set<String> f;
    private Set<String> g;
    /* access modifiers changed from: private */
    public Set<String> h;

    /* access modifiers changed from: private */
    public Boolean a(String str, String[] strArr) {
        for (String endsWith : strArr) {
            if (endsWith.endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private void a(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        c(loadPackageParam);
        d(loadPackageParam);
        e(loadPackageParam);
        g(loadPackageParam);
        f(loadPackageParam);
        XposedBridge.log("HiddenCore Module Actived");
        XposedBridge.log("HiddenCore Module Working");
        XposedBridge.log("Safety Net Enabled");
        XposedBridge.log("Hide Root - Hide Xposed - Hide Mock Location Work");
    }

    /* access modifiers changed from: private */
    public boolean a(String str, Set<String> set) {
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

    /* access modifiers changed from: private */
    public String[] a(String[] strArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add("sh");
            arrayList.add("-onEventParameters");
        }
        for (String append : strArr) {
            sb.append(" ");
            sb.append(append);
        }
        for (String append2 : this.e) {
            sb.append(" | grep -v ");
            sb.append(append2);
        }
        arrayList.add(sb.toString());
        return (String[]) arrayList.toArray(new String[0]);
    }

    private void b() {
        ClassLoader classLoader = null;
        XposedHelpers.findAndHookMethod("java.security.MessageDigest", classLoader, "isEqual", new Object[]{byte[].class, byte[].class, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
        XposedHelpers.findAndHookMethod("java.security.Signature", classLoader, "verify", new Object[]{byte[].class, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
        XposedHelpers.findAndHookMethod("java.security.Signature", classLoader, "verify", new Object[]{byte[].class, Integer.TYPE, Integer.TYPE, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                methodHookParam.setResult(Boolean.TRUE);
            }
        }});
    }

    private void b(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (Build.VERSION.SDK_INT >= 23) {
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "checkOp", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "checkOp", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "checkOpNoThrow", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "checkOpNoThrow", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteOp", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteOp", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteOpNoThrow", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteOpNoThrow", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteProxyOp", new Object[]{String.class, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteProxyOp", new Object[]{Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteProxyOpNoThrow", new Object[]{String.class, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "noteProxyOpNoThrow", new Object[]{Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "startOp", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "startOp", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "startOpNoThrow", new Object[]{String.class, Integer.TYPE, String.class, this.a});
            XposedHelpers.findAndHookMethod("android.app.AppOpsManager", loadPackageParam.classLoader, "startOpNoThrow", new Object[]{Integer.TYPE, Integer.TYPE, String.class, this.a});
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str, Set<String> set) {
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

    private void c(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPackageParam.classLoader, "getString", new Object[]{ContentResolver.class, String.class, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                if (methodHookParam.args[1].equals("mockmock_location")) {
                    methodHookParam.setResult("0");
                }
            }
        }});
        if (Build.VERSION.SDK_INT >= 17) {
            XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPackageParam.classLoader, "getStringForUser", new Object[]{ContentResolver.class, String.class, Integer.TYPE, new XC_MethodHook() {
                final invader2 a = new invader2();

                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                    if (methodHookParam.args[1].equals("mock_location")) {
                        methodHookParam.setResult("0");
                    }
                }
            }});
        }
        if (Build.VERSION.SDK_INT >= 18) {
            XposedHelpers.findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "isFromMockProvider", new Object[]{new XC_MethodHook() {
                final invader2 a = new invader2();

                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                    methodHookParam.setResult(false);
                }
            }});
        }
    }

    private void d(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledApplications", new Object[]{Integer.TYPE, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
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
            final invader2 a = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
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
    }

    private void e(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("java.lang.Class", loadPackageParam.classLoader, "forName", new Object[]{String.class, Boolean.TYPE, ClassLoader.class, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                String str = (String) methodHookParam.args[0];
                if (str != null) {
                    if (str.equals("de.robv.android.xposed.XposedBridge") || str.equals("de.robv.android.xposed.XC_MethodReplacement")) {
                        methodHookParam.setThrowable(new ClassNotFoundException());
                    }
                }
            }
        }});
    }

    private void f(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedHelpers.findAndHookMethod("java.lang.Runtime", loadPackageParam.classLoader, "exec", new Object[]{String[].class, String[].class, File.class, new XC_MethodHook() {
            final invader2 a = new invader2();

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v25, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                String[] strArr = (String[]) methodHookParam.args[0];
                if (strArr != null && strArr.length >= 1) {
                    String str = strArr[0];
                    if (this.a.b(str, this.a.d)) {
                        if (str.equals("su") || str.endsWith("/su")) {
                            methodHookParam.setThrowable(new IOException());
                        } else if (!this.a.d.contains("pm") || (!str.equals("pm") && !str.endsWith("/pm"))) {
                            if (this.a.d.contains("ps") && (str.equals("ps") || str.endsWith("/ps"))) {
                                methodHookParam.args[0] = this.a.a(strArr, true);
                            } else if (this.a.d.contains("which") && (str.equals("which") || str.endsWith("/which"))) {
                                methodHookParam.setThrowable(new IOException());
                            } else if (this.a.d.contains("busybox") && this.a.a("busybox", strArr).booleanValue()) {
                                methodHookParam.setThrowable(new IOException());
                            } else if (!this.a.d.contains("sh") || (!str.equals("sh") && !str.endsWith("/sh"))) {
                                methodHookParam.setThrowable(new IOException());
                            } else {
                                methodHookParam.setThrowable(new IOException());
                            }
                        } else if (strArr.length >= 3 && strArr[1].equalsIgnoreCase("list") && strArr[2].equalsIgnoreCase("packages")) {
                            methodHookParam.args[0] = this.a.a(strArr, true);
                        } else if (strArr.length >= 3) {
                            if ((strArr[1].equalsIgnoreCase("dump") || strArr[1].equalsIgnoreCase("path")) && this.a.a(strArr[2], (Set<String>) this.a.e)) {
                                methodHookParam.args[0] = new String[]{strArr[0], strArr[1], "FAKE.JUNK.PACKAGE"};
                            }
                        }
                    }
                }
            }
        }});
        XposedHelpers.findAndHookMethod("java.lang.Runtime", loadPackageParam.classLoader, "loadLibrary", new Object[]{String.class, ClassLoader.class, new XC_MethodHook() {
            final invader2 a = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                String str = (String) methodHookParam.args[0];
                if (str != null && this.a.a(str, (Set<String>) this.a.f)) {
                    methodHookParam.setResult((Object) null);
                }
            }
        }});
    }

    private void g(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(File.class, new Class[]{String.class}), new XC_MethodHook(10000) {
            final invader2 a = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                if (((String) methodHookParam.args[0]).endsWith("su")) {
                    methodHookParam.args[0] = "/system/xbin/FAKEJUNKFILE";
                } else if (((String) methodHookParam.args[0]).endsWith("busybox")) {
                    methodHookParam.args[0] = "/system/xbin/FAKEJUNKFILE";
                } else if (this.a.a((String) methodHookParam.args[0], (Set<String>) this.a.e)) {
                    methodHookParam.args[0] = "/system/app/FAKEJUNKFILE.apk";
                }
            }
        });
        XposedBridge.hookMethod(XposedHelpers.findConstructorExact(File.class, new Class[]{String.class, String.class}), new XC_MethodHook(10000) {
            final invader2 a = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                if (((String) methodHookParam.args[1]).equalsIgnoreCase("su")) {
                    methodHookParam.args[1] = "FAKEJUNKFILE";
                } else if (((String) methodHookParam.args[1]).contains("busybox")) {
                    methodHookParam.args[1] = "FAKEJUNKFILE";
                } else if (this.a.a((String) methodHookParam.args[1], (Set<String>) this.a.e)) {
                    methodHookParam.args[1] = "FAKEJUNKFILE.apk";
                }
            }
        });
    }

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        this.g = new HashSet(Arrays.asList(listed.b));
        this.d = new HashSet(Arrays.asList(listed.d));
        this.e = new HashSet(Arrays.asList(listed.c));
        this.f = new HashSet(Arrays.asList(listed.e));
        b(loadPackageParam);
        if (this.g.contains(loadPackageParam.packageName) || this.g.contains(loadPackageParam.processName)) {
            a(loadPackageParam);
        }
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        XposedHelpers.findAndHookMethod(Instrumentation.class, "newActivity", new Object[]{ClassLoader.class, String.class, Intent.class, new XC_MethodHook() {
            final invader2 listed = new invader2();

            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                Activity unused = invader2.c = (Activity) methodHookParam.getResult();
            }
        }});
        XposedHelpers.findAndHookMethod(Activity.class, "onResume", new Object[]{new XC_MethodHook() {
            final invader2 listed = new invader2();

            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                Set unused = this.listed.h = new HashSet(Arrays.asList(listed.a));
                if (invader2.c != null && this.listed.h.contains(invader2.c.getPackageName())) {
                    invader2.c.getWindow().addFlags(128);
                    Activity unused2 = invader2.c;
                }
            }
        }});
        if (Build.VERSION.SDK_INT >= 23) {
            this.a = new a() {
                final invader2 b = new invader2();

                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                    Object obj = methodHookParam.args[0];
                    if (obj.equals(58) || obj.equals("android:mock_location")) {
                        methodHookParam.setResult(0);
                    }
                }
            };
            this.b = new a() {
                final invader2 b = new invader2();

                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                    Object obj = methodHookParam.args[0];
                    if (obj.equals(58) || obj.equals("android:mock_location")) {
                        methodHookParam.setResult((Object) null);
                    }
                }
            };
        }
        b();
    }
}
