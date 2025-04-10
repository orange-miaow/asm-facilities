package com.github.orange_miaow.component;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }

}
