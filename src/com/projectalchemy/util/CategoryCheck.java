package com.projectalchemy.util;

import java.util.ArrayList;

public class CategoryCheck {
    private static ArrayList<String> categoryList = new ArrayList<String>() {
        {
            add("bangladesh");
            add("sport");
            add("politics");
            add("economics");
            add("international");
            add("entertainment");
            add("lifestyle");
            add("education");
            add("technology");
            add("sahitto-o-sangskriti");
        }
    };

    public static String categoryCheck(String url) {
        for(String category : categoryList) {
            if(url.contains(category)) {
                return category;
            }
        }
        return "other";
    }
}
