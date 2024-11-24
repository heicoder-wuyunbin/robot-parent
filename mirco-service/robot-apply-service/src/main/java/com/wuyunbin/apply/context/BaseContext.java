package com.wuyunbin.apply.context;

public class BaseContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void set(String key) {
        context.set(key);
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
