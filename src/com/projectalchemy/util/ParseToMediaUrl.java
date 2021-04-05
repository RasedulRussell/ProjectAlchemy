package com.projectalchemy.util;

public class ParseToMediaUrl {
    public static String parseToMediaURL(String url) {
        if(url.length() == 0) {
            return null;
        }
        int i;
        for(i = 0; i < url.length() - 5; i++) {
            String hello = url.substring(i, i + 5 - 1);
            if(hello.equals("http")) break;
        }
        int j = i;
        while(i < url.length()-1 && url.charAt(i+1) != '"') {
            i++;
        }
        return url.substring(j, i);
    }
}
