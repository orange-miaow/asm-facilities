package com.github.orange_miaow.facilities;

import com.github.orange_miaow.component.MyClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class TracingFacility {

    public static void traceClass(String className, ClassVisitor classVisitor, Printer printer, PrintWriter printWriter, int parsingOptions) throws IOException {
        TraceClassVisitor tcv = new TraceClassVisitor(classVisitor, printer, printWriter);

        ClassReader cr = new ClassReader(className);
        cr.accept(tcv, parsingOptions);
    }

    public static void traceClassByASMifier(String className, int parsingOptions) throws IOException {
        traceClass(className, new MyClassVisitor(), new ASMifier(), new PrintWriter(System.out), parsingOptions);
    }

    public static void traceClassByTextifier(String className, int parsingOptions) throws IOException {
        traceClass(className, new MyClassVisitor(), new Textifier(), new PrintWriter(System.out), parsingOptions);
    }

}
