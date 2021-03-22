package com.projectalchemy.util;

public class ParseToMediaUrl {
    public static String parseToMediaURL(String str) {
        String absUrlGenerate = "";
        int i = 0;
        while((i < str.length()) && str.charAt(i) != '"') {
            i++;
        }
        i++;
        while((i < str.length()) && str.charAt(i) != '"') {
            absUrlGenerate = absUrlGenerate + str.charAt(i++);
        }
        return absUrlGenerate;
    }
}
