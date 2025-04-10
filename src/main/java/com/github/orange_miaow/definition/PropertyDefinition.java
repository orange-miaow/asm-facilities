package com.github.orange_miaow.definition;

import java.util.List;

public class PropertyDefinition {

    private String name;
    private Class type;
    private List<AnnotationDefinition> annotationDefinitions;

    public PropertyDefinition() {
    }

    public PropertyDefinition(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public PropertyDefinition(String name, Class type, List<AnnotationDefinition> annotationDefinitions) {
        this.name = name;
        this.type = type;
        this.annotationDefinitions = annotationDefinitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public List<AnnotationDefinition> getAnnotationInfos() {
        return annotationDefinitions;
    }

    public void setAnnotationInfos(List<AnnotationDefinition> annotationDefinitions) {
        this.annotationDefinitions = annotationDefinitions;
    }
}
