

def init_weights_from_data(k, n, data, samples_rows):
    import random
    import copy
    import numpy as np
    w = [[[i+j*k for l in range(n)] for i in range(k)] for j in range(k)]

    samples = copy.deepcopy(np.array(data))
    samples = sorted(samples, key=lambda x: random.random())
    #print('w', samples)
    s = 0
    for i in range(k):
        for j in range(k):
            w[i][j] = samples[s]
            s += 1


    #sams_np = np.array(samples)

    #for i in range(k):
        #for j in range(k):
            #randRow = np.random.randint(samples_rows, size=1)
            ##w[i][j] = np.random.choice(sams_np[randRow[0], :])
            ##print(randRow, sams_np[randRow[0]])
            #w[i][j] = sams_np[randRow[0]]
    return w


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

    #sam_np = np.array(sample)

    closest = dict()
    closest['value'] = w[0][0]
    closest['diff'] = np.Infinity
    closest['x'] = 0
    closest['y'] = 0


    for i in range(k):
        for j in range(k):
            #w_ij_np = np.array(w[i][j])
            #w_ij_np = w[i][j]
            #dist = np.linalg.norm(sam_np-w_ij_np)
            #dist = sc.spatial.distance.euclidean(sam_np, w_ij_np)
            dist = sc.spatial.distance.euclidean(sample, w[i][j])
            if(dist < closest['diff']):
                #print(w[i][j], sample)
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
            #dist = sc.spatial.distance.euclidean(w[winner_x][winner_y], w[i][j])
            dist = sc.spatial.distance.euclidean([winner_x, winner_y], [i, j])
            if (dist <= r):
                #print([winner_x, winner_y], [i, j], dist,r)
                #print('close enough')
                list_of_active_coords.append({"x": i, "y":j, "val": dist})

    return list_of_active_coords


def update_weights(w, sample, N_on, learning_rate):
    import numpy as np
    import scipy as sc

    #sam_np = np.array(sample)
    sam_np = sample

    for i in range(len(N_on)):
        x = N_on[i]['x']
        y = N_on[i]['y']
        #aux_wij = np.array(w[x][y])
        aux_wij = w[x][y]
        #print(x, y, aux_wij)
        #diff = sam_np - aux_wij
        #diff = aux_wij - sam_np
        diff = np.subtract(sam_np, aux_wij)
        w[x][y] = aux_wij + (learning_rate*diff)
        #print('out:', w[x][y])

    return w


#only gets you coords for available top-bottom-left-right, no diagonals, not the values, just the coords that work
def get_neighbors(matrix, row, col):
    rows, cols = len(matrix), len(matrix[0])
    out = [(col+a[0], row+a[1]) for a in
                    [(-1,0), (1,0), (0,-1), (0,1)]
                    if ( (0 <= col+a[0] < cols) and (0 <= row+a[1] < rows))]

    return out

def get_neighbors_avg_dst(x, y, w):
    import scipy as sc
    nb = get_neighbors(w, y, x)
    curr = w[x][y]

    out = 0
    for i in range(len(nb)):
        neigh = w[nb[i][0]][nb[i][1]]
        out += sc.spatial.distance.euclidean(curr,neigh)

    return out / len(nb)



def get_avg_distance(w, k):
    #dist = 0
    points = [[0 for i in range(k)] for j in range(k)]
    for i in range(len(w)):
        for j in range(len(w[0])):
            #dist += get_neighbors_avg_dst(i, j, w)
            points[i][j] = get_neighbors_avg_dst(i, j, w)
    return points
    #return dist / (len(w)*len(w[0]))