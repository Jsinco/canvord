package dev.jsinco.canvord.utility;

import org.jetbrains.annotations.Nullable;

public final class StringUtil {

    @Nullable
    public static String getFromEnvironment(String key) {
        String str = System.getProperty(key);
        if (str == null) {
            str = System.getenv(key);
        }
        return str;
    }
}
