package com.revature.p0.models;

public class Item {
    private int Id;
    private String Name;
    private String description;
    private String rarity;
    private double value;

    public Item(){

    }

    public Item(int id, String name, String description, String rarity, double value) {
        Id = id;
        Name = name;
        this.description = description;
        this.rarity = rarity;
        this.value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("Id=").append(Id);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", rarity='").append(rarity).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
    
}
