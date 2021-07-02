package com.example.marveltest.character;

/**
 * POJO for Marvel API Character endpoint response
 *
 * Represents what comes back from Marvel API endpoint /v1/public/characters
 *
 * See:
 * https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0
 *
 * For field details
 *
 * @author Ben Abramson
 */
public class MarvelCharacterDataWrapper {

    private Integer code;

    private String status;

    private String copyright;

    private String attributionText;

    private String attributionHTML;

    private String etag;

    private MarvelCharacterDataContainer data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public MarvelCharacterDataContainer getData() {
        return data;
    }

    public void setData(MarvelCharacterDataContainer data) {
        this.data = data;
    }
}
