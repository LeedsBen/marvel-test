# marvel-test

This application is a proxy API for Marvel Characters.

There are two JSON over REST endpoints:
 - '/characters' which returns an array of Marvel Character IDs
 - '/characters/{id}' which returns a short profile of character with the specified Marvel ID
 
 This application requires a Marvel API Key. It is currently configured with the author's public key and the private key has been redacted.
 
 In order to run the application successfully, you will need to add in your own public & private keys in `com.example.marveltest.service.MarvelCharacterService`
 
 This is a Spring Boot application that builds with Maven, and is stored in Git
 
 In order to build the application, run `git clone` on the repository, and then run `mvn clean install`. You will need a git client and Maven installed.
 
 To run the application, build it first and then execute `java -jar target/marvel-test-1.0-SNAPSHOT.jar`
 
 Once running, you can hit the application (with curl, postman or in a browser) at `http://localhost:8080/`
 
 See JavaDoc for more detail. 