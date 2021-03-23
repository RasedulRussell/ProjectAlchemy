package com.projectalchemy.util;

import java.net.URL;
import java.util.regex.*;

public class UrlValidator {

    public static boolean isValid(String url, String validPattern) {
        Pattern pattern = Pattern.compile(validPattern);
        Matcher matcher = pattern.matcher(url);
        boolean ok = matcher.matches();
        return ok;
    }
}
