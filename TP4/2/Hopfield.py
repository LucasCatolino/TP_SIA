import pandas as pd
import numpy as np

class Hopfield:
   
    # init method or constructor 
    def __init__(self, data):
        self.W= self.calculateSynapticWeight(data)
        
    def calculateSynapticWeight(self, data):
        firstRow= data.pop(0)
        Kt= np.array([firstRow])
        while len(data) > 0:
            newRow= np.array(data.pop(0))
            Kt= np.concatenate([Kt, [newRow]])
        K= Kt.T
        
        N= len(firstRow)
        w= (1/N) * np.dot(K, Kt)
        
        #Diagonal is zero
        for i in range(len(w)):
            w[i][i]= 0
        
        return w
 
    def run(self, S):
        file = open("Hopfield.out", "w") 
        i= 0
        prevS= S
        
        file.write(str(prevS))
        file.write("\n")
        
        actS= self.prodW(prevS)
        while (not np.array_equal(prevS, actS) and i < 5000000):
            i= i+1
            prevS= actS;
            
            file.write("Epoch: ")
            file.write(str(i))
            file.write("\n")
            file.write(str(prevS))
            file.write("\n")
            
            actS= self.prodW(prevS)
            
        file.write("Epoch: ") 
        file.write(str(i))
        file.write("\n")
        file.write(str(actS))
        file.write("\n")        
        file.close()
    
    def printW(self):
        print(self.W)
        
    def prodW(self, S):
        return np.sign(np.dot(self.W, S)).astype(int)

def noisyData(data, prob):
    toRet= []
    for i in range(len(data)):
        rand= np.random.rand()
        if (rand < prob):
            toRet.append(data[i] * -1)
        else:
            toRet.append(data[i])
    return toRet

def mainHopfield(dataFrameName, data1, data2, data3, data4, testingData, noiseProb):
    #Read data
    df= pd.read_csv(dataFrameName, sep='\t', skiprows=0, header=None)
    
    #Data from matrix to arrays
    data= []
    toAdd= []
    for index, row in df.iterrows():
        for column in df:
            number= row[column]
            if (not pd.isna(number)):
                toAdd.append(int(number))
            else:
                data.append(toAdd)
                toAdd=[]
                break
    
    #Data to save in Hopfield
    toHop= [data[data1], data[data2], data[data3], data[data4]]
    
    #Object Hopfield
    h= Hopfield(toHop)
    
    #Noise on data
    toTest= noisyData(data[testingData], noiseProb)
    
    #Test with noised data
    h.run(toTest)