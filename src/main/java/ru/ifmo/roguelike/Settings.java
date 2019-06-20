package ru.ifmo.roguelike;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Class to get player settings from config.properties
 */
public class Settings {
    private static Properties initialProperties;
    private static Map<String, String> settedProperties = new HashMap<>();

    public static void addPropertyFile(Properties properties) {
        initialProperties = properties;
    }

    public static <T> T getProperty(String key, Class<T> clazz) {
        String result;
        if (settedProperties.containsKey(key)) {
            result = settedProperties.get(key);
        } else {
            result = initialProperties.getProperty(key);
        }
        if (result == null) {
            return null;
        }
        if (Integer.class == clazz) {
            return (T) Integer.valueOf(result);
        }
        if (String.class == clazz) {
            return (T) result;
        }
        if (Character.class == clazz) {
            return (T) Character.valueOf(result.toCharArray()[0]);
        }
        return (T) result;
    }

    public static void setProperty(String key, Object value) {
        settedProperties.put(key, String.valueOf(value));
    }
}
