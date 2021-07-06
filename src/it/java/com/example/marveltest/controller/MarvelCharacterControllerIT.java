package com.example.marveltest.controller;

import com.example.marveltest.MarvelTestApp;
import com.example.marveltest.character.MarvelCharacter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for REST Controller
 *
 * Spins up Spring Context & fires in HTTP requests
 *
 * @author Ben Abramson
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MarvelTestApp.class)
@DisplayName("Marvel Character Controller Integration Test")
public class MarvelCharacterControllerIT {

    @LocalServerPort
    private int localServerPort;

    /**
     * Simple test to check Spring Context loads
     */
    @Test
    @DisplayName("Basic test to check the Spring Context")
    public void basicSpringContextTest() {
        assertTrue(true);
    }

    /**
     * Proper test to hit HTTP endpoints
     */
    @Test
    @DisplayName("Full Controller Test")
    public void marvelCharacterIT() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String url = "http://localhost:" + localServerPort;

        // get characters
        UriComponentsBuilder getCharacters = UriComponentsBuilder.fromHttpUrl(url + "/characters");
        ResponseEntity<Integer[]> getCharactersResponse = restTemplate.getForEntity(getCharacters.build().toUri(), Integer[].class);
        assertEquals(HttpStatus.FOUND, getCharactersResponse.getStatusCode());
        assertTrue(getCharactersResponse.getBody().length > 0);

        // get a specific character
        Integer characterId = getCharactersResponse.getBody()[0];
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());
        UriComponentsBuilder uriGetCharacter = UriComponentsBuilder.fromHttpUrl(url + "/characters/" + characterId);
        ResponseEntity<MarvelCharacter> getCharacterResponse = restTemplate.exchange(uriGetCharacter.build().toUri(), HttpMethod.GET, entity, MarvelCharacter.class);
        assertEquals(302, getCharacterResponse.getStatusCodeValue());
        assertEquals(getCharactersResponse.getBody()[0], getCharacterResponse.getBody().getId());

        // Try with bad ID
        HttpEntity<String> entity2 = new HttpEntity<String>(null, new HttpHeaders());
        UriComponentsBuilder uriGetBadCharacter = UriComponentsBuilder.fromHttpUrl(url + "/characters/-1");
        ResponseEntity<String> getBadCharacterResponse = restTemplate.exchange(uriGetBadCharacter.build().toUri(), HttpMethod.GET, entity2, String.class);
        assertEquals(404, getBadCharacterResponse.getStatusCodeValue());
        assertEquals("No character found for ID: -1", getBadCharacterResponse.getBody());
    }
}
