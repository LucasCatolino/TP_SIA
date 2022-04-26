import numpy as np
from scipy.optimize import minimize
import time


class Individuo:
    X = []
    fitness = 1

    def __init__(self,X):
        self.X = X

    def __str__(self):
        return self.X

    def getW(self):
        return self.X[0:3]
    def getS(self):
        return self.X[3:9]
    def getR(self):
        return self.X[9:11]

def g(x):
    return np.exp(x) / (1 + np.exp(x))

def F(W, S, R, xi):
    wd = 0
    sd = 0
    for j in [1, 2]:
        sd = 0
        for k in [0, 2]:
            sd += S[(j - 1) * 3 + k] * xi[k] - R[j - 1]

        wd += (W[j] * g(sd)) - W[0]

    return g(wd)

def E(x):
    zeta = [0, 1, 1]
    xi_samples = [[4.4793, -4.0765, -4.0765], [-4.1793, -4.9218, 1.7664], [-3.9429, -0.7689, 4.8830]]
    ind = Individuo(x)
    out = 0
    for mu in [0, 2]:
        out += (zeta[mu] - F(ind.getW(), ind.getS(), ind.getR(), xi_samples[mu]))**2


    return out

def scipyOptimizeCG(x):
    myX = x
    # bounds_x1 = (-1, 0)
    # bounds_x2 = (-11, -10)
    # bounds_x3 = (8, 9)
    # bounds_x4 = (1, 2)
    # bounds_x5 = (-1, 0)
    # bounds_x6 = (-1, 0)
    # bounds_x7 = (-3, -2)
    # bounds_x8 = (0, 1)
    # bounds_x9 = (0, 1)
    # bounds_x10 = (0, 1)
    # bounds_x11 = (-1, 0)

    # bounds = [bounds_x1, bounds_x2, bounds_x3, bounds_x4, bounds_x5, bounds_x6, bounds_x7, bounds_x8, bounds_x9, bounds_x10, bounds_x11]

    start = time.time()

    #result = minimize(E, x0, method = 'Powell', bounds = bounds)
    #result = minimize(E, x0, method = 'Powell')
        #print([myX, fit])
    #result = minimize(E, x0, method= 'Newton-CG', jac= True)
    #result = adam(E, x0)
    MINIMUM = 0.0000001
    fit = 1
    while(fit > MINIMUM):
        result = minimize(E, myX, method= 'Powell')
        fit = E(result.x)
        myX = result.x

    done = time.time()
    elapsed =done - start


    #print(result)

    #[-2.50019497e-01, -1.09999427e+01,  9.00000000e+00,  1.99995911e+00, -6.61070036e-05, -9.99952076e-01, -2.99995918e+00,  9.99933893e-01, 9.99952102e-01,  5.66444188e-01, -7.00226234e-01]
    X = Individuo(myX)
    #print(F(X.getW(), X.getS(), X.getR(), [4.4793, -4.0765, -4.0765])) #f1
    #print(F(X.getW(), X.getS(), X.getR(), [-4.1793, -4.9218, 1.7664])) #f2
    #print(F(X.getW(), X.getS(), X.getR(),  [-3.9429, -0.7689, 4.8830])) #f3

    print("OPTIMAL ARG:", myX)
    print("OPTIMAL ERROR:", E(myX))
    print("TIME: ", elapsed)

    """        x: array([   2.38222076,   49.66509128, -831.16883206,  -10.53020137,
              7.66220188,    7.86251335,   21.26905866,    7.44191062,
              7.74710959,    7.76952171,    7.82782936]) """


x0 = np.random.uniform(-1, 1, [11])

scipyOptimizeCG(x0)

