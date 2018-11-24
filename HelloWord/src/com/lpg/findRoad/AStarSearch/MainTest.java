package com.lpg.findRoad.AStarSearch;

public class MainTest { 
    
	  public static void main(String[] args) { 
	    MyMap map = new MyMap(); 
	    AStar aStar = new AStar(); 
	    map.ShowMap(); 
	    aStar.search(map); 
	    System.out.println("============================="); 
	    System.out.println("经过A*算法计算后"); 
	    System.out.println("============================="); 
	    map.ShowMap();  
	  } 
	}
