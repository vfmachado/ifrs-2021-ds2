package fritzen.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import fritzen.database.Database;
import fritzen.model.Season;
import fritzen.model.Serie;

public class SerieDAO {
  
  public List<Serie> getSeries() {
   
    Connection con = Database.getConnection();

    try {
      ResultSet rs = con.prepareStatement("select * from series").executeQuery();

      List<Serie> lista = new ArrayList<Serie>();
      while (rs.next()) {
        Serie serie = new Serie(rs.getString("name"), rs.getDate("releasedate").toLocalDate());
        serie.setId(rs.getInt("id"));
        serie.setDescription(rs.getString("description"));
        lista.add(serie);

        //VERIFICAR NO BANCO SE EXISTEM TEMPORADAS ASSOCIADAS A ESTA SÉRIE E CARREGAR =)
        PreparedStatement ps = con.prepareStatement("select * from seasons where serie_id = ?");
        ps.setInt(1, serie.getId());
        ResultSet rsSeason = ps.executeQuery();

        List<Season> seasons = new ArrayList<Season>();
        while (rsSeason.next()) {
          Season season = new Season();
          season.setName(rsSeason.getString("name"));
          season.setQuantEps(rsSeason.getInt("quantseps"));
          seasons.add(season);
        }
        serie.setSeasons(seasons);
        
      }



      return lista;

    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }


  public void addSerie(Serie serie) {

    Connection con = Database.getConnection();
    try {
      PreparedStatement ps = con.prepareStatement("INSERT INTO series (name, description, releasedate) VALUES (?, ?, ?)");
      ps.setString(1, serie.getName());
      ps.setString(2, serie.getDescription());
      ps.setDate(3, Date.valueOf(serie.getReleaseDate()));
      ps.execute();
    } catch (SQLException e) {
      System.out.println("Erro ao inserir uma série");
      e.printStackTrace();
    }
  }


  public void updateSerie(int id, Serie s) {

  }


  public void deleteById(int id) {
    
  }
}
