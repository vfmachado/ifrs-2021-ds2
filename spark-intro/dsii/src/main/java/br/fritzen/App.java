package br.fritzen;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import br.fritzen.user.User;
import br.fritzen.user.UserController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {

    //static String lastname;

    public static void main( String[] args ) throws Exception {
        /*
        get("/hello", (req, res) -> "<h1>TESTE</h1> <h2>" + lastname + "</h2>");
        
        get("/user/:name", (req, res) -> "Rota /hello/:name NAME: " + req.params(":name"));

        post("/user", (req, res) -> {

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
        staticFiles.location("/public");

        post("/user", (req, res) -> UserController.create(req, res), new HandlebarsTemplateEngine());
        get("/user",  (req, res) -> UserController.list(req, res), new HandlebarsTemplateEngine());

        Map model = new HashMap();
        model.put("message", "Minha mensagem");
        get("/template", (rq, rs) -> new ModelAndView(model, "hello.hbs"), new HandlebarsTemplateEngine());
    
        get("/outra", (req, res) -> "OK");
    }
}
