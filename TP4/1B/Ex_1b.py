from oja import mainOja
import json


# Opening JSON file
f = open("Ex_1b.json", "r")

# returns JSON object as
# a dictionary
data = json.load(f)
ex = data['Exercise 1B']

mainOja(ex['dataName'], ex['epochs'], ex['learningRate'])

f.close()