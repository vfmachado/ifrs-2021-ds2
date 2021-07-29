package fritzen.auth;

import fritzen.user.User;
import fritzen.user.UserDao;
import spark.Route;

public class AuthController {
  
  public static Route validar = (req, res) -> {

    String email = req.queryParams("email");
    String password = req.queryParams("password");

    UserDao userDao = new UserDao();

    try {
      User user = userDao.getByEmailAndPass(email, password);
      
      if (user == null) {
        throw new Exception();
      }

      req.session(true);
      req.session().attribute("name", user.getName());
      req.session().attribute("email", user.getEmail());
      req.session().attribute("user_id", user.getId());
      req.session().attribute("login", true);

      return "ok...";

    } catch (Exception e) {
      return "Usuario " + req.queryParams("email") + " e senha não conferem";
    }


  };

}
