package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Character {
    private Artist artist;
    private String characterName;

    public Character(Artist artist, String characterName) {
        this.artist = artist;
        this.characterName = characterName;
    }


    public Artist getArtist() {
        return artist;
    }

    public String getCharacterName() {
        return characterName;
    }
}
