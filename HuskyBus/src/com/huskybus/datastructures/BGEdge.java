package com.huskybus.datastructures;

import android.util.Log;

class BGEdge {
	private String nextStopName;
	private BusRoute element;
	private RouteStop stopInfo;
	
	public BGEdge(String nextStopName, BusRoute element, RouteStop routeStop){
		this.setStopName(nextStopName);
		this.setElement(element);
		this.setStopInfo(routeStop);
		Log.d("bgedge", element.getDescription() + ": " + routeStop.getDescription() + " -> " + nextStopName);
	}

	public String getStopName() {
		return nextStopName;
	}

	public void setStopName(String vertexNum) {
		this.nextStopName = vertexNum;
	}

	public BusRoute getElement() {
		return element;
	}

	public void setElement(BusRoute element) {
		this.element = element;
	}

	public RouteStop getStopInfo() {
		return stopInfo;
	}

	public void setStopInfo(RouteStop stopInfo) {
		this.stopInfo = stopInfo;
	}
}