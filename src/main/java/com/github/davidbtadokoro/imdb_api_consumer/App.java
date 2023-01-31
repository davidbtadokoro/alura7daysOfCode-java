package com.github.davidbtadokoro.imdb_api_consumer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;
import com.github.davidbtadokoro.imdb_api_consumer.service.ImdbApiClient;
import com.github.davidbtadokoro.imdb_api_consumer.service.ImdbMoviesJsonParser;
import com.github.davidbtadokoro.imdb_api_consumer.service.ImdbTop250HTMLGenerator;

public class App {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java App <api-key>");
      System.exit(1);
    }

    ImdbApiClient imdbApiConsumer = new ImdbApiClient(args[0]);
    String moviesJson = imdbApiConsumer.requestTop250MoviesJson();

    ImdbMoviesJsonParser moviesJsonParser = new ImdbMoviesJsonParser(moviesJson);
    List<Movie> movies = moviesJsonParser.getMoviesListFromJson();

    try (Writer writer = new PrintWriter(new File("imdbTop250Movies.html"))) {
      ImdbTop250HTMLGenerator htmlGenerator = new ImdbTop250HTMLGenerator(writer);
      htmlGenerator.generateHTML(movies);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
