package com.github.orange_miaow.facilities;

import com.github.orange_miaow.component.SimpleClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class ClassFacility {

    public static int getClassVersion(String className) {
        ClassWriter cw = new ClassWriter(0);
        SimpleClassVisitor cv = new SimpleClassVisitor(cw);

        ClassReader cr = null;
        try {
            cr = new ClassReader(className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cr.accept(cv, 0);

        return cv.getClassVersion();
    }

    public static int getClassVersion() {
        return getClassVersion(ClassFacility.class.getName());
    }

}
