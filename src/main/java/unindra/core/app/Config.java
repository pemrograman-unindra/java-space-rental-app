package unindra.core.app;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;

public class Config {
    private static Dotenv dotenv;

    static {
        try {
            File envFile = new File(".env");
            if (envFile.exists()) {
                dotenv = Dotenv.configure().directory(".").ignoreIfMalformed().ignoreIfMissing().load();
            }
        } catch (Exception e) {
            // Tidak perlu lempar error, cukup abaikan dan pakai default
        }
    }

    public static String get(String key, String defaultValue) {
        if (dotenv != null) {
            String value = dotenv.get(key);
            if (value != null) return value;
        }
        String envValue = System.getenv(key);
        return envValue != null ? envValue : defaultValue;
    }

    public static String get(String key) {
        return get(key, null);
    }
}
