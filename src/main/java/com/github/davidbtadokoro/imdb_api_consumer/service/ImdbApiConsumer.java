package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Collections;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;

public class ImdbApiConsumer {

  private ObjectMapper mapper = getDefaultObjectMapper();
  private String apiKey;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("ERROR: Missing API key!");
      System.out.println("USAGE: java ImdbApiConsumer <api-key>");
      System.exit(1);
    }
    
    ImdbApiConsumer imdbApiConsumer = new ImdbApiConsumer(args[0]);
    String moviesJson = imdbApiConsumer.requestTop250MoviesJson();
    List<Movie> movies = imdbApiConsumer.getMoviesList(moviesJson);
    movies.forEach(System.out::println);
  }

  private static ObjectMapper getDefaultObjectMapper() {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ImdbApiConsumer(String apiKey) {
    this.apiKey = apiKey;
  }

  public String requestTop250MoviesJson() {
    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest
          .newBuilder()
          .uri(new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey)))
          .GET()
          .build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      return response.body();
    }
    catch (Exception e) {
      e.printStackTrace();
      return "The request generated an exception";
    }
  }

  public List<Movie> getMoviesList(String moviesJson) {
    try {
      String moviesJsonArray = mapper.readTree(moviesJson).get("items").toString();
      return mapper.readValue(moviesJsonArray, new TypeReference<List<Movie>>(){});
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
