package com.huskybus.generators;

public class BusStop{
	public String description, textingKey;
	public double heading, latitude, longitude;
	public int addressID, maxZoomLevel, order, routeID, routeStopID, secondsAtStop, secondsToNextStop;
	public boolean showDefaultedOnMap, showEstimatesOnMap;
	public MapPoint[] mapPoints;

	public BusStop(){
		description = textingKey = null;
		latitude = longitude = heading = -1;
		addressID = maxZoomLevel = order = routeID = routeStopID = secondsAtStop = secondsToNextStop = -1;
		showDefaultedOnMap = showEstimatesOnMap = true;
		mapPoints = null;
	}

	public String toString(){
		return description;
	}
}