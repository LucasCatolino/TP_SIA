import math
import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler

def mainOja(fileData, epochs, learningRate):
    df= pd.read_csv (fileData, 'r')

    df1 = df.loc[:, df.columns != 'Country']

    #Estandarizo los datos
    df_countries= df["Country"]
    df_stand=  StandardScaler().fit_transform(df[["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]])
    df_stand= pd.DataFrame(df_stand, columns=df[["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].columns)

    o = OJA(data=df_stand, epochs=epochs, learningRate=learningRate)
    o.run()




class OJA:

    # init method or constructor
    def __init__(self, data, epochs, learningRate):
        import numpy as np
        self.X= data
        self.w = np.random.rand(self.X.iloc[[0]].size)
        self.epochs = epochs
        self.learningRate = learningRate

    # Sample Method
    def run(self):
        import numpy as np
        file = open("Oja.out", "w")

        file.write("Learning rate: " + str(self.learningRate) + "\n")

        data_count = len(self.X.index)
        for i in range(self.epochs):
            for p in range(data_count):
                x_p = self.X.iloc[[p]][["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].to_numpy()[0]

                s= np.inner(x_p, self.w)

                self.w= self.w + self.learningRate * s * (x_p - s * self.w)

            file.write("Epoch: " + str(i) + ", " + str(self.getW()) + "\n")

        file.close()

    def printX(self):
        print(self.X)

    def printW(self):
        print(self.w)

    def  getW(self):
        return self.w * -1

    def getNormW(self):
        import numpy as np
        return np.sqrt(np.inner(self.w,self.w))