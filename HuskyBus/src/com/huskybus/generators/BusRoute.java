package com.huskybus.generators;

public class BusRoute{
	public String description, mapLineColor, textingKey;
	public boolean hideRouteLine, isCheckedOnMap, isVisibleOnMap, showPolygon;
	public double mapLatitude, mapLongitude;
	public int mapZoom, order, routeID;
	public BusStop[] stops;
	
	public BusRoute(){
		description = mapLineColor = textingKey = null;
		hideRouteLine = isCheckedOnMap = showPolygon = false;
		isVisibleOnMap = true;
		mapLatitude = mapLongitude = -1;
		mapZoom = order = routeID = -1;
		stops = null;
	}
	
	@Override
	public String toString(){
		return description + "(" + routeID + ")";
	}
}