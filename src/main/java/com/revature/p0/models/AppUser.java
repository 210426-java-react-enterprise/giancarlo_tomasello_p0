package com.revature.p0.models;

import com.revature.p0.util.ArrayList;

public class AppUser {
    private int Id;
    private String Username;
    private String Password;
    private String email;
    private String firstName;
    private String lastName;
    private double goldPieces;
    private int dragonShards;
    private ArrayList<Item> backpack;

    public AppUser(){

        backpack = new ArrayList<>();
    }

    public AppUser(int id, String username, String password, String email, String firstName, String lastName, double goldPieces, int dragonShards) {
        this(username, password, email, firstName, lastName, goldPieces, dragonShards, new ArrayList<>());
        Id = id;
    }

    public AppUser(String username, String password, String email, String firstName, String lastName, double goldPieces,
                   int dragonShards, ArrayList<Item> backpack) {
        Username = username;
        Password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.goldPieces = goldPieces;
        this.dragonShards = dragonShards;
        this.backpack = backpack;
    }

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

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
        sb.append("Id=").append(Id);
        sb.append(", Username='").append(Username).append('\'');
        sb.append(", Password='").append(Password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", goldPieces=").append(goldPieces);
        sb.append(", dragonShards=").append(dragonShards);
        sb.append(", backpack=").append(backpack);
        sb.append('}');
        return sb.toString();
    }

    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    public void setBackpack(ArrayList<Item> backpack) {
        this.backpack = backpack;
    }
}
