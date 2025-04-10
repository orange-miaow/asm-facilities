package com.github.orange_miaow.definition;

import org.objectweb.asm.AnnotationVisitor;

@FunctionalInterface
public interface AnnotationAssignment {

    void assign(AnnotationVisitor visitor);

}
