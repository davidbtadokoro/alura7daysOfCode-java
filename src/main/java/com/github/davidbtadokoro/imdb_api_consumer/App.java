package com.github.davidbtadokoro.imdb_api_consumer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;
import com.github.davidbtadokoro.imdb_api_consumer.service.ImdbApiConsumer;
import com.github.davidbtadokoro.imdb_api_consumer.service.MoviesListHTMLGenerator;

public class App {
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }
    
    ImdbApiConsumer imdbApiConsumer = new ImdbApiConsumer(args[0]);
    String moviesJson = imdbApiConsumer.requestTop250MoviesJson();
    List<Movie> movies = imdbApiConsumer.getMoviesList(moviesJson);
    
    try (Writer writer = new PrintWriter(new File("imdbTop250Movies.html"))) {
      MoviesListHTMLGenerator htmlGenerator = new MoviesListHTMLGenerator(writer);
      htmlGenerator.generate(movies);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
