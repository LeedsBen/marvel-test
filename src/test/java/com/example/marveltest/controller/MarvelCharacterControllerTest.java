package com.example.marveltest.controller;

import com.example.marveltest.character.MarvelCharacter;
import com.example.marveltest.service.MarvelCharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.notNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Simple unit test for the REST controller
 *
 * @author Ben Abramson
 */
@ExtendWith(MockitoExtension.class)
public class MarvelCharacterControllerTest {

    @Mock
    private MarvelCharacterService marvelCharacterService;

    /**
     * Test for MarvelCharacterController#characters
     */
    @Test
    public void marvelCharacterControllerCharacterIdsTest() {
        when(marvelCharacterService.getCharacterIds()).thenReturn(new HashSet<Integer>());
        MarvelCharacterController marvelCharacterController = new MarvelCharacterController(marvelCharacterService);
        ResponseEntity<Integer[]> characterIds = marvelCharacterController.characters();
        assertEquals(HttpStatus.FOUND, characterIds.getStatusCode());
    }

    /**
     * Test for MarvelCharacterController#charactersById
     */
    @Test
    public void marvelCharacterControllerCharacterByIdTest() {
        when(marvelCharacterService.getCharacterById(any())).thenReturn(createCharacter());
        MarvelCharacterController marvelCharacterController = new MarvelCharacterController(marvelCharacterService);
        ResponseEntity<MarvelCharacter> character = marvelCharacterController.charactersById(100);
        assertEquals(HttpStatus.FOUND, character.getStatusCode());
        assertEquals("Mr Test", character.getBody().getName());
    }

    private MarvelCharacter createCharacter() {
        MarvelCharacter marvelCharacter = new MarvelCharacter();
        marvelCharacter.setId(1);
        marvelCharacter.setName("Mr Test");
        marvelCharacter.setDescription("Test Superhero");
        return marvelCharacter;
    }
}
