package com.hugo.common.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by ohj on 2016/10/25.
 */
public class Config {
    private static Configuration config = null;
    public static String getConfig(String name,String defaultValue){
        if(config==null){
            try {
                config = new PropertiesConfiguration("properties/config.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        return config.getString(name,defaultValue);
    }

    public static String getConfig(String name,String defaultValue,boolean reload){
        if(reload){
            try {
                config = new PropertiesConfiguration("properties/config.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        return config.getString(name,defaultValue);
    }
}
