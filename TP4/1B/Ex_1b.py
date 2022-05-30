from oja import mainOja
import json
import os

#path_to_config = os.path.join(os.path.dirname(__file__), 'Ex_1b.json')

# Opening JSON file
#f = open(path_to_config)
f = open( 'Ex_1b.json')

# returns JSON object as
# a dictionary
data = json.load(f)
ex = data['Exercise 1B']

#mainOja(os.path.join(os.path.dirname(__file__), ex['dataName']), ex['epochs'], ex['learningRate'])
mainOja(ex['dataName'], ex['epochs'], ex['learningRate'])

f.close()