package com.projectalchemy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CategoryCheck {
    private static HashMap<String, ArrayList<String>> categoryList = new HashMap<>() {
        {
            put("sports", new ArrayList<String>(Arrays.asList("sport", "cricket", "football","sports")));
            put("bangladesh", new ArrayList<String>(Arrays.asList("samagrabangladesh", "bangladesh", "country-new", "national", "whole-country")));
            put("international", new ArrayList<String>(Arrays.asList("world", "international")));
            put("entertainment", new ArrayList<>(Arrays.asList("entertainment", "movie", "music")));
            put("technology", new ArrayList<>(Arrays.asList("technology", "info-tech", "tech-everyday")));
            put("economics", new ArrayList<String>(Arrays.asList("economics", "business", "industry-business", "industry-trade")));
            put("lifestyle", new ArrayList<String>(Arrays.asList("lifestyle")));
            put("education", new ArrayList<String>(Arrays.asList("education")));
        }
    };

    public static String categoryCheck(String url) {
        for(var category : categoryList.entrySet()) {
            String mainCategory = category.getKey();
            ArrayList<String> list = category.getValue();
            for(var str : list) {
                if(url.contains(str)) {
                    return mainCategory;
                }
            }
        }
        return "other";
    }
}
