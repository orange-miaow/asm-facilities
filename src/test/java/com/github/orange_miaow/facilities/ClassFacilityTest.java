package com.github.orange_miaow.facilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassFacilityTest {

    @Test
    void getClassVersion() {
        int classVersion = ClassFacility.getClassVersion(ExampleObject.class.getName());
        System.out.println("class version: " + classVersion);
    }
}