package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;


public class BGVertex {
	private BusStop element;
	private HashMap<Integer, BGEdge> adjacent;

	public BGVertex(BusStop busStop){
		this.setStop(busStop);
		this.adjacent = new HashMap<Integer, BGEdge>();
	}

	public BusStop getStop() {
		return element;
	}

	public void setStop(BusStop element) {
		this.element = element;
	}
	
	public String getNextStopName(int routeID){
		return adjacent.get(routeID).getStopName();
	}
	
	public void addRoute(BusStop busStopEnd, BusRoute busRoute, RouteStop routeStop){
		adjacent.put(busRoute.getRouteID(), new BGEdge(busStopEnd.getDescription(), busRoute, routeStop));
	}
}