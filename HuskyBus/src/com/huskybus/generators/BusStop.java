package com.huskybus.generators;

public class BusStop{
	private String description;
	private String textingKey;
	private double heading;
	private double latitude;
	private double longitude;
	private int addressID;
	private int maxZoomLevel;
	private int order;
	private int routeID;
	private int routeStopID;
	private int secondsAtStop;
	private int secondsToNextStop;
	private boolean showDefaultedOnMap;
	private boolean showEstimatesOnMap;
	private MapPoint[] mapPoints;

	public BusStop(){
		setDescription(setTextingKey(null));
		setLatitude(setLongitude(setHeading(-1)));
		setAddressID(setMaxZoomLevel(setOrder(setRouteID(setRouteStopID(setSecondsAtStop(setSecondsToNextStop(-1)))))));
		setShowDefaultedOnMap(setShowEstimatesOnMap(true));
		setMapPoints(null);
	}

	public String toString(){
		return getDescription();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getTextingKey() {
		return textingKey;
	}

	public String setTextingKey(String textingKey) {
		this.textingKey = textingKey;
		return textingKey;
	}

	public double getLongitude() {
		return longitude;
	}

	public double setLongitude(double longitude) {
		this.longitude = longitude;
		return longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHeading() {
		return heading;
	}

	public double setHeading(double heading) {
		this.heading = heading;
		return heading;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public int getMaxZoomLevel() {
		return maxZoomLevel;
	}

	public int setMaxZoomLevel(int maxZoomLevel) {
		this.maxZoomLevel = maxZoomLevel;
		return maxZoomLevel;
	}

	public int getOrder() {
		return order;
	}

	public int setOrder(int order) {
		this.order = order;
		return order;
	}

	public int getRouteID() {
		return routeID;
	}

	public int setRouteID(int routeID) {
		this.routeID = routeID;
		return routeID;
	}

	public int getRouteStopID() {
		return routeStopID;
	}

	public int setRouteStopID(int routeStopID) {
		this.routeStopID = routeStopID;
		return routeStopID;
	}

	public int getSecondsAtStop() {
		return secondsAtStop;
	}

	public int setSecondsAtStop(int secondsAtStop) {
		this.secondsAtStop = secondsAtStop;
		return secondsAtStop;
	}

	public int getSecondsToNextStop() {
		return secondsToNextStop;
	}

	public int setSecondsToNextStop(int secondsToNextStop) {
		this.secondsToNextStop = secondsToNextStop;
		return secondsToNextStop;
	}

	public boolean isShowDefaultedOnMap() {
		return showDefaultedOnMap;
	}

	public void setShowDefaultedOnMap(boolean showDefaultedOnMap) {
		this.showDefaultedOnMap = showDefaultedOnMap;
	}

	public boolean isShowEstimatesOnMap() {
		return showEstimatesOnMap;
	}

	public boolean setShowEstimatesOnMap(boolean showEstimatesOnMap) {
		this.showEstimatesOnMap = showEstimatesOnMap;
		return showEstimatesOnMap;
	}

	public MapPoint[] getMapPoints() {
		return mapPoints;
	}

	public void setMapPoints(MapPoint[] mapPoints) {
		this.mapPoints = mapPoints;
	}
}