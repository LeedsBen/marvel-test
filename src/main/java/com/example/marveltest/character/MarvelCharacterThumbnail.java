package com.example.marveltest.character;

/**
 * POJO for Thumbnail data from Marvel API
 *
 * @author Ben Abramson
 */
public class MarvelCharacterThumbnail {

    /**
     * Path to Marvel thumbnail image
     */
    private String path;

    /**
     * Image filetype (e.g. jpg, png)
     */
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
