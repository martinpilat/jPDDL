import re
import sys
import os
import random

class cell:
   def __init__(self,x,y,s,r):
      self.x=x
      self.y=y
      self.s=s
      self.r=r


grid=[]
ResNo=0

def GenerateGrid(GridSize,Type,ResRnd,ShrRnd):
   global grid
   global ResNo
   for i in range(1,GridSize+1):
      for j in range(1,GridSize+1):
         if i==1 and j==1:
            grid.append(cell(1,1,1,0))
         else:
            #Types of grid
            if Type==1:
               s=int((i+j)%2==0)
            if Type==2:
               s=int(((i%2)+(j%2))==2)
            if Type==3:
               s=int(random.random()>ShrRnd)
            #determining a resource on a solid platform
            r=0
            if s==1:
               r=int(random.random()<=ResRnd)
            ResNo=ResNo+r  
            grid.append(cell(i,j,s,r)) 

try:
  assert len(sys.argv) >= 4
  #print(str(sys.argv))
  GridSize = int(sys.argv[1])
  Type = int(sys.argv[2])
  ResRnd = float(sys.argv[3])  
  ShrRnd = float(sys.argv[4])
  problem_number = int(sys.argv[5])
  GenerateGrid(GridSize, Type,ResRnd,ShrRnd)  
  print("(define (problem Perestroika-problem-{}-{}-{}-{}-{})".format(GridSize,Type,ResRnd,ShrRnd,problem_number))
  print("(:domain Perestroika)")
  print("(:objects ",end='')
  for o in range(1,ResNo+1):
      print("r{} ".format(o),end='')
  print("- resource")
  for i in range(1,GridSize+1):
      for j in range(1,GridSize+1):
         print("l-{}-{} ".format(i,j),end='')
  print("- location)")
  print("(:init (act-round)(alive)(at-agent l-1-1)")
  for i in range(1,GridSize+1):
      for j in range(1,GridSize+1):
         if i+1 <= GridSize:
            print("(connected l-{}-{} l-{}-{})".format(i,j,i+1,j))
            print("(connected l-{}-{} l-{}-{})".format(i+1,j,i,j))
         if j+1 <= GridSize:
            print("(connected l-{}-{} l-{}-{})".format(i,j,i,j+1))
            print("(connected l-{}-{} l-{}-{})".format(i,j+1,i,j))         
  rs=1
  for c in grid:
     print("(accessible l-{}-{})".format(c.x,c.y))
     if c.s==1:
        print("(solid l-{}-{})".format(c.x,c.y))
     else:
        print("({} l-{}-{})".format(random.choice(['small', 'medium', 'big']),c.x,c.y))
     if c.r==1:
        print("(at-res r{} l-{}-{})".format(rs,c.x,c.y))
        rs=rs+1
  print("(= (total-cost) 0))")
  print("(:goal (and (alive)")
  for o in range(1,ResNo+1):
     print("(taken r{})".format(o))
  print("))")
  print("(:metric minimize (total-cost))")
  print(")")

except AssertionError:
  print("usage: generator.py <GridSize> <Type (1=ChessBoard, 2=EvenRows and Columns Shrining, 3=Random> <probability Resource> <probability Shrinking> <problem number")
