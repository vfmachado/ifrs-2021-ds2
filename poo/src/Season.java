public class Season {
  
  private String name;
  private int numberOfEpisodes;

  public Season(String name, int numberOfEpisodes) {
    this.name = name;
    this.numberOfEpisodes = numberOfEpisodes;
  }

  public String getName() {
    return name;
  }

  public int getNumberOfEpisodes() {
    return numberOfEpisodes;
  }

}
