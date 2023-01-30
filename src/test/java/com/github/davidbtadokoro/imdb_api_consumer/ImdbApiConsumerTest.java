package com.github.davidbtadokoro.imdb_api_consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;
import com.github.davidbtadokoro.imdb_api_consumer.service.ImdbApiConsumer;

public class ImdbApiConsumerTest {

  private String apiKey = "fakeApiKey";
  private ImdbApiConsumer imdbApiConsumer;
  private String moviesJsonSample;

  @BeforeEach
  void setUp() throws IOException {
    Path moviesSamplePath = Path.of("src/test/resources/json/moviesSample.json");
    moviesJsonSample = Files.readString(moviesSamplePath);
    imdbApiConsumer = new ImdbApiConsumer(this.apiKey);
  }

  @Test
  void getMoviesListShouldReturnCorrectNumberOfMovies() {
    List<Movie> moviesSample = imdbApiConsumer.getMoviesList(moviesJsonSample);

    assertEquals(3, moviesSample.size());
  }

  @Test
  void getMoviesListShouldCorrectlyDesserializeAMovie() {
    List<Movie> moviesSample = imdbApiConsumer.getMoviesList(moviesJsonSample);

    Movie movieSample1 = moviesSample.get(0);
    assertEquals("Great Movie", movieSample1.getTitle());
    assertEquals("2020", movieSample1.getYear());
    assertEquals("anUrl1", movieSample1.getImage());
    assertEquals("9.9", movieSample1.getImDbRating());

    Movie movieSample2 = moviesSample.get(1);
    assertEquals("Good Movie", movieSample2.getTitle());
    assertEquals("2021", movieSample2.getYear());
    assertEquals("anUrl2", movieSample2.getImage());
    assertEquals("8.0", movieSample2.getImDbRating());

    Movie movieSample3 = moviesSample.get(2);
    assertEquals("Bad Movie", movieSample3.getTitle());
    assertEquals("2022", movieSample3.getYear());
    assertEquals("anUrl3", movieSample3.getImage());
    assertEquals("2.0", movieSample3.getImDbRating());
  }

}
