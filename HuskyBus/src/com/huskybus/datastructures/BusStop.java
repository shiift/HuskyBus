package com.huskybus.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class BusStop {

	private String description;
	private String textingKey;
	private double longitude;
	private double latitude;
	
	private HashMap<Integer, BusRoute> busRoutes;
	private HashMap<Integer, RouteStop> stopInfo;
	
	private MarkerOptions markerOptions;
	private Marker marker; 

	public BusStop(){
		setDescription("");
		setLongitude(0);
		setLatitude(0);
		
		busRoutes = new HashMap<Integer, BusRoute>();
		stopInfo = new HashMap<Integer, RouteStop>();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the textingKey
	 */
	public String getTextingKey() {
		return textingKey;
	}

	/**
	 * @param textingKey the textingKey to set
	 */
	public void setTextingKey(String textingKey) {
		this.textingKey = textingKey;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the markerOptions
	 */
	public MarkerOptions getMarkerOptions() {
		return markerOptions;
	}

	/**
	 * @param markerOptions the markerOptions to set
	 */
	public void setMarkerOptions(MarkerOptions markerOptions) {
		this.markerOptions = markerOptions;
	}

	/**
	 * @return the marker
	 */
	public Marker getMarker() {
		return marker;
	}

	/**
	 * @param marker the marker to set
	 */
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	public HashMap<Integer, BusRoute> getRotues(){
		return busRoutes;
	}

	public void copyFrom(RouteStop busStop) {
		setDescription(busStop.getDescription());
		setTextingKey(busStop.getTextingKey());
		setLatitude(busStop.getLatitude());
		setLongitude(busStop.getLongitude());
	}
	
	public void addBusRoute(BusRoute busRoute, RouteStop routeStop){
		busRoutes.put(busRoute.getRouteID(), busRoute);
//		Log.d("markermanager", busRoute.getDescription() + ": " + this.getDescription() + " :: " + routeStop.getDescription());
		stopInfo.put(busRoute.getRouteID(), routeStop);
	}
	
	public BusRoute getBusRoute(BusRoute busRoute){
		return busRoutes.get(busRoute.getRouteID());
	}
	
	public RouteStop getRouteStop(int routeID){
		return stopInfo.get(routeID);
	}
	
	public boolean containsBusRoute(int routeID){
		return busRoutes.containsKey(routeID);
	}
}
