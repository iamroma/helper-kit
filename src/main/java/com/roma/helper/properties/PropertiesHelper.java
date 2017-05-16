package com.roma.helper.properties;

import com.roma.helper.Helper;
import com.roma.helper.common.ArgsHelper;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by roma on 16/05/2017.
 */
public class PropertiesHelper implements Helper {

    public static Properties loadFromClasspath(String fileName) throws Exception {
        if (ArgsHelper.isNullOrEmpty(fileName)) return null;
        ClassLoader classLoader = PropertiesHelper.class.getClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream(fileName)) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = PropertiesHelper.loadFromClasspath("auth.properties");
        System.out.println(properties.getProperty("rest.server", "No"));
    }
}
