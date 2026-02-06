package model;

public abstract class User {
    protected String username;
    protected String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean logIn(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public void logOut() {
        
    }
    
     public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
