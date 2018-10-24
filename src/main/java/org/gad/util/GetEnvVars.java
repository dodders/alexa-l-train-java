package org.gad.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import static java.lang.String.format;

public class GetEnvVars {

    Logger log = LogManager.getLogger(this.getClass());

    public String getVar(String key) {
        if (key == null) {
            return null;
        }
        String envVar = System.getenv(key);
        if (envVar == null) {
            log.info(format("didn't find key %s in environment variables.", key));
            return null;
        } else {
            return envVar;
        }
    }
}
