package com.example.marveltest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Config - basic component scan at root package
 *
 * @author Ben Abramson
 */
@Configuration
@ComponentScan(basePackages = "com.example.marveltest")
public class MarvelTestConfig {
}
