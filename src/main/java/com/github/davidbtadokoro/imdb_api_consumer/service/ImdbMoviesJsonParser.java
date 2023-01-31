package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;

public class ImdbMoviesJsonParser implements JsonParser {

  private ObjectMapper mapper = getDefaultObjectMapper();
  private String moviesJson;

  private static ObjectMapper getDefaultObjectMapper() {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ImdbMoviesJsonParser(String moviesJson) {
    this.moviesJson = moviesJson;
  }

  public List<Movie> parse() {
    try {
      String moviesJsonArray = mapper.readTree(moviesJson).get("items").toString();
      return mapper.readValue(moviesJsonArray, new TypeReference<List<Movie>>() {});
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
