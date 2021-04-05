package com.projectalchemy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CategoryCheck {
    private static final HashMap<String, ArrayList<String>> categoryList = new HashMap<>() {
        {
            put("sports", new ArrayList<>(Arrays.asList("sport", "cricket", "football","sports")));
            put("bangladesh", new ArrayList<>(Arrays.asList("samagrabangladesh", "bangladesh", "country-new", "national", "whole-country")));
            put("world", new ArrayList<>(Arrays.asList("world", "international")));
            put("entertainment", new ArrayList<>(Arrays.asList("entertainment", "movie", "music")));
            put("technology", new ArrayList<>(Arrays.asList("technology", "info-tech", "tech-everyday")));
            put("business", new ArrayList<>(Arrays.asList("economics", "business", "industry-business", "industry-trade")));
            put("lifestyle", new ArrayList<>(Collections.singletonList("lifestyle")));
            put("education", new ArrayList<>(Collections.singletonList("education")));
            put("politics", new ArrayList<>(Collections.singletonList("politics")));
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
        return "others";
    }
}
