package com.projectalchemy.util;

import java.net.URL;

public class UrlValidator {

    public static boolean isValid(String url) {
        /* Try creating a valid URL */

        if(url.contains("article")) {
            return true;
        }else {
            return false;
        }
    }
}
