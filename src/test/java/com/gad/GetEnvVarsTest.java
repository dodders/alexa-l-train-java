package com.gad;

import org.gad.GetEnvVars;
import org.gad.MyContext;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GetEnvVarsTest {

    @Test
    public void test() {
        MyContext ctx = new MyContext();
        GetEnvVars getEnv = new GetEnvVars(ctx.getLogger());
        assertNotNull(getEnv.getVar("JAVA_HOME"));
    }
}
