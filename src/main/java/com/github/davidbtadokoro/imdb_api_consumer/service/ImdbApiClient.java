package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiClient implements ApiClient {

  private String apiKey;

  public ImdbApiClient(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getBody() {
    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey)))
          .GET()
          .build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      return response.body();
    } catch (Exception e) {
      e.printStackTrace();
      return "The request generated an exception";
    }
  }

}
