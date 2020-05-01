package com.example.l09_t05;

public class Smart {
    private int id;
    private int place_id;
    private String name;
    private String city;
    private String address;
    private String country;
    private int postalcode;
    private String availability;


    protected Smart(int id, int place_id, String name, String city, String address, String country, int postalcode, String availability) {
        this.id = id;
        this.place_id = place_id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.country = country;
        this.postalcode = postalcode;
        this.availability = availability;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPlace_id() { return place_id; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getCountry() {return country; }
    public int getPostalcode() { return postalcode; }
    public String getAvailability() { return availability; }

    @Override
    public String toString() {
        return name;
    }
}
