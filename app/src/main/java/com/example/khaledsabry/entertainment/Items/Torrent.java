package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class Torrent {
    private String title;
    private String magnet;
    private int size;
    private int seed;
    private int leach;
    private String quailty;
    private String sourceName;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getLeach() {
        return leach;
    }

    public void setLeach(int leach) {
        this.leach = leach;
    }

    public String getQuailty() {
        return quailty;
    }

    public void setQuailty(String quailty) {
        this.quailty = quailty;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
