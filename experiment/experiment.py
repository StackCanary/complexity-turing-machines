import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

import os

import glob

csvs = glob.glob('*.csv')

dfs = [(i, pd.read_csv(i)) for i in csvs]

for name, df in dfs:

    filename, extension = os.path.splitext(name)
    
    plt.figure()
    plt.title(filename.title())
    plt.scatter(df['n'], df['steps'].values, s=20, c='blue')
    plt.ylabel('no. of steps')
    plt.xlabel('n')
    plt.savefig(filename + '_graph.png')


print('Complete!')    
