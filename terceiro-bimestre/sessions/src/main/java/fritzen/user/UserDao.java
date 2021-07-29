package fritzen.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fritzen.config.Database;

public class UserDao {

  public void add(User user) {

    Connection con = Database.getConnection();

    try {

      String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPassword());
      ps.execute();

    } catch (SQLException e) {
      System.out.println("Erro ao cadastrar usuário");
      e.printStackTrace();
    }

  }

  public List<User> getUsers() {

    List<User> users = new ArrayList<User>();

    Connection con = Database.getConnection();

    try {

      String sql = "SELECT * FROM users;";
      ResultSet rs = con.prepareStatement(sql).executeQuery();

      while (rs.next()) {
        User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
        users.add(user);
      }

    } catch (SQLException e) {
      System.out.println("Erro ao buscar os usuários");
      e.printStackTrace();
    }

    return users;
  }

  public User getByName(String name) {

    Connection con = Database.getConnection();
    User user = null;
    try {

      String sql = "SELECT * FROM users where name = ?;";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, name);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
      }

    } catch (SQLException e) {
      System.out.println("Erro ao buscar os usuários");
      e.printStackTrace();
    }

    return user;
  }

  public User getByEmailAndPass(String email, String password) {
    Connection con = Database.getConnection();
    User user = null;
    
    try {
      String sql = "SELECT * FROM users where email = ? and password = ?;";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, email);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
        user.setId(rs.getInt("id"));
      }
      
    } catch (SQLException e) {
      System.out.println("Erro ao buscar os usuários");
      e.printStackTrace();
    }

    return user;
  }

}
