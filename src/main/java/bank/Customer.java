package bank;

public class Customer {
    private int id;
    private String name;
    private String username;
    private String password;
    private int accountId;
    private boolean authenticated;

    public Customer (int d, String n, String u, String pwd, int aId) {
      this.setId(d);
      this.setName(n);
      this.setUsername(u);
      this.setPassword(pwd);
      this.setAccountId(aId);
      this.setAuthenticated(false);
    }

    public void setId(int i) {
      this.id = i;
    }

    public void setName(String n) {
      this.name = n;
    }

    public void setUsername(String u) {
      this.username = u;
    }

    public void setPassword(String p) {
      this.password = p;
    }

    public void setAccountId(int ic) {
      this.accountId = ic;
    }

    public void setAuthenticated(boolean auth) {
      this.authenticated = auth;
    }

    public int getId() {
      return this.id;
    }

    public String getName() {
      return this.name;
    }

    public String getUsername() {
      return this.username;
    }

    public String getPassword() {
      return this.password;
    }

    public int getAccountId() {
      return this.accountId;
    }

    public boolean isAuthenticated() {
      return this.authenticated;
    }
}