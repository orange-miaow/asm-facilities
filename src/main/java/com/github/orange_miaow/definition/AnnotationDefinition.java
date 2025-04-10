package com.github.orange_miaow.definition;

public class AnnotationDefinition {

    private Class type;
    private AnnotationAssignment annotationAssignment;

    public AnnotationDefinition() {
    }

    public AnnotationDefinition(Class type, AnnotationAssignment annotationAssignment) {
        this.type = type;
        this.annotationAssignment = annotationAssignment;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public AnnotationAssignment getAnnotationAssignment() {
        return annotationAssignment;
    }

    public void setAnnotationAssignment(AnnotationAssignment annotationAssignment) {
        this.annotationAssignment = annotationAssignment;
    }
}
