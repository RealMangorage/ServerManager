package org.mangorage.servermanager;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test {
    private static Object object = new Object();
    private static Reference<Object> objectReference = new SoftReference<>(object);

    private static Object objectb = new Object();
    private static Reference<Object> objectReferenceb = new WeakReference<>(objectb);

    public static void main(String[] args) {
        System.out.println(object);
        System.out.println(objectb);

        object = null;
        objectb = null;

        System.gc();

        System.out.println(objectReference.get());
        System.out.println(objectReferenceb.get());
    }
}
