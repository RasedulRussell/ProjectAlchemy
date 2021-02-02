
import java.sql.*;

public class Table {
    public void CreatTable(){
        String tableCreate = "create table tableTest(url varchar(1000)not null," +
                "details blob," +
                "type varchar(100)not null)" ;

        Connection connection = OracleConnectionFactory.getConnectionOracle() ;
        try{
            Statement statement = connection.createStatement();
            ////System.out.println("my Table "+ tableCreate);
            statement.execute(tableCreate);
            ///System.out.println("Table created successfully");
            statement.close();
            connection.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
