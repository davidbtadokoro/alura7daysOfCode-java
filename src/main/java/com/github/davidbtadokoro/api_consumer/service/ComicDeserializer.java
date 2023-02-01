package com.github.davidbtadokoro.api_consumer.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.davidbtadokoro.api_consumer.model.Comic;

public class ComicDeserializer extends StdDeserializer<Comic> {

  public ComicDeserializer() {
    this(null);
  }

  public ComicDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Comic deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
    String title, year = "", urlImage;

    JsonNode rootNode = jp.getCodec().readTree(jp);
  
    // Extracting title property
    title = rootNode.get("title").asText();

    // Extracting year property
    JsonNode dates = rootNode.get("dates");
    for (JsonNode date : dates) {
      if (date.get("type").textValue().compareTo("onsaleDate") == 0) {
        year = date.get("date").textValue();
      }
    }
    year = year.split("-")[0];
    
    // Extracting URL image property
    urlImage = String.format("%s.%s",
      rootNode.get("thumbnail").get("path").textValue(),
      rootNode.get("thumbnail").get("extension").textValue());
    
    return new Comic(title, year, urlImage);
  }
  
}
