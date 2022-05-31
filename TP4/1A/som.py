import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler

def mainKohonen (fileData, k, n, rad, learRate, epochs ):
    df= pd.read_csv(fileData)
    df_countries = df["Country"]
    print("Reading data...")

    df_stand=  StandardScaler().fit_transform(df[["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]])
    df_stand= pd.DataFrame(df_stand, columns=df[["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].columns)

    params = Params(k=k,n=n,r=rad, lr=learRate)
    som = SOM(params, data=df_stand, epochs=epochs)
    res = som.run()

    save_heatmaps(res)
    save_averages(res)
    save_weights(res)

    file = open("KohonenGroupings.out", "w")
    groups = som.test(df_countries, res['weights'])
    for i in range(k):
        for j in range(k):
            file.write("Neuron at (" + str(i) + ", " + str(j) + "): ")
            file.write(list2str(groups[i][j]) + "\n")
    #file.write(som.test(df_countries, res['weights']))
    file.close()
    print("Done.")

def list2str(l):
    return arr2str(np.asarray(l))

def arr2str(arr):
    return np.array2string(arr, formatter={'float_kind':lambda x: "%.2f" % x}, separator=",")


def save_heatmaps(res):
    file = open("KohonenHeatmaps.out", "w")
    for i in range(len(res["heatmaps"])):
        file.write("Epoch: " + str(i) + "\n")
        file.write(list2str(res["heatmaps"][i]) + "\n")
    file.close()

def save_averages(res):
    file = open("KohonenAverages.out", "w")
    for i in range(len(res["averages"])):
        file.write("Epoch: " + str(i) + "\n")
        file.write(list2str(res["averages"][i]) + "\n")
    file.close()

def save_weights(res):
    file = open("KohonenWeights.out", "w")
    for i in range(len(res["allWeights"])):
        file.write("Epoch: " + str(i) + "\n")
        file.write(list2str(res["allWeights"][i]) + "\n")
    file.close()


class Params:
    def __init__(self, k, n, r, lr):
        self.k = k
        self.n = n
        self.radius = r
        self.learning_rate = lr


class SOM:

    def __init__(self, params, data, epochs):
        self.params = params
        self.data = data
        self.epochs = epochs

    def run(self):
        import numpy as np
        from utils import get_avg_distance, init_weights_from_data, get_winner_neuron, get_active_neurons, update_weights
        import copy

        data_count = len(self.data.index)
        max_t = self.epochs * data_count
        t = 0

        w = init_weights_from_data(self.params.k, self.params.n, self.data, data_count)
        #print(w)

        radius = self.params.radius
        learning_rate = self.params.learning_rate

        #for the avg distance between neurons plot
        averages = []

        #for the animated heatmap (so we are not running it in real time, i blame jupyter)
        heatmaps = []

        #for posterity
        weights = []


        for e in range(0, self.epochs):
            #plotting array
            points = [[0 for i in range(self.params.k)] for j in range(self.params.k)]

            for p in range(data_count):
                #get sample from data
                x_p = self.data.iloc[[p]][["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].to_numpy()[0]

                #find winner neuron
                closest_info = get_winner_neuron(w, x_p, self.params.k)
                #print(str(closest_info['x']) + " " + str(closest_info['y']) + " " +  str(closest_info['value']) + " " + str(closest_info['diff']) + " " + str(x_p))

                points[closest_info['x']][ closest_info['y']] += 1

                #neuronas activadas
                N_k = get_active_neurons(w, self.params.k, closest_info['x'], closest_info['y'], radius)

                #actualizar pesos
                w = update_weights(w, x_p, N_k, learning_rate)

                #update radius and learning_rate
                radius = np.floor((max_t - t*1.2) * radius/max_t) + 1
                #print(radius)
                #learning_rate = learning_rate / (t+1)
                learning_rate = self.params.learning_rate * (np.exp(-t*0.002))
                #print(learning_rate)
                t += 1

            #save info for heatmap...
            heatmaps.append(copy.deepcopy(points))
            avg = get_avg_distance(w, self.params.k)
            averages.append(copy.deepcopy(avg))
            weights.append(copy.deepcopy(w))
            #print(avg)

        out = dict()
        out['heatmaps'] = heatmaps
        out['averages'] = averages
        out['weights'] = w
        out['allWeights'] = weights
        #print('final weights', w)

        return out

    def test(self, labels, w):
        import numpy as np
        from utils import get_avg_distance, init_weights_from_data, get_winner_neuron, get_active_neurons, update_weights
        import copy

        data_count = len(self.data.index)

        #res array...
        points = [[ [] for i in range(self.params.k)] for j in range(self.params.k)]

        for p in range(data_count):
            #get sample from data
            x_p = self.data.iloc[[p]][["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].to_numpy()[0]

            #find winner neuron
            closest_info = get_winner_neuron(w, x_p, self.params.k)
            #print(str(closest_info['x']) + " " + str(closest_info['y']) + " " +  str(closest_info['value']) + " " + str(closest_info['diff']) + " " + str(x_p))
            points[closest_info['x']][ closest_info['y']].append(labels[p])

        return points



