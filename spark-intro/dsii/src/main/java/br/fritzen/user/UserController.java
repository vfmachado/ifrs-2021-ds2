package br.fritzen.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {
  
  static List<User> users = new ArrayList<User>();

  public static ModelAndView create(Request req, Response res) {
    
    //System.out.println("BODY: " + req.body());

    String name = req.queryParams("name");
    String email = req.queryParams("email");

    User user = new User(name, email);
    users.add(user);

    Map<String, String> model = new HashMap();
    model.put("name", user.getName());
    model.put("email", user.getEmail());

    return new ModelAndView(model, "createUser.hbs");
  };


  public static ModelAndView list(Request req, Response res) {
    
    Map<String, Object> model = new HashMap<>();
    model.put("users", users);
    return new ModelAndView(model, "listUsers.hbs");

  };


}
