package com.github.davidbtadokoro.api_consumer.model;

public class Comic implements Content {

  private String title;
  private String year;
  private String urlImage;
  
  public Comic(String title, String year, String urlImage) {
    this.title = title;
    this.year = year;
    this.urlImage = urlImage;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getYear() {
    return year;
  }

  @Override
  public String getUrlImage() {
    return urlImage;
  }

  @Override
  public String getRating() {
    return "N/A";
  }

  @Override
  public String toString() {
    return String.format("Título: %s | Ano: %s", title, year);
  }
  
}
