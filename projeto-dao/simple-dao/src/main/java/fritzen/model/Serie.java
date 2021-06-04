package fritzen.model;

import java.time.LocalDate;
import java.util.List;

public class Serie {

  private int id;
  private String name;
  private LocalDate releaseDate;
  private String description;

  private List<Season> seasons;

  public Serie(String name, LocalDate releaseDate) {
    this.name = name;
    this.releaseDate = releaseDate;
  }

  public String getName() {
    return name;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public List<Season> getSeasons() {
    return seasons;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.id + "\t" + this.name + "\t" + this.description + "\t" + this.releaseDate;
  }
}
