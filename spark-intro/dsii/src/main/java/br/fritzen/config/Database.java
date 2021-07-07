package br.fritzen.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  
  private static Connection connection = null;

  public static Connection getConnection() {

    if (connection == null) {

      String dbUrl = "jdbc:postgresql://" 
        + "ec2-54-87-112-29.compute-1.amazonaws.com" + ":"  //HOST
        + "5432"                                            //PORTA
        + "/d91ke5rqnh4lap";                                //BANCO
        
      String username = "vdgtptbmvzapgd";
      String password = "86e93d6b4abd0764bc0cf1f5f5f1effdf5c600852628910145506ce975782ed8";

      try {
        connection = DriverManager.getConnection(dbUrl, username, password);
        System.out.println("Conectado...");
      } catch (SQLException error) {
        System.out.println("Nao foi possivel conectar ao banco");
        error.printStackTrace();
      }
    }

    return connection;

  }

}
