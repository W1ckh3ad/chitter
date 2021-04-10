package de.fhdw.chitter.models;

public class Staff {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
