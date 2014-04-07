package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;


public class BusGraph<E, V>{

	private int size;
	private HashMap<String, BGVertex> adj;
	
	public BusGraph(){
		size = 0;
		adj = new HashMap<String, BGVertex>();
	}
	
	public void addStop(BusStop busStopStart, BusRoute busRoute, BusStop busStopEnd){
		if(adj.containsKey(busStopStart.getDescription())){
			adj.get(busStopStart.getDescription()).addRoute(busRoute, busStopEnd);
		}else{
			//adj.put(busStopStart.getDescription(), new BGVertex());
		}
	}
}