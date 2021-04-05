package com.projectalchemy.util;


import java.util.regex.*;

public class UrlValidator {

    public static boolean isValid(String url, String validPattern) {
        Pattern pattern = Pattern.compile(validPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
