package com.example.marveltest.controller;

import com.example.marveltest.character.MarvelCharacter;
import com.example.marveltest.service.MarvelCharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * HTTP REST Controller to model HTTP operations
 */
@RestController
public class MarvelCharacterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarvelCharacterController.class);

    /**
     * Marvel Character Service
     */
    private MarvelCharacterService marvelCharacterService;

    /**
     * Constructor for Spring bean wiring
     *
     * @param marvelCharacterService
     */
    public MarvelCharacterController(MarvelCharacterService marvelCharacterService) {
        this.marvelCharacterService = marvelCharacterService;
    }

    /**
     * @see MarvelCharacterService#getCharacterIds()
     *
     */
    @GetMapping("/characters")
    public ResponseEntity<Integer[]> characters() {
        Set<Integer> ids = marvelCharacterService.getCharacterIds();
        return new ResponseEntity<Integer[]>(ids.toArray(new Integer[ids.size()]), HttpStatus.FOUND);
    }

    /**
     * @see MarvelCharacterService#getCharacterById(Integer)
     */
    @GetMapping("/characters/{id}")
    public ResponseEntity<MarvelCharacter> charactersById(@PathVariable Integer id) {
        MarvelCharacter marvelCharacter = marvelCharacterService.getCharacterById(id);
        return new ResponseEntity<MarvelCharacter>(marvelCharacter, HttpStatus.FOUND);
    }
}
