package com.gad.util;

import org.gad.util.GetEnvVars;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GetEnvVarsTest {

    @Test
    public void test() {
        GetEnvVars getEnv = new GetEnvVars();
        assertNotNull(getEnv.getVar("JAVA_HOME"));
    }
}
