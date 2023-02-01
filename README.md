# Alura7daysOfCode - Java Formation

This repository is for the final activity of the [Java Formation](https://cursos.alura.com.br/formacao-java) from [Alura](https://www.alura.com.br/).

## The Project

The project is an [IMDB API](https://imdb-api.com/api) and [Marvel API](https://developer.marvel.com/) consumer to display information about movies and comics in a HTML format.

The activity is spread in 7 daily challenges.

## Technology stack

* Language: Java 17

* Build Tool: Maven 3.8.7

* IDE: VSCode

## How to run the application

### With Maven

1. Build the project using `mvn clean package` in the root of the repository (where the `pom.xml` is located).

2. Run the `api-consumer.jar` file generated inside the `target` directory passing an IMDB API key, an Marvel public key and an Marvel private key as arguments.

3. Open the `imdbTop250Movies.html` and `spiderManFirst100Comics.html` files generated with your favorite browser.

### Using an IDE

Generally, you are going yo configure your Main Class as `com.github.davidbtadokoro.api_consumer.App`, set an IMDB API key, an Marvel public key and an Marvel private key as arguments and run the application through your IDE.

Specifically, using VSCode, open the `.vscode/launch.json` and set the field `mainClass` as `com.github.davidbtadokoro.api_consumer.App` and the field `args` as `["<imdb-api-key>", "<marvel-public-key>", "<marvel-private-key>"]`.

In any case, HTML files named `imdbTop250Movies.html` and `spiderManFirst100Comics.html` will be generated and you can open it with a browser.

## Challenges

### Day 1

__Objective__: Make an HTTP request to the API and display the data in the terminal.

### Day 2

__Objective__: Parse the JSON from the HTTP response containing the Top 250 Movies.

### Day 3

__Objective__: Model the Movie entity.

### Day 4

__Objective__: Generate a HTML page of the movies obtained from the API call.

### Day 5

__Objective__: Encapsulate the HTTP call to the API and the JSON parsing in other classes.

### Day 6

__Objective__: Make the code polymorphic to other API calls.

### Day 7

__Objective__: Create more ways to sort the lists other than the returned one from the API call.