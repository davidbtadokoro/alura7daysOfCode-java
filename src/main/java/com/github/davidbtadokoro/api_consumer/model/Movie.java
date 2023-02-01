package com.github.davidbtadokoro.api_consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie implements Content, Comparable<Movie> {

  private String title;
  private String year;
  private String urlImage;
  private String rating;

  @Override
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  @Override
  @JsonProperty("year")
  public String getYear() {
    return year;
  }

  @Override
  @JsonProperty("image")
  public String getUrlImage() {
    return urlImage;
  }

  @Override
  @JsonProperty("imDbRating")
  public String getRating() {
    return rating;
  }

  @Override
  public String toString() {
    return String.format("Titulo: %s | Ano: %s | Nota: %s", title, year, rating);
  }

  @Override
  public int compareTo(Movie anotherMovie) {
    return -this.rating.compareTo(anotherMovie.rating);
  }

}
