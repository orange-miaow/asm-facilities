package com.github.orange_miaow.facilities;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TracingFacilityTest {

    @Test
    void traceClassByASMifier() throws IOException {
        TracingFacility.traceClassByASMifier(ExampleObject.class.getName(), ClassReader.SKIP_DEBUG);
    }

    @Test
    void traceClassByTextifier() throws IOException {
        TracingFacility.traceClassByTextifier(ExampleObject.class.getName(), ClassReader.SKIP_DEBUG);
    }
}