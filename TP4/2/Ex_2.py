from Hopfield import mainHopfield
import json
  
# Opening JSON file
f = open('Ex_2.json')
  
# returns JSON object as 
# a dictionary
data = json.load(f)
  
# Iterating through the json
# list
dataName= data['Exercise 2']['dataFrameName']
data1= data['Exercise 2']['data1']
data2= data['Exercise 2']['data2']
data3= data['Exercise 2']['data3']
data4= data['Exercise 2']['data4']
testingData= data['Exercise 2']['testingData']
noiseProb= data['Exercise 2']['noiseProb']
  
# Closing file
f.close()

mainHopfield(dataName, data1, data2, data3, data4, testingData, noiseProb)