package utils;

import java.util.Properties;

public class PropertiesUtil {
	public static Properties ReadPropertiesUtil() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			properties.load(classLoader.getResourceAsStream("/config/database.properties"));
		}catch(Exception e) {
			
		}
		return properties;
	}
}
