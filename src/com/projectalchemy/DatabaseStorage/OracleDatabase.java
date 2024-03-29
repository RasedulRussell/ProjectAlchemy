package com.projectalchemy.DatabaseStorage;

import com.projectalchemy.models.Article;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OracleDatabase implements Database {

    private Connection connection;

    public OracleDatabase(Connection connection) {
        this.connection = connection;
    }

    public void StoreData(Article article) {
        try {

            PreparedStatement insertStatement = connection.prepareStatement("insert into NEWSPAPER.Article (id, title, rawdetails, details, url) values (?,?,?,?,?)");

            insertStatement.setString(1,article.getId());
            insertStatement.setString(2,article.getTitle());

            Blob blob = connection.createBlob();
            blob.setBytes(3, article.getDetails().getBytes());
            insertStatement.setBlob(3, blob);

            insertStatement.setString(4, article.getDetails());
            insertStatement.setString(5, article.getUrl());
            var isExecuted = insertStatement.execute();

            if(!isExecuted){

            }
            insertStatement.close();

        } catch (Exception exception) {
            if(exception.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")){
                //fetch news
                //check updated
                //
            }
            System.out.println(exception);
        }
    }
}
