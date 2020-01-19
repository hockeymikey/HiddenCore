package defpackage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* renamed from: zza  reason: default package */
public class zza {
    public static final e a = new a();
    public static final e b = new b();
    public static final e c = new c();
    public static final e d = new d();

    /* renamed from: zza$a */
    public static class a extends e {
        public static final Set<String> a = new HashSet(Arrays.asList(list.a));

        public Set<String> a() {
            return a;
        }

        public String b() {
            return "CustomizeApps";
        }

        public String c() {
            return "com.coffaceAPPS_LIST";
        }
    }

    /* renamed from: zza$b */
    public static class b extends e {
        public static final Set<String> a = new HashSet(Arrays.asList(list.b));

        public Set<String> a() {
            return a;
        }

        public String b() {
            return "CustomizeCommands";
        }

        public String c() {
            return "com.coffaceAPPS_SET";
        }
    }

    /* renamed from: zza$c */
    public static class c extends e {
        public static final Set<String> a = new HashSet(Arrays.asList(list.c));

        public Set<String> a() {
            return a;
        }

        public String b() {
            return "CustomizeKeywords";
        }

        public String c() {
            return "com.coffaceSET";
        }
    }

    /* renamed from: zza$d */
    public static class d extends e {
        public static final Set<String> a = new HashSet(Arrays.asList(list.d));

        public Set<String> a() {
            return a;
        }

        public String b() {
            return "CustomizeLibnames";
        }

        public String c() {
            return "LIBNAMES_SET";
        }
    }

    /* renamed from: zza$e */
    public static abstract class e {
        public abstract Set<String> a();

        public abstract String b();

        public abstract String c();
    }
}
