package com.github.orange_miaow.component;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor() {
        super(ASM4);
    }

}
