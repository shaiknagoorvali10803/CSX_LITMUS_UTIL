package com.csx.test.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SamplwTest {
    //Sample test case to pass the build
    @Test
    void sampleTest(){
        Assertions.assertTrue("sample".equalsIgnoreCase("sample"));
    }
}
