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
        print(w)

        radius = self.params.radius
        learning_rate = self.params.learning_rate

        #for the avg distance between neurons plot
        averages = []

        #for the animated heatmap (so we are not running it in real time, i blame jupyter)
        heatmaps = []


        for e in range(0, self.epochs):
            #plotting array
            points = [[0 for i in range(self.params.k)] for j in range(self.params.k)]

            for p in range(data_count):
                #get sample from data
                x_p = self.data.iloc[[p]][["Area", "GDP", "Inflation", "Life.expect", "Military", "Pop.growth", "Unemployment"]].to_numpy()[0]

                #find winner neuron
                closest_info = get_winner_neuron(w, x_p, self.params.k)
                #print(closest_info)

                points[closest_info['x']][ closest_info['y']] += 1

                #neuronas activadas
                N_k = get_active_neurons(w, self.params.k, closest_info['x'], closest_info['y'], radius)

                #actualizar pesos
                w = update_weights(w, x_p, N_k, learning_rate)

                #update radius and learning_rate
                radius = np.floor((max_t - t*1.2) * radius/max_t) + 1
                print(radius)
                #learning_rate = learning_rate / (t+1)
                learning_rate = self.params.learning_rate * (np.exp(-t*0.002))
                #print(learning_rate)
                t += 1

            #save info for heatmap...
            heatmaps.append(copy.deepcopy(points))
            avg = get_avg_distance(w)
            averages.append(copy.copy(avg))
            #print(e)

        out = dict()
        out['heatmaps'] = heatmaps
        out['averages'] = averages
        out['weights'] = w
        print(w)

        return out

