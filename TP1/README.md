## Sistemas de inteligencia artificial

# Trabajo Practico N°1: Métodos de búsqueda

## Descripción

Este proyecto es una implementación de un generador de soluciones para un rompecabezas de números (la variante de 3x3: 8-puzzle).

Es capaz de generar soluciones a traves de estrategias de búsqueda no informadas (`BPA`, `BPP` y `BPPV`) y de búsquedas informadas (`Heurística Local`, `Heurística Global` y `A\*`) con 3 heurísticas elegibles: `Manhattan`, `Euclideana` y una `no admisible`.

Se pueden configurar varios parámetros del problema: puzzle a resolver, tipo de estrategia, método de búsqueda, heurística, limite de profundidad _(los últimos 2 siendo tomados en cuenta cuando tenga sentido)_.

Al correr el ejecutable `TP1.jar` se imprima en la terminal los resultados de la búsqueda y se guardara en un log un nombre que siga la convención `output-yyyy-mm-dd+hh-mm-ss.log`.

### Configuration

In `./parameters/cfg.txt` you can find the configurations file, which allows for the following to be selected: puzzle, search strategy (`uninformed | informed`), search method (`bpa | bpp | bppv| hl | hg | astar`), limit (used with BPPV) & heuristic (`euclidean | manhattan | invalid`).
The file looks something like this:

```
puzzle: 123456708
strategy: uninformed
method: BPPV
limit: 1
```

another example:

```
puzzle: 265013478
strategy: informed
method: hg
heur: manhattan
```

#### NOTE:

The config file **must** be in a directory alongside the .jar file, like this:

```
  ./ <= the folder in which you have the program
   |
   |_ TP1.jar
   |
   |_ /parameters
      |
      |_cfg.txt
```

## Setup

### Requirements

-   [Java Runtime Environment](https://www.java.com/en/download/).
-   (if you want to open this project in VSCode [here's a link to tutorial on how to get things in place](https://code.visualstudio.com/docs/java/java-tutorial)).

To run this project execute this command from the terminal (with the terminal in the directory where the .jar file is located):

```
$> java -jar TP1.jar
```

## Development

### VSCode Related Usage

Since you are working with VSCode, some things to take into account:

-   Saving a .java file in the `src` folder while trigger VSCode to compile it to a .class file in `bin`
-   To run a program, go to `bin` and execute: `java filename`
-   To export a build to JAR, use the command palette and type `Java: Export Jar...`

### Folder Structure

The workspace contains two folders by default, where:

-   `src`: the folder to maintain sources
-   `lib`: the folder to maintain dependencies
-   `parameters`: the folder where the config file is found

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

### Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
