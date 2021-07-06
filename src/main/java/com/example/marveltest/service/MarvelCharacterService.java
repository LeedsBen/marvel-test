package com.example.marveltest.service;

import com.example.marveltest.character.MarvelCharacter;
import com.example.marveltest.character.MarvelCharacterDataWrapper;
import com.example.marveltest.exception.MarvelCharacterNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Service Class for Marvel Character Application
 *
 * Manages the under-the-hood communication with main Marvel API
 *
 * @author Ben Abramson
 */
@Service
public class MarvelCharacterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarvelCharacterService.class);

    /**
     * In memory map of characters keyed by Marvel ID
     */
    private Map<Integer, MarvelCharacter> marvelCharacters;

    /**
     * Up-to-date view of the total number of characters
     */
    private int characterTotal = 0;

    /**
     * Marvel API Key (for my account!) - replace this with your own public key
     */
    private String marvelApiPublicKey = "aff106a14b70b7021658807153e0a0c2";

    /**
     * Public Constructor - instantiates Character Map and populates
     */
    public MarvelCharacterService() {
        marvelCharacters = new HashMap<>();
        checkCharacters();
    }

    /**
     * Method to check if the Map of characters is complete - call first character and look at character total
     *
     * Runs on class construction and a schedule of every 10 mins
     */
    @Scheduled(fixedDelay = 600000, initialDelay = 90000)
    private void checkCharacters() {
        LOGGER.info("Checking character list");
        Integer totalNumberOfMarvelCharacters = getCharactersFromMarvelApi(1, 0).getData().getTotal();
        if (totalNumberOfMarvelCharacters == null) {
            LOGGER.error("Unable to get character total from Marvel API");
            return;
        }
        characterTotal = totalNumberOfMarvelCharacters;
        LOGGER.info("Character total: " + characterTotal);

        if (totalNumberOfMarvelCharacters > marvelCharacters.size()) {
            populateCharacters();
        }
    }

    /**
     * Method to populate the map of Marvel Characters
     *
     * Hits API and gets Marvel Characters in batches of 100
     *
     */
    private void populateCharacters() {
        LOGGER.info("Populating Full Character set");
        int offset = 0;
        int limit = 100;
        while(marvelCharacters.size() < characterTotal) {
            // TODO: Future enhancement, lock map while doing this!
            getCharactersFromMarvelApi(limit, offset).getData().getResults().stream().forEach(marvelCharacter ->  {
                marvelCharacters.put(marvelCharacter.getId(), marvelCharacter);
            });
            offset += limit;
        }
        LOGGER.info("Character set size: " + marvelCharacters.size());
    }

    /**
     * Method to call Marvel API endpoint v1/public/characters and add to the map
     *
     * @param offset - offset to pass to Marvel API
     * @param limit - number of records to return
     */
    protected MarvelCharacterDataWrapper getCharactersFromMarvelApi(int limit, int offset) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gateway.marvel.com:443/v1/public/characters";

        // Hash fields
        String timestamp = String.valueOf(System.currentTimeMillis());

        // Hit Marvel Endpoint and get characters
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());
        UriComponentsBuilder uriGetCharacters = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ts", timestamp)
                .queryParam("apikey", marvelApiPublicKey)
                .queryParam("hash", generateHash(timestamp))
                .queryParam("limit", 100)
                .queryParam("offset", offset);
        LOGGER.debug("Calling Marvel get character API with offset of: " + offset);
        return restTemplate.exchange(uriGetCharacters.build().toUri(), HttpMethod.GET, entity, MarvelCharacterDataWrapper.class).getBody();
    }

    /**
     * Method to create hash to pass to Marvel API
     *
     * @param timestamp
     * @return hash of string 'timestamp + private key + public key'
     */
    private String generateHash(String timestamp) {
        /************************************
         * Replace this with your own key ! *
         ************************************/
        String hashme = timestamp + "my-private-key-go-get-your-own!" + marvelApiPublicKey;
        return DigestUtils.md5DigestAsHex(hashme.getBytes());
    }

    /**
     * Method to return all IDs of all Marvel Characters
     *
     * @return set of Integer IDs
     */
    public Set<Integer> getCharacterIds() {
        return marvelCharacters.keySet();
    }

    /**
     * Method to return specified Character information by Marvel Character ID
     *
     * @param id - Marvel ID for the character
     * @return information about the character
     * @throws MarvelCharacterNotFoundException if character ID is not in map
     */
    public MarvelCharacter getCharacterById(Integer id) throws MarvelCharacterNotFoundException {
        MarvelCharacter marvelCharacter = marvelCharacters.get(id);
        if (marvelCharacter == null) {
            LOGGER.warn("Character ID not present: " + id);
            throw new MarvelCharacterNotFoundException("No character found for ID: " + id);
        }
        return marvelCharacter;
    }
}
