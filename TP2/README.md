# Trabajo Practico N°2: Algoritmos Genéticos

## Descripción

$inserte\ descripción\ aquí$

Al correr el ejecutable `TP2.jar` se imprime en la terminal información sobre los resultados de la búsqueda y los parametros usados, además se guarda en un log con un nombre que siga la convención `output-yyyy-mm-dd+hh-mm-ss.json`.

### Configuration

In `./cfg.txt` you can find the configurations file, which allows for the following to be selected:

-   `population` $(int)$, population size
-   `mutation`:
    -   `probability` $(double)$
    -   `deviation` $(double)$
-   `selector`:
    -   `type`: $(elite,\ stochastic,\ truncated,\ boltzman,\ roulette,\ rank,\ tournament)$
    -   `truncated` : `k` $(int)$
    -   `boltzman`:
        -   `T0` $(double)$
        -   `Tc` $(double)$
        -   `k` $(double)$
-   `cross`:
    -   `type`: $(simple,\ multiple,\ uniform)$
    -   `multiple`: `k` $(int)$
-   `cutoff`:
    -   `maxgen` $(int)$
    -   `minacceptable` $(double)$
    -   `maxrepstruct` $(int)$
    -   `maxrepcontent` $(int)$
-   `dump` $(boolean)$, if true it will write info(best, avg, worst) about all generations

    The file looks something like this:

```
population: 10
selector.type: elite
mutation.deviation: 0.1
cross.type: uniform
cutoff.maxgen: 50
dump: true
```

another example:

```
population: 1000
selector.type: elite
mutation.deviation: 0.2
mutation.probability: 0.1
cross.type: simple
cutoff.maxgen: 5000
cutoff.maxrepstruct: 10
cutoff.maxrepcontent: 15
```

#### NOTE:

The config file **must** be in a directory alongside the .jar file, like this:

```
  ./ <= the folder in which you have the program
   |
   |_ TP2.jar
   |
   |_ cfg.txt
```

## Development

-   Dependencies? `mvn eclipse:eclipse`
-   Jar? `mvn package` and it will be at `target/tp2-1.0.jar`

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

-   `src`: the folder to maintain sources
-   `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
