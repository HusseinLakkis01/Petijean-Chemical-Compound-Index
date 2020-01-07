#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from collections import defaultdict
import re
#import networkx as x
import os
class graph:
    
    
    # constructor for the graph
    def __init__(self):
          self.graph=defaultdict(list)
    
    
    # method that adds a pair of vertices into the graph      
    def addedge(self,u,v):   
        self.graph[u].append(v)
        self.graph[v].append(u)
        
        
    # method that gets the eccentricity of a vertix in the graph
    def eccentricity(self,s):
        # initialize eccentricity to 0
        eccentricity = 0
        # create a queue that will be used in the bfs call on thev vertix
        queue = []
        # create a distance list that will store the distance from s to others
        # initialize it to 0
        disto = [0] * len(self.graph)
        # create a list that will keep track if the visited vertices
        #all vertices are not visited at first
        visited = [False for i in range(len(self.graph))]
        # add s to the queue
        queue.append(s)
        # while the queue is not empty do:
        while(queue):
            # extract the vertix in the front
            v = queue.pop(0)
         
            # visit all non visited neighbors and update the distance to each
            # the distance to the next vertix is the distance to the current 
            # plus one
            for w in self.graph[v]:
                if visited[w] == False:
                    disto[w] = disto[v] + 1
                    queue.append(w)
                    # mark the vertix as visited
                    visited[w] = True
            # to get the eccentricity, get the max distance in the list         
            for i in range(len(disto)):
                if disto[i] > eccentricity:
                    eccentricity = disto[i]
                    
          
        return eccentricity
    
    
    
    def diameter(self):
        """ method the runs the eccentricity method on all vertices
        and returns the diameter of the graph"""
        
        #initialize the list values to 0 
        Eccentricity_list = [0 for i in range(len(self.graph))]  
        
        for v in range (len(self.graph)):
            
            #run the method
            Eccentricity_list[v] = self.eccentricity(v)
           
            
        return max(Eccentricity_list)     
      
        
        
    def radius(self):
        """ method the runs the eccentricity method on all vertices
        and returns the radius of the graph"""
        
        Eccentricity_list = [0 for i in range(len(self.graph))]  
        
        for v in range (len(self.graph)):
             Eccentricity_list[v] = self.eccentricity(v)
             
        return min(Eccentricity_list)
        
    
    def petijean(self):
        """ method that computes the Petijean index of a graph,
        be ware of the python edition you have to avoid integer division"""
        
        return ((self.diameter()-self.radius())/self.radius())
 
# EXAMPLE CYCLOHEXANE   
        
g=graph()   

g.addedge(0, 1)  
g.addedge(1, 2) 
g.addedge(2, 3) 
g.addedge(3, 4) 
g.addedge(4, 5)
g.addedge(5, 0)

print("Petijean index of cyclohexane is: ")     
print(g.petijean())


# change woring directory
os.chdir('/Users/husseinalilakkis/Desktop/CHEM')
# list all files in the directory
for filename in os.listdir():
    #array to store the bonds vor edge
    array = []
    # read the file
    with open(filename,'r') as f:
        #skip first 2 lines
        next(f)
        next(f)
        
        for line in f:
            line = line.strip() # strip end-on-line
            #use regexp to matcfh lines that contain bonds, but delete 
            # the first 2 matched as these are the atom and bond numbers\
            
            a=re.match(r"^(\d+) {1,2}(\d+)", line)
            # if we have a match, store in the array
            if a:
               array.append(a.group(1))
               array.append(a.group(2))
        # DELETE FIRST 2       
        del(array[0:2])      
        # cast the array to integers 
        array = [int(i)-1 for i in array]
        # print(array) use this to check if bonds are correct or not
        Molecular_Graph = graph()
        # add to the graph each pair of consecutive vertices as edges
        # make the for loop step 2 instead of one
        for i in range(0, len(array),2):
            Molecular_Graph.addedge(i, i+1)
            
        print(filename + "has a Petijean index of :" 
              + Molecular_Graph.petijean())
            
            

               
               
               
               
