package org.gad;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class GetEnvVars {

    Properties props = new Properties();
    LambdaLogger logger;

    public GetEnvVars(LambdaLogger logger) {
        this.logger = logger;
        String fname = System.getProperty("user.home") + File.separator + ".keys" + File.separator + "keyfile";
        Path p = Paths.get(fname);
        try {
            InputStream is = Files.newInputStream(p, StandardOpenOption.READ);
            props.load(is);
            logger.log(props.entrySet().size() + " props loaded from " + fname);
        } catch (IOException e) {
            logger.log("did not load properties from file:" + e.getMessage());
        }
    }

    public String getVar(String key) {
        if (props.containsKey(key)) {
            logger.log(String.format("found key %s in local keystore.", key));
            return props.getProperty(key);
        } else {
            logger.log(String.format("found key %s in environment.", key));
            return System.getenv(key);
        }
    }
}
