package com.huskybus.datastructures;

class BGEdge {
	private String stopName;
	private BusRoute element;
	private RouteStop stopInfo;
	
	public BGEdge(String stopName, BusRoute element, RouteStop stopInfo){
		this.setStopName(stopName);
		this.setElement(element);
		this.setStopInfo(stopInfo);
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String vertexNum) {
		this.stopName = vertexNum;
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