package com.github.davidbtadokoro.api_consumer.service;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.davidbtadokoro.api_consumer.model.Comic;

public class MarvelComicsJsonParser implements JsonParser {

  private ObjectMapper mapper = getDefaultObjectMapper();
  private String comicsJson;

  private static ObjectMapper getDefaultObjectMapper() {
    ObjectMapper mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    SimpleModule module = new SimpleModule()
        .addDeserializer(Comic.class, new ComicDeserializer());
    mapper.registerModule(module);

    return mapper;
  }

  public MarvelComicsJsonParser(String comicsJson) {
    this.comicsJson = comicsJson;
  }

  @Override
  public List<Comic> parse() {
    try {
      String moviesJsonArray = mapper.readTree(comicsJson).get("data").get("results").toString();
      return mapper.readValue(moviesJsonArray, new TypeReference<List<Comic>>() {});
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
