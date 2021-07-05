package com.example.marveltest.exception;

/**
 * Simple exception wrapper for interceptor
 *
 * @author Ben Abramson
 */
public class MarvelCharacterNotFoundException extends RuntimeException {

    public MarvelCharacterNotFoundException(String msg) { super(msg); }
}
