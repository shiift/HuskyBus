package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;


public class BGVertex {
	private BusStop element;
	private HashMap<Integer, BGEdge> adjacent;

	public BGVertex(BusStop element, HashMap<Integer, BGEdge> adjacent){
		this.setElement(element);
		this.adjacent = adjacent;
	}

	public BusStop getElement() {
		return element;
	}

	public void setElement(BusStop element) {
		this.element = element;
	}
	
	public void addRoute(BusRoute busRoute, BusStop busStopEnd){
		
//		adjacent.put(busRoute.getRouteID(), new BGEdge(busRoute));
	}
}