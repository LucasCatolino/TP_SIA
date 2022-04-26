import tensorflow as tf
from tensorflow.python.training import gradient_descent
import numpy as np
import time


x = tf.Variable(tf.random.uniform(shape=[11], minval = -1, maxval= 1, dtype=tf.float64))

@tf.function
def f_x():

    #inner functions shennanigans
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

    def g(x_in):
        return tf.math.exp(x_in) / (1 + tf.math.exp(x_in))

    def F(W, S, R, xi):
        wd = 0
        sd = 0
        for j in [1, 2]:
            sd = 0
            for k in [0, 2]:
                sd += S[(j - 1) * 3 + k] * xi[k] - R[j - 1]

            wd += (W[j] * g(sd)) - W[0]

        return g(wd)

    def E(x_in):
        zeta = [0, 1, 1]
        xi_samples = [[4.4793, -4.0765, -4.0765], [-4.1793, -4.9218, 1.7664], [-3.9429, -0.7689, 4.8830]]
        ind = Individuo(x_in)
        out = 0
        for mu in [0, 2]:
            out += (zeta[mu] - F(ind.getW(), ind.getS(), ind.getR(), xi_samples[mu]))**2


        return out

    return E(x)
iter = 0
start = time.time()
MINIMUM = 0.0000001
MAX_ITER = 50000
while (f_x() > MINIMUM and iter < MAX_ITER):
    #print([x, f_x()])
    opt = gradient_descent.GradientDescentOptimizer(0.1).minimize(f_x) # GRADIENT DESCENT
    #opt =tf.keras.optimizers.Adam(0.1).minimize(f_x, var_list=[x])  # ADAM
    iter += 1

done = time.time()
elapsed =done - start

print("OPTIMAL ARG:", x)
print("OPTIMAL ERROR:", f_x())
print("TIME: ", elapsed)
print("ITER: ", iter)