from som import mainKohonen
import json


# Opening JSON file
f = open("Ex_1a.json", "r")

# returns JSON object as
# a dictionary
data = json.load(f)
ex = data['Exercise 1A']

mainKohonen(ex['dataName'], ex['k'], ex['n'], ex['radius'], ex['learningRate'], ex['epochs'])

f.close()