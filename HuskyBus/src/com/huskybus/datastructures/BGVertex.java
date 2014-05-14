package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;


public class BGVertex {
	private BusStop element;
	private HashMap<Integer, BGEdge> routeHash;

	public BGVertex(BusStop busStop){
		this.setStop(busStop);
		this.routeHash = new HashMap<Integer, BGEdge>();
	}

	public BusStop getStop() {
		return element;
	}

	public void setStop(BusStop element) {
		this.element = element;
	}
	
	public String getNextStopName(BusRoute busRoute){
		return routeHash.get(busRoute).getStopName();
	}
	
	public void addRoute(BusStop busStopEnd, BusRoute busRoute, RouteStop routeStop){
		routeHash.put(busRoute.getRouteID(), new BGEdge(busStopEnd.getDescription(), busRoute, routeStop));
	}
}