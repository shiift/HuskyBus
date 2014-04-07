package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;


public class BusGraph<E, V>{

	private HashMap<String, BGVertex> adj;
	
	public BusGraph(){
		adj = new HashMap<String, BGVertex>();
	}
	
	public void addStop(BusStop busStopStart, BusStop busStopEnd, BusRoute busRoute, RouteStop routeStop){
		if(adj.containsKey(busStopStart.getDescription())){
			adj.get(busStopStart.getDescription()).addRoute(busStopEnd, busRoute, routeStop);
		}else{
			adj.put(busStopStart.getDescription(), new BGVertex(busStopStart));
			adj.get(busStopStart.getDescription()).addRoute(busStopEnd, busRoute, routeStop);
		}
	}
	
	public BusStop getNextStop(BusStop busStop, int routeID){
		return adj.get(adj.get(busStop.getDescription()).getNextStopName(routeID)).getStop();
	}
}