package com.github.davidbtadokoro.api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.apache.commons.codec.digest.DigestUtils;

public class MarvelApiClient implements ApiClient {

  private String apiKey;
  private String privateKey;

  public MarvelApiClient(String apiKey, String privateKey) {
    this.apiKey = apiKey;
    this.privateKey = privateKey;
  }

  // Request for first 100 comics featured by Spider-Man (Peter Parker)
  public String getBody() {
    String ts = String.valueOf(System.currentTimeMillis());
    String hash = DigestUtils.md5Hex(ts + privateKey + apiKey);

    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(new URI(String.format(
              "https://gateway.marvel.com/v1/public/characters/1009610/comics?format=comic&orderBy=onsaleDate&limit=100&ts=%s&apikey=%s&hash=%s",
              ts, apiKey, hash)))
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
