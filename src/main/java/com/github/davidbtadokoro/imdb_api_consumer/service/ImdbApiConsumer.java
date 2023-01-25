package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiConsumer {
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }

    String uri = String.format("https://imdb-api.com/en/API/Top250Movies/%s", args[0]);

    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(new URI(uri))
          .GET()
          .build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      System.out.printf("%nSTATUS CODE: %d%n%n", response.statusCode());
      System.out.printf("BODY: %s", response.body());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
