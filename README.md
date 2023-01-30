# Alura7daysOfCode - Java Formation

This repository is for the final activity of the [Java Formation](https://cursos.alura.com.br/formacao-java) from [Alura](https://www.alura.com.br/).

## The Project

The project is an [IMDB API](https://imdb-api.com/api) consumer to display the Top 250 Movies (and maybe TV Shows) in a HTML format.

The activity is spread in 7 daily challenges.

## Technology stack

* Language: Java 17

* Build Tool: Maven 3.8.7

* IDE: VSCode

## How to run the application

### With Maven

1. Build the project using `mvn clean package` in the root of the repository (where the `pom.xml` is located).

2. Run the `imdb-api-consumer.jar` file generated inside the `target` directory passing an IMDB API key as argument.

3. Open the `imdbTop250Movies.html` file generated with your favorite browser.

### Using an IDE

Generally, you are going yo configure your Main Class as `com.github.davidbtadokoro.imdb_api_consumer.App`, set an IMDB API key as argument and run the application through your IDE.

Specifically, using VSCode, open the `.vscode/launch.json` and set the field `mainClass` as `com.github.davidbtadokoro.imdb_api_consumer.App` and the field `args` as `[<imdb-api-key>]`.

In any case, a HTML file named `imdbTop250Movies.html` will be generated and you can open it with a browser.

## Challenges

### Day 1

__Objective__: Make an HTTP request to the API and display the data in the terminal.

### Day 2

__Objective__: Parse the JSON from the HTTP response containing the Top 250 Movies.

### Day 3

__Objective__: Model the Movie entity.

### Day 4

__Objective__: Generate a HTML page of the movies obtained from the API call. 