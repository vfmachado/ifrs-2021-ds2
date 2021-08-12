package fritzen.images;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fritzen.config.Database;

public class ImageDao {
  
  public void add(String filename, int userId) {

    Connection con = Database.getConnection();

    try {

      String sql = "INSERT INTO images (filename, user_id) VALUES (?, ?);";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, filename);
      ps.setInt(2, userId);
      ps.execute();

    } catch (SQLException e) {
      System.out.println("Erro ao cadastrar a imagem");
      e.printStackTrace();
    }

  }

}
