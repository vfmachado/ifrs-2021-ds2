import java.util.ArrayList;

public class Serie {
  
  private String name;

  private ArrayList<Season> seasons;

  public Serie(String name) {
    this.name = name; //O NAME DO OBJETO RECEBE O NAME PASSADO POR PARAMETRO
    this.seasons = new ArrayList<Season>();
  }

  public String getName() {
    return name;
  }

  public ArrayList<Season> getSeasons() {
    return seasons;
  }

  public void addSeason(Season season) {
    this.seasons.add(season);
  }

  @Override
  public String toString() {
    String resultado = "";

    resultado += "Name: " + this.name;
    resultado += "\nSeasons:";

    for (int i = 0; i < seasons.size(); i++) {
      resultado += "\nSeason Name: " + seasons.get(i).getName() + " - eps: " + seasons.get(i).getNumberOfEpisodes();
    }

    return resultado;
  }
}
