package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiConsumer {

  private String apiKey;
  private String top250MoviesJSON;

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
      top250MoviesJSON = response.body();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTop250MoviesJSON() {
    return top250MoviesJSON;
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }

    ImdbApiConsumer imdbApiConsumer = new ImdbApiConsumer(args[0]);
    imdbApiConsumer.sendTop250MoviesRequest();
    System.out.println(imdbApiConsumer.getTop250MoviesJSON());
  }

}
