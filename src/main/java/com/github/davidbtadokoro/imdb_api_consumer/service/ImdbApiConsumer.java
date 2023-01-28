package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class ImdbApiConsumer {

  private String apiKey;
  private String moviesJson;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }
    
    ImdbApiConsumer imdbApiConsumer = new ImdbApiConsumer(args[0]);
    imdbApiConsumer.sendTop250MoviesRequest();

    String[] movieArray = imdbApiConsumer.parseMoviesJson();

    List<String> titles = imdbApiConsumer.parseTitles(movieArray);
    titles.forEach(System.out::println);

    List<String> urlImages = imdbApiConsumer.parseUrlImages(movieArray);
    urlImages.forEach(System.out::println);

    List<String> years = imdbApiConsumer.parseYears(movieArray);
    years.forEach(System.out::println);

    List<String> ratings = imdbApiConsumer.parseRatings(movieArray);
    ratings.forEach(System.out::println);
  }

  public ImdbApiConsumer(String apiKey) {
    this.apiKey = apiKey;
  }

  public void sendTop250MoviesRequest() {
    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey)))
          .GET()
          .build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      moviesJson = response.body();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getMoviesJson() {
    return moviesJson;
  }

  public String[] parseMoviesJson() {
    String moviesJsonInsideBrackets = moviesJson
        .substring(moviesJson.indexOf('[') + 1, moviesJson.indexOf(']'));
    return moviesJsonInsideBrackets
        .substring(1, moviesJsonInsideBrackets.length() - 1)
        .split("\\},\\{");
  }
  
  public List<String> parseTitles(String[] moviesArray) {
    return parseAttribute(moviesArray, 2);
  }
  
  public List<String> parseYears(String[] moviesArray) {
    return parseAttribute(moviesArray, 4);
  }
  
  public List<String> parseUrlImages(String[] moviesArray) {
    return parseAttribute(moviesArray, 5);
  }
  
  public List<String> parseRatings(String[] moviesArray) {
    return parseAttribute(moviesArray, 7);
  }

  private List<String> parseAttribute(String[] movieArray, int position) {
    return Stream.of(movieArray)
        .map(e -> e.split("\",\"")[position])
        .map(e -> e.split(":\"")[1])
        .collect(Collectors.toList());
  }

}
