package com.example.marveltest.character;

import java.util.Date;

/**
 * POJO to represent a Marvel Character as returned by the Marvel API
 *
 * @author Ben Abramson
 */
public class MarvelCharacter {

    /**
     * Marvel Character ID
     */
    private Integer id;

    /**
     * Marvel Character Name
     */
    private String name;

    /**
     * Marvel Thumbnail data
     */
    private MarvelCharacterThumbnail thumbnail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarvelCharacterThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MarvelCharacterThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
