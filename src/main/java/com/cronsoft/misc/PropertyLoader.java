package com.cronsoft.misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	private static String SERVER_PROPERTIES = "url.properties";
	
	public static Properties loadServerProperties() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try (InputStream resourcesStream = loader.getResourceAsStream(SERVER_PROPERTIES)) {
			props.load(resourcesStream);
		} catch (IOException ex) {
		}
		return props;
	}
	
}

