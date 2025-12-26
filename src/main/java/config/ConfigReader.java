package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            properties.load(input);

            // Debug AFTER loading
            System.out.println("Browser from config: " + properties.getProperty("browser"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
        // prevent object creation
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
