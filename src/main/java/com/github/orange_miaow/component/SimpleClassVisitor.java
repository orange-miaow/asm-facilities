package com.github.orange_miaow.component;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class SimpleClassVisitor extends ClassVisitor {

    private int classVersion;

    public SimpleClassVisitor(ClassVisitor cv) {
        super(ASM4, cv);
    }

    public int getClassVersion() {
        return classVersion;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.classVersion = version;
    }

}
