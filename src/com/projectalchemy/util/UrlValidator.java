package com.projectalchemy.util;

import java.net.URL;

public class UrlValidator {

    public static boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            if(url ==null || url.isEmpty()) return false;
            
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
}
