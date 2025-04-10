package com.github.orange_miaow.facilities;

import com.github.orange_miaow.definition.AnnotationDefinition;
import com.github.orange_miaow.definition.PropertyDefinition;
import org.objectweb.asm.*;

import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class GeneratingFacility {

    public static byte[] generateBeanClassByteArray(int version, String className, List<AnnotationDefinition> classAnnotationDefinitions, List<PropertyDefinition> propertyDefinitions) {
        String internalClassName = className.replace('.', '/');

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(version, ACC_PUBLIC | ACC_SUPER, internalClassName, null, "java/lang/Object", null);

        // generate class annotation
        if (null != classAnnotationDefinitions) {
            for (int i = 0; i < classAnnotationDefinitions.size(); i++) {
                AnnotationDefinition annotationDefinition = classAnnotationDefinitions.get(i);
                String annotationDescriptor = Type.getType(annotationDefinition.getType()).getDescriptor();

                annotationVisitor0 = classWriter.visitAnnotation(annotationDescriptor, true);
                annotationDefinition.getAnnotationAssignment().assign(annotationVisitor0);
            }
        }

        // generate field
        for (int i = 0; i < propertyDefinitions.size(); i++) {
            PropertyDefinition propertyDefinition = propertyDefinitions.get(i);
            String fieldName = propertyDefinition.getName();
            String fieldDescriptor = Type.getType(propertyDefinition.getType()).getDescriptor();

            fieldVisitor = classWriter.visitField(ACC_PRIVATE, fieldName, fieldDescriptor, null, null);

            // generate field annotation
            List<AnnotationDefinition> fieldAnnotationDefinitions = propertyDefinition.getAnnotationInfos();
            if (null != fieldAnnotationDefinitions) {
                for (int j = 0; j < fieldAnnotationDefinitions.size(); j++) {
                    AnnotationDefinition annotationDefinition = fieldAnnotationDefinitions.get(j);
                    String annotationDescriptor = Type.getType(annotationDefinition.getType()).getDescriptor();

                    annotationVisitor0 = fieldVisitor.visitAnnotation(annotationDescriptor, true);
                    annotationDefinition.getAnnotationAssignment().assign(annotationVisitor0);
                }
            }

            fieldVisitor.visitEnd();
        }

        // generate constructor
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        // generate getter and setter
        for (int i = 0; i < propertyDefinitions.size(); i++) {
            PropertyDefinition propertyDefinition = propertyDefinitions.get(i);
            String fieldName = propertyDefinition.getName();
            String fieldDescriptor = Type.getType(propertyDefinition.getType()).getDescriptor();

            String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String getterMethodDescriptor = "()" + fieldDescriptor;

            String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String setterMethodDescriptor = "(" + fieldDescriptor + ")V";

            int getterMethodReturnOpcode = ARETURN;
            int setterMethodArgOpcode = ALOAD;
            int getterMethodMaxStack = 1;
            int setterMethodMaxStack = 2;
            int setterMethodMaxLocals = 2;
            if (fieldDescriptor.equals("Z") || fieldDescriptor.equals("B") || fieldDescriptor.equals("C") || fieldDescriptor.equals("S") || fieldDescriptor.equals("I")) {
                getterMethodReturnOpcode = IRETURN;
                setterMethodArgOpcode = ILOAD;
            } else if (fieldDescriptor.equals("J")) {
                getterMethodReturnOpcode = LRETURN;
                setterMethodArgOpcode = LLOAD;
                getterMethodMaxStack = 2;
                setterMethodMaxStack = 3;
                setterMethodMaxLocals = 3;
            } else if (fieldDescriptor.equals("F")) {
                getterMethodReturnOpcode = FRETURN;
                setterMethodArgOpcode = FLOAD;
            } else if (fieldDescriptor.equals("D")) {
                getterMethodReturnOpcode = DRETURN;
                setterMethodArgOpcode = DLOAD;
                getterMethodMaxStack = 2;
                setterMethodMaxStack = 3;
                setterMethodMaxLocals = 3;
            }

            {
                methodVisitor = classWriter.visitMethod(ACC_PUBLIC, getterMethodName, getterMethodDescriptor, null, null);
                methodVisitor.visitCode();
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, internalClassName, fieldName, fieldDescriptor);
                methodVisitor.visitInsn(getterMethodReturnOpcode);
                methodVisitor.visitMaxs(getterMethodMaxStack, 1);
                methodVisitor.visitEnd();
            }
            {
                methodVisitor = classWriter.visitMethod(ACC_PUBLIC, setterMethodName, setterMethodDescriptor, null, null);
                methodVisitor.visitCode();
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitVarInsn(setterMethodArgOpcode, 1);
                methodVisitor.visitFieldInsn(PUTFIELD, internalClassName, fieldName, fieldDescriptor);
                methodVisitor.visitInsn(RETURN);
                methodVisitor.visitMaxs(setterMethodMaxStack, setterMethodMaxLocals);
                methodVisitor.visitEnd();
            }
        }

        classWriter.visitEnd();

        return classWriter.toByteArray();
    }

    public static byte[] generateBeanClassByteArray(String className, List<AnnotationDefinition> classAnnotationDefinitions, List<PropertyDefinition> propertyDefinitions) {
        int classVersion = ClassFacility.getClassVersion();
        return generateBeanClassByteArray(classVersion, className, classAnnotationDefinitions, propertyDefinitions);
    }

}