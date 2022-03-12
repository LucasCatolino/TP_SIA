## Sistemas de inteligencia artificial

# Trabajo Practico N°1: Métodos de búsqueda

## General Info

This project is [insert text here]

### Configurations

In `src/parameters` you can find the configurations file, which allows for the following to be selected: puzzle, search strategy (`uninformed | informed`), search method (`bpa | bpp | bppv| hl | hg | astar`), limit (used with BPPV) & heuristic (`euclidean | manhattan | invalid`).
The file looks something like this:

```
puzzle: 123456708
strategy: uninformed
method: BPA
limit: 1
heur: invalid
```

## Setup

To run this project, [insert text here]

```
$> java -jar TP1.jar
```

## Development

### VSCode Related Usage

Since you are working with VSCode, some things to take into account:

-   Saving a .java file in the `src` folder while trigger VSCode to compile it to a .class file in `bin`
-   To run a program, go to `bin` and execute: `java filename`
-   To export a build to JAR, use the command palette and type `Java: Export Jar...`

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### Folder Structure

The workspace contains two folders by default, where:

-   `src`: the folder to maintain sources
-   `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

### Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
