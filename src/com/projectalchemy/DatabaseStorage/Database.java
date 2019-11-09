package com.projectalchemy.DatabaseStorage;

import com.projectalchemy.models.Article;

public interface Database {
    public void StoreData(Article article);
}
