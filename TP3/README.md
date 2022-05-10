# Trabajo Practico N°3: Perceptron simple y multicapa

## Descripción

Para usar un Perceptron simple, utilice `SimplePerceptronApp.jar` el cual requiere un archivo `conf.json` en el mismo directorio que el ejecutable. Al terminar el programa creara un archivo `output-yyyy-mm-dd+hh-mm-ss.json` en el cual se imprimira la mejor iteración, tiempo, parametros usados, nro. de iteraciones y épocas. Dependiendo de los parametros se creara tambien un archivo `output--ERROR-yyyy-mm-dd+hh-mm-ss.txt` en el cual se imprimen los errores y los pesos para cada iteración, y/o el archivo `output--TRAIN-ACC-yyyy-mm-dd+hh-mm-ss.txt` en el que se imprime el accuracy al final de cada época.

Ejemplo de archivo de configuración:
```
{
    "iterationLimit": 1000,
    "learningRate": 0.1,
    "trainingDataInputs": [
        [-1, 1],
        [1, -1],
        [-1, -1],
        [1, 1]
    ],
    "trainingDataOutputs": [-1, -1, -1, 1],
    "trainingDataInputDimension": 2,
    "trainingDataInputSize": 4,
    "perceptronMode": "step",
    "printHistory": "both"
}
```

#

Para usar un Perceptron simple con validacion cruzada, utilice `SimpleCrossValidatorApp.jar` el cual requiere un archivo `conf.json` en el mismo directorio que el ejecutable. Al terminar el programa creara un archivo `output-yyyy-mm-dd+hh-mm-ss.json` en el cual se imprimira la mejor iteración, tiempo, parametros usados, nro. de iteraciones y épocas. Dependiendo de los parametros se creara tambien un archivo `output--ERROR-yyyy-mm-dd+hh-mm-ss.txt` en el cual se imprimen los errores y los pesos para cada iteración, y/o el archivo `output--TRAIN-ACC-yyyy-mm-dd+hh-mm-ss.txt` en el que se imprime el accuracy segun el conjunto de entrenamiento al final de cada época y el archivo `output--TEST-ACC-yyyy-mm-dd+hh-mm-ss.txt` con el accuracy segun el conjunto de prueba.

Ejemplo de archivo de configuración:
```
{
    "iterationLimit": 5000000,
    "learningRate": 0.001,
    "minAcceptable": 10e-3,
    "trainingDataInputs": [
        [0],
        [1],
        [2],
        [3],
        [4],
        [5],
        [6],
        [7],
        [8],
        [9],
        [10]
    ],
    "trainingDataOutputs": [0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100],
    "trainingDataInputDimension": 1,
    "trainingDataInputSize": 11,
    "perceptronMode": "nonlinear",
    "sigmoidType": "tanh",
    "beta": 0.5,
    "kCuts": 3,
    "printHistory": "epochs"

}
```

#

Para usar los Perceptrones multicapa correspondientes a cada ejercicio, correr los ejecutables `Ex3_1.jar`, `Ex3_2.jar`, `Ex3_3.jar`, los cuales requieren de los archivos de configuración `ex3_i.txt`, siendo i 1, 2 o 3, ubicados en el directorio /resources. En los mismos se configuran las épocas, entradas, salidas y probabilidad de ruido según corresponda. Se deben respetar los espacios, las comas y las llaves.

Por implementación, se utilizan los siguientes valores:

- beta= 1
- learning rate= 0.01
- accuracy error= 0.18
- función de activación: tangente hiperbólica

Al finalizar los algoritmos, se generarán los archivos de salida con los datos por época de las salidas deseadas vs la salida calculada y el error cometido para la mejor red en cada época. Finalmente se calculan los datos solicitados por el enunciado.

Además se generan archivos planos en el mismo directorio que el ejecutable, que podrán ser utilizados para graficar desde Jupyter Notebook para las visualizaciones del accuracy por época o del error cometido por la inserción de ruido.

Ejemplo de archivo de configuración:
```
Epochs: 100001
HiddenLayers: 2
HiddenLayersSizes: 4 3
InputSize: 2
XSize: 4
inputX: { { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 } }
OutputSize: 1
YSize: 4
outputY: { 1, 1, -1, -1 }
```

#

Este proyecto utiliza la libreria Gson (https://github.com/google/gson) para serializar los resultados a json de manera simple.



## Development

-   Dependencies? `mvn eclipse:eclipse`
-   Jar? `mvn package` and it will be at `target/tp2-1.0.jar`
