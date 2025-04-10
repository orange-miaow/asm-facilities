package com.github.orange_miaow.facilities;

import com.github.orange_miaow.component.MyClassLoader;
import com.github.orange_miaow.definition.PropertyDefinition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GeneratingFacilityTest {

    @Test
    void generateBeanClassByteArrayWithoutAnnotation() throws Exception {
        String className = "generated.Student";
        List<PropertyDefinition> propertyDefinitions = new ArrayList<>() {{
            add(new PropertyDefinition("id", long.class));
            add(new PropertyDefinition("name", String.class));
            add(new PropertyDefinition("age", int.class));
        }};

        byte[] bytes = GeneratingFacility.generateBeanClassByteArray(className, null, propertyDefinitions);
        Class<?> clazz = new MyClassLoader(GeneratingFacilityTest.class.getClassLoader()).defineClass(className, bytes);

        Object object = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("setId", long.class).invoke(object, 10001L);
        clazz.getMethod("setName", String.class).invoke(object, "Mike");
        clazz.getMethod("setAge", int.class).invoke(object, 20);

        Object id = clazz.getMethod("getId").invoke(object);
        Object name = clazz.getMethod("getName").invoke(object);
        Object age = clazz.getMethod("getAge").invoke(object);

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }

}