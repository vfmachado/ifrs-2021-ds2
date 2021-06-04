package br.vini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vini.model.Receita;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Trying to connect..." );
        String dbUrl = "jdbc:postgresql://" 
        + "ec2-54-87-112-29.compute-1.amazonaws.com" + ":"  //HOST
        + "5432"                                            //PORTA
        + "/d91ke5rqnh4lap";                                //BANCO
        
        String username = "vdgtptbmvzapgd";
        String password = "86e93d6b4abd0764bc0cf1f5f5f1effdf5c600852628910145506ce975782ed8";

        List<Receita> receitas = new ArrayList<Receita>();

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);) {
            // Code here.
            System.out.println("CONNECTED!!!");


            ResultSet result = connection.prepareStatement("select * from receitas order by id").executeQuery();
            while (result.next()) {
                //System.out.println(result.getString("nome") + " - SERVE " + result.getInt("porcoes") + " pessoa(s)");
                receitas.add(new Receita(result.getString("nome"), result.getInt("porcoes")));
            }


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

        //A CONEXÃO JÁ NÃO EXISTE MAIS AQUI
        for (Receita receita : receitas) {
            System.out.println(receita);
        }

    }
}
