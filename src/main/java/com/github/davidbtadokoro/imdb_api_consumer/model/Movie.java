package com.github.davidbtadokoro.imdb_api_consumer.model;

public class Movie {

  private String title;
  private String year;
  private String image;
  private String imDbRating;

  public String getTitle() {
    return title;
  }

  public String getYear() {
    return year;
  }

  public String getImage() {
    return image;
  }

  public String getImDbRating() {
    return imDbRating;
  }

  @Override
  public String toString() {
    return String.format("Titulo: %s | Ano: %s | Nota: %s", title, year, imDbRating);
  }

}
