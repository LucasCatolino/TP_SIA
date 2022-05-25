


def init_weights(k, n):
    import numpy as np
    w = [[[i+j*k for l in range(n)] for i in range(k)] for j in range(k)]

    for i in range(k):
        for j in range(k):
            for l in range(k):
                w[i][j][l] = np.random.uniform(-1, 1)
    return w

def get_winner_neuron(w, sample, k):
    import numpy as np
    import scipy as sc

    sam_np = np.array(sample)

    closest = dict()
    closest['value'] = w[0][0]
    closest['diff'] = np.Infinity
    closest['x'] = 0
    closest['y'] = 0


    for i in range(k):
        for j in range(k):
            w_ij_np = np.array(w[i][j])
            #dist = np.linalg.norm(sam_np-w_ij_np)
            dist = sc.spatial.distance.euclidean(sam_np, w_ij_np)
            if(dist < closest['diff']):
                closest['diff'] = dist
                closest['value'] = w[i][j]
                closest['x'] = i
                closest['y'] = j

    return closest

def get_active_neurons(w, k, winner_x, winner_y, r):
    import scipy as sc
    list_of_active_coords = []

    for i in range(k):
        for j in range(k):
            dist = sc.spatial.distance.euclidean([winner_x, winner_y], [i, j])
            if (dist <= r):
                list_of_active_coords.append({"x": i, "y":j, "val": dist})

    return list_of_active_coords

def update_weights(w, k, sample, N_on, learning_rate):
    import numpy as np
    import scipy as sc

    sam_np = np.array(sample)

    for i in range(k):
        for j in range(k):
            if {"x": i, "y": j} in N_on:
                aux_wij = np.array(w[i][j])
                diff = sam_np - aux_wij
                w[i][j] = aux_wij + (learning_rate*diff)

    return w