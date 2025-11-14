package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = TestConfig.class.getResourceAsStream("/test.properties")) {
            if (is != null) props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar test.properties", e);
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url", "http://localhost:8080");
    }

    public static String getUserEmail() {
        return props.getProperty("user.email", "user@test.com");
    }

    public static String getUserPassword() {
        return props.getProperty("user.password", "validPass123");
    }

    public static String getVisitorEmail() {
        return props.getProperty("visitor.email", "visitor@test.com");
    }

    public static String getLockedUserEmail() {
        return props.getProperty("locked.user.email", "blocked@test.com");
    }

    public static String getInvalidPassword() {
        return props.getProperty("invalid.password", "wrongPass");
    }
}