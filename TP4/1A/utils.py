


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

    closest = w[0][0]
    diff = np.Infinity

    for i in range(k):
        for j in range(k):
            w_ij_np = np.array(w[i][j])
            #dist = np.linalg.norm(sam_np-w_ij_np)
            dist = sc.spatial.distance.euclidean(sam_np, w_ij_np)
            if(dist < diff):
                diff = dist
                closest = w[i][j]

    return closest

