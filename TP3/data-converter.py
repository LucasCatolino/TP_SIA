import json


words = []
pattern_dim = 1
for word in open('./tp3-ej2-outputs.txt'):
    words.append(word.rstrip('\n'))

#print(words)

out = []
for w in words:
    if pattern_dim > 1:
        pattern = []
        for val in w.split():
            pattern.append(float(val))
        out.append(pattern)
    else:
        for val in w.split():
            out.append(float(val))
            #out.append(float(val))

y = json.dumps(out)
#un-comment if you want to read the resulting array from the terminal!
print(y)

#resulting array length, don't comment this one.
print("size: ", len(out))

#un-comment if you want to create a json file with the resulting array!
#with open('tp3-ej2-in.json', 'w') as outfile:
    #outfile.write(y)