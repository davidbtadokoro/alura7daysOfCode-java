package com.github.davidbtadokoro.api_consumer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import com.github.davidbtadokoro.api_consumer.model.Comic;
import com.github.davidbtadokoro.api_consumer.model.Movie;
import com.github.davidbtadokoro.api_consumer.service.HTMLGenerator;
import com.github.davidbtadokoro.api_consumer.service.ImdbApiClient;
import com.github.davidbtadokoro.api_consumer.service.ImdbMoviesJsonParser;
import com.github.davidbtadokoro.api_consumer.service.MarvelApiClient;
import com.github.davidbtadokoro.api_consumer.service.MarvelComicsJsonParser;

public class App {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("ERROR: Missing API keys!");
      System.out.println("USAGE: java App <imdb-api-key> <marvel-api-key> <marvel-private-key>");
      System.exit(1);
    }

    ImdbApiClient imdbApiClient = new ImdbApiClient(args[0]);
    String moviesJson = imdbApiClient.getBody();

    ImdbMoviesJsonParser moviesJsonParser = new ImdbMoviesJsonParser(moviesJson);
    List<Movie> movies = moviesJsonParser.parse();

    try (Writer writer = new PrintWriter(new File("imdbTop250Movies.html"))) {
      HTMLGenerator htmlGenerator = new HTMLGenerator(writer);
      htmlGenerator.generateHTML(movies);
    } catch (IOException e) {
      e.printStackTrace();
    }

    MarvelApiClient marvelApiClient = new MarvelApiClient(args[1], args[2]);
    String comicsJson = marvelApiClient.getBody();
    
    MarvelComicsJsonParser comicsJsonParser = new MarvelComicsJsonParser(comicsJson);
    List<Comic> comics = comicsJsonParser.parse();

    try (Writer writer = new PrintWriter(new File("spiderManFirst100Comics.html"))) {
      HTMLGenerator htmlGenerator = new HTMLGenerator(writer);
      htmlGenerator.generateHTML(comics);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
