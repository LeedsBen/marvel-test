package com.example.marveltest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Config - basic component scan at root package
 *
 * @author Ben Abramson
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.example.marveltest")
public class MarvelTestConfig {
}
