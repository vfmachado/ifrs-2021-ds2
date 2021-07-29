package fritzen;

import static spark.Spark.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fritzen.auth.AuthController;
import fritzen.config.Database;


public class App 
{
    public static void main( String[] args )
    {

        staticFiles.location("/public");

        post("/entrar", AuthController.validar);

        get("/", (req, res) -> "Hello");


        get("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/login.html");
            return null;
        });

        get("/protected", (req, res) -> {
            Boolean login = req.session().attribute("login");
            
            if (login != null && login == true)
                return "Acessível apenas logando, logado:  " + req.session().attribute("login");

            res.header("Location", "/login.html");
            res.redirect("/login.html");
            return null;
        });


        post("/adicionar", (req, res) -> {
            
            Boolean login = req.session().attribute("login");
            
            if (login == null || login == false) {
                res.redirect("/login.html");
                return "";
            }
                

            int userId = req.session().attribute("user_id");
            String msg = req.queryParams("msg");


            Connection con = Database.getConnection();

            try {
        
              String sql = "INSERT INTO posts (user_id, msg) VALUES (?, ?);";
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setInt(1, userId);
              ps.setString(2, msg);
              ps.execute();
        
              return "Tudo certo...";

            } catch (SQLException e) {
              System.out.println("Erro ao cadastrar usuário");
              e.printStackTrace();

              return "ooooppss";
            }
            

        });

    }
}
