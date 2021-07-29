package fritzen.user;

public class User {
  
  private int id;
  private String name;
  private String email;
  private String password;

  public User(String name, String email, String pass) {
    this.name = name;
    this.email = email;
    this.password = pass;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
