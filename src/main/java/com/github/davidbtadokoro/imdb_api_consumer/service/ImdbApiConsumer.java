package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ImdbApiConsumer {

  private String apiKey;
  private String moviesJson;

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
    return moviesJson
        .substring(moviesJson.indexOf('[') + 1, moviesJson.indexOf(']'))
        .split("\\},\\{");
  }

  public List<String> parseTitles(String[] moviesArray) {
    List<String> moviesList = Arrays.asList(moviesArray);
    List<String> titles = new ArrayList<>();

    for (String movie : moviesList) {
      String title = movie.split("\",\"")[2]
          .replace("title\":\"", "");
      titles.add(title);
    }

    return titles;
  }

  public List<String> parseYears(String[] moviesArray) {
    List<String> moviesList = Arrays.asList(moviesArray);
    List<String> years = new ArrayList<>();

    for (String movie : moviesList) {
      String year = movie.split("\",\"")[4]
          .replace("year\":\"", "");
      years.add(year);
    }

    return years;
  }

  public List<String> parseUrlImages(String[] moviesArray) {
    List<String> moviesList = Arrays.asList(moviesArray);
    List<String> urlImages = new ArrayList<>();

    for (String movie : moviesList) {
      String urlImage = movie.split("\",\"")[5]
          .replace("image\":\"", "");
      urlImages.add(urlImage);
    }

    return urlImages;
  }

  public List<String> parseRatings(String[] moviesArray) {
    List<String> moviesList = Arrays.asList(moviesArray);
    List<String> ratings = new ArrayList<>();

    for (String movie : moviesList) {
      String rating = movie.split("\",\"")[7]
          .replace("imDbRating\":\"", "");
      ratings.add(rating);
    }

    return ratings;
  }
  
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

}
