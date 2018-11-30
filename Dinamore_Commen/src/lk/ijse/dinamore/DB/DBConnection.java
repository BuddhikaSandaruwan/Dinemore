/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.dinamore.DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Buddhika
 */
public class DBConnection {
    private Connection connection;
    private static DBConnection dBConnection;

    private DBConnection() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        File file=new File("/lk/ijse/dinamore/DB/application.properties");
        FileReader fileReader=new FileReader(file);
        
        Properties properties=new Properties();
        properties.load(fileReader);
        
        String ip=properties.getProperty("ip");
        String port=properties.getProperty("port");
        String db=properties.getProperty("db");
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        
        String jdbcUrl="jdbc:mysql://"+ip+":"+port+"/"+db;
        connection=DriverManager.getConnection(jdbcUrl,user,password);
        
    }
    public static DBConnection getInstance() throws ClassNotFoundException, IOException, FileNotFoundException, SQLException{
        if(dBConnection==null){
         dBConnection=new DBConnection();
        }
        return dBConnection;  
    }
    public Connection getConnection(){
        return connection;
    }
}
