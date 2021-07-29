package br.fritzen;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import br.fritzen.config.Database;
import br.fritzen.user.User;
import br.fritzen.user.UserController;
import br.fritzen.user.UserDao;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

/*
    mvn -U clean install    => forcar a instalacao dos repos do maven
*/

public class App {

    // static String lastname;

    public static void main( String[] args ) throws Exception {
        /*
        get("/hello", (req, res) -> "<h1>TESTE</h1> <h2>" + lastname + "</h2>");
        
        get("/user/:name", (req, res) -> "Rota /hello/:name NAME: " + req.params(":name"));

        post("/user", (req, res) -> {
            aqui eu tentei fazer tal coisa...
            
            System.out.println(req.body());
            lastname = req.body();
            return "OK";
        });
        */

        /*
         * GET /    => TELA COM UM BOTAO / FORM PARA INSERIR USUARIO (ARQUIVO ESTATICO)
         * GET /USER => LISTA DE USUARIOS
         * POST /USER => INSIRA UM NOVO USUARIO 
         * 
         */

       // Database.getConnection();

        staticFiles.location("/public");

        post("/session", (req, res) -> {
            
            System.out.println("QUERY email: " + req.queryParams("email"));
            System.out.println("QUERY password: " + req.queryParams("password"));

            String email = req.queryParams("email");
            String password = req.queryParams("password");

            UserDao userDao = new UserDao();

            try {
                User user = userDao.getByEmailAndPass(email, password);

                if (user == null) {
                    throw new Exception("Email and Password not match");
                }
                req.session().attribute("user_name", user.getName());
                req.session().attribute("user", user);
                return "ok...";

            } catch (Exception error) {
                return error.getMessage();
            }
        });

        get("/session", (req, res) -> {
            User userAttr = req.session().attribute("user");
            return "NAME: " + req.session().attribute("user_name") + "\nUSER ATTR: " + userAttr + "\n";
        });


        get("/logout", (req, res) -> {
            req.session().invalidate();
            return "ok...";
        });

        get("/user/:name", UserController.detail);
        post("/user", (req, res) -> UserController.create(req, res), new HandlebarsTemplateEngine());
        get("/user",  (req, res) -> UserController.list(req, res), new HandlebarsTemplateEngine());

        Map model = new HashMap();
        model.put("message", "Minha mensagem");
        get("/template", (rq, rs) -> new ModelAndView(model, "hello.hbs"), new HandlebarsTemplateEngine());
    
        get("/outra", (req, res) -> "OK");
    }
}
