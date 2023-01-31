package com.github.davidbtadokoro.imdb_api_consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie implements Content {

  private String title;
  private String year;
  private String urlImage;
  private String rating;

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  @JsonProperty("year")
  public String getYear() {
    return year;
  }

  @JsonProperty("image")
  public String getUrlImage() {
    return urlImage;
  }

  @JsonProperty("imDbRating")
  public String getRating() {
    return rating;
  }

  @Override
  public String toString() {
    return String.format("Titulo: %s | Ano: %s | Nota: %s | Url:", title, year, rating);
  }

}
