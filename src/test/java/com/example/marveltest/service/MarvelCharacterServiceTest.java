package com.example.marveltest.service;


import com.example.marveltest.character.MarvelCharacter;
import com.example.marveltest.character.MarvelCharacterDataWrapper;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Marvel Character Service
 *
 * @author Ben Abramson
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarvelCharacterServiceTest {

    private MarvelCharacterService mcs = new MarvelCharacterService();

    /**
     * Basic test to create new service, populate characters and check the right number have loaded
     */
    @Test
    @Order(1)
    public void simpleCharacterServiceTest() {
        MarvelCharacterDataWrapper marvelCharacter = mcs.getCharactersFromMarvelApi(0,0);
        int totalCharacters = marvelCharacter.getData().getTotal().intValue();
        assertTrue(totalCharacters > 0);
        // Should be equal unless characters have changed during test!
        assertEquals(totalCharacters, mcs.getCharacterIds().size());
    }

    /**
     * Test map to ensure right characters are in
     */
    @Test
    @Order(2)
    public void checkCharactersTest() {
        MarvelCharacter benParker = mcs.getCharacterById(1009489);
        assertEquals("Ben Parker", benParker.getName());
        MarvelCharacter elektra = mcs.getCharacterById(1009288);
        assertEquals("Elektra", elektra.getName());
        MarvelCharacter jessicaJones = mcs.getCharacterById(1009378);
        assertEquals("Jessica Jones", jessicaJones.getName());
    }
}
