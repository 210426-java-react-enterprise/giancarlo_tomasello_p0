package com.revature.p0.models;

public class AppUser {
    private String Username;
    private String Password;
    private String email;
    private String firstName;
    private String lastName;
    private double goldPieces;
    private int dragonShards;

    public AppUser(String username, String password, String email, String firstName, String lastName, double goldPieces, int dragonShards) {
        Username = username;
        Password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.goldPieces = goldPieces;
        this.dragonShards = dragonShards;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getGoldPieces() {
        return goldPieces;
    }

    public void setGoldPieces(double goldPieces) {
        this.goldPieces = goldPieces;
    }

    public int getDragonShards() {
        return dragonShards;
    }

    public void setDragonShards(int dragonShards) {
        this.dragonShards = dragonShards;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("Username='").append(Username).append('\'');
        sb.append(", Password='").append(Password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", goldPieces=").append(goldPieces);
        sb.append(", dragonShards=").append(dragonShards);
        sb.append('}');
        return sb.toString();
    }
}
