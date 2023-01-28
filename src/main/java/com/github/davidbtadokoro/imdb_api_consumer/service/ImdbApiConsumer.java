package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiConsumer {

  private String apiKey;
  private HttpClient client;
  private HttpRequest request;
  private HttpResponse<String> response;

  public ImdbApiConsumer(String apiKey) {
    this.apiKey = apiKey;
    this.client = HttpClient.newBuilder().build();
  }

  public void sendTop250MoviesRequest() {
    try {
      request = HttpRequest
          .newBuilder()
          .uri(new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey)))
          .GET()
          .build();
      response = client.send(request, BodyHandlers.ofString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getResponseStatusCode() {
    return response.statusCode();
  }

  public String getResponseBody() {
    return response.body();
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }

    ImdbApiConsumer imdbApiConsumer = new ImdbApiConsumer(args[0]);
    imdbApiConsumer.sendTop250MoviesRequest();
    System.out.println(imdbApiConsumer.getResponseStatusCode() + " " + imdbApiConsumer.getResponseBody());
  }

}
