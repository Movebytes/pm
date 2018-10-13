package com.pm.core;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(Configuration.APPLICATION_PATH)
public class Configuration extends Application {
    final public static String APPLICATION_PATH = "/api";
}
