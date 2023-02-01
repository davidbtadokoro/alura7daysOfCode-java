package com.github.davidbtadokoro.api_consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.davidbtadokoro.api_consumer.model.Comic;
import com.github.davidbtadokoro.api_consumer.service.MarvelComicsJsonParser;

public class MarvelComicsJsonParserTest {
  
  private MarvelComicsJsonParser comicsJsonParser;
  private String comicsJsonSample;

  @BeforeEach
  void setUp() throws IOException {
    Path comicsSamplePath = Path.of("src/test/resources/json/comicsSample.json");
    comicsJsonSample = Files.readString(comicsSamplePath);
    comicsJsonParser = new MarvelComicsJsonParser(comicsJsonSample);
  }

  @Test
  void parseShouldReturnCorrectNumberOfMovies() {
    List<Comic> comicsSample = comicsJsonParser.parse();

    assertEquals(3, comicsSample.size());
  }

  @Test
  void parseShouldCorrectlyDesserializeAComic() {
    List<Comic> comicsSample = comicsJsonParser.parse();

    Comic comicSample1 = comicsSample.get(0);
    assertEquals("Great Comic (2020) #1", comicSample1.getTitle());
    assertEquals("2020", comicSample1.getYear());
    assertEquals("anUrl1.jpg", comicSample1.getUrlImage());

    Comic comicSample2 = comicsSample.get(1);
    assertEquals("Good Comic (2021) #2", comicSample2.getTitle());
    assertEquals("2021", comicSample2.getYear());
    assertEquals("anUrl2.jpg", comicSample2.getUrlImage());

    Comic comicSample3 = comicsSample.get(2);
    assertEquals("Bad Comic (2022) #3", comicSample3.getTitle());
    assertEquals("2022", comicSample3.getYear());
    assertEquals("anUrl3.jpg", comicSample3.getUrlImage());
  }

}
