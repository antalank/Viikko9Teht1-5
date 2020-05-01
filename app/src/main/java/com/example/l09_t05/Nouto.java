package com.example.l09_t05;

public class Nouto {
    private int place_id;
    private int day;
    private String express_out;

    protected Nouto(int place_id, int day, String express_out) {
        this.place_id = place_id;
        this.day = day;
        this.express_out = express_out;
    }
    public int getPlace_id() { return place_id; }
    public  int getDay() { return  day; }
    public String getExpress_out() { return express_out; }

}
