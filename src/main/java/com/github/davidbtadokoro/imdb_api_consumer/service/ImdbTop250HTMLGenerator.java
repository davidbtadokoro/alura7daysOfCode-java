package com.github.davidbtadokoro.imdb_api_consumer.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.github.davidbtadokoro.imdb_api_consumer.model.Movie;

public class ImdbTop250HTMLGenerator {

  private static String preamble = """
        <!DOCTYPE html>
        <html>
      """;
  private static String head = """
      <head>
        <meta charset=\"utf-8\">
        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
          + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
      </head>
      """;
  private static String divTemplate = """
      <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem; margin: auto\">
        <h4 class=\"card-header\">%s</h4>
        <div class=\"card-body\">
          <img class=\"card-img\" src=\"%s\" alt=\"%s\">
          <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
        </div>
      </div>
      """;
      
  private Writer writer;

  public ImdbTop250HTMLGenerator(Writer writer) {
    this.writer = writer;
  }

  public void generateHTML(List<Movie> movies) {
    try {
      writer.write(preamble);
      writer.write(head);
      writeBody(movies);
      writer.write("</html>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void writeBody(List<Movie> movies) throws IOException {
    writer.write("<body>");
    for (Movie movie : movies) {
      writer.write(String.format(divTemplate, movie.getTitle(), movie.getImage(), movie.getTitle(),
          movie.getImDbRating(), movie.getYear()));
    }
    writer.write("</body>");
  }

}
