package com.huskybus.datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import android.util.Log;


public class BusGraph<E, V>{

	private HashMap<String, BGVertex> stopHash;
	
	public BusGraph(){
		stopHash = new HashMap<String, BGVertex>();
	}
	
	public void addStop(BusStop busStopStart, BusStop busStopEnd, BusRoute busRoute){
		if(stopHash.containsKey(busStopStart.getDescription())){
			stopHash.get(busStopStart.getDescription()).addRoute(busStopEnd, busRoute, busStopStart.getRouteStop(busRoute.getRouteID()));
		}else{
			stopHash.put(busStopStart.getDescription(), new BGVertex(busStopStart));
			stopHash.get(busStopStart.getDescription()).addRoute(busStopEnd, busRoute, busStopStart.getRouteStop(busRoute.getRouteID()));
		}
	}
	
	public BusStop getNextStop(BusStop busStop, BusRoute busRoute){
		return stopHash.get(stopHash.get(busStop.getDescription()).getNextStopName(busRoute)).getStop();
	}
	
	public void printLog(BusRoute busRotue){
		stopHash.get(busRotue.getBusStops().get(0).getDescription());
	}
}