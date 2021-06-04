package fritzen.model;

public class Season {

  private String name;
  private int quantEps;

  public Season() {

  }

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantEps(int quantEps) {
    this.quantEps = quantEps;
  }

  public String getName() {
    return name;
  }

  public int getQuantEps() {
    return quantEps;
  }

  @Override
  public String toString() {
    return this.name + "\t" + this.quantEps;
  }
}