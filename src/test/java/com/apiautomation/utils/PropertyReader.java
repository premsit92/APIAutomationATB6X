package com.apiautomation.utils;

import org.yaml.snakeyaml.introspector.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {
    public String readKey(String key){

        Properties properties = new Properties();
        try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/data.properties");
                properties.load(fileInputStream);


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        return properties.getProperty(key);
    }

}
