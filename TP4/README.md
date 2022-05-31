# Trabajo Practico N°4: Métodos de Aprendizaje NO Supervisado

## Descripción

Librerias utilizadas: `numpy`,`matplotlib`,`pandas` (para lectura de archivos), `sklearn` (estandarizacion de los datos), `scipy` (calcular distancias para vectores).

Instalacion: `pip intall -r libraries.txt`

Para correr el ejercicio 1.a) utilice el comando `python Ex_1a.py` estando situado en la carpeta `1A`, el mismo utiliza como entrada de parametros el archivo `Ex_1a.json` en el mismo directorio que el codigo a correr. Al terminar el programa, creara los archivos `KohonenAverages.out`, `KohonenGroupings.out`, `KohonenHeatmaps.out` y `KohonenWeights.out`, los cuales tendran información acerca de las distancias promedio entre neuronas vecinas por cada epoch, las asociaciones entre países y las neuronas finales, la cantidad de elementos asociados a cada neurona por cada epoch y los pesos de la red por cada epoch.
Se requiere que el archivo con los datos a aprender este en la misma carpeta que el archivo `Ex_1a.py`.

Ejemplo del archivo de configuracion:

```
{
	"Exercise 1A": {
		"dataName": "europe.csv",
		"learningRate": 0.7,
		"k": 3,
		"n": 7,
		"radius": 10,
		"epochs": 100
	}
}

```

Para correr el ejercicio 1.b) utilice el comando `python Ex_1b.py` estando situado en la carpeta `1B`, el mismo utiliza como entrada de parametros el archivo `Ex_1b.json` en el mismo directorio que el codigo a correr. Al terminar el programa, creara los archivos `Oja.out`, el cual tendra informacion de los pesos sinapticos a lo largo de las epocas.
Se requiere que el archivo con los datos a aprender este en la misma carpeta que el archivo `Ex_1b.py`.

Ejemplo del archivo de configuracion:

```
{
	"Exercise 1B": {
		"dataName": "europe.csv",
		"learningRate": 0.0001,
		"epochs": 1000
	}
}


```

Para correr el ejercicio 2 utilice el comando `python Ex_2.py` estando situado en la carpeta `2`, el mismo utiliza como entrada de parametros el archivo `Ex_2.json` en el mismo directorio que el codigo a correr. Al terminar el programa, creara los archivos `Hopfield.out`, el cual tendra informacion de los patrones a lo largo de las epocas hasta llegar a un patron estabilizado.
Se requiere que el archivo con los datos a aprender este en la misma carpeta que el archivo `Ex_2.py`.

Ejemplo del archivo de configuracion:

```
{
	"Exercise 2": {
		"dataFrameName": "letras.txt",
		"data1": 11,
		"data2": 15,
		"data3": 19,
		"data4": 23,
		"testingData": 15,
		"noiseProb": 0.5
	}
}


```
