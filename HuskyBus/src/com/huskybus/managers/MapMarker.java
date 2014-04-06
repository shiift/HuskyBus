package com.huskybus.managers;

import java.util.ArrayList;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huskybus.generators.BusRoute;

public class MapMarker {

	private String description;
	private String textingKey;
	private ArrayList<BusRoute> routes;
	private double longitude;
	private double latitude;
	private MarkerOptions markerOptions;
	private Marker marker; 

	public MapMarker(){
		setRoutes(new ArrayList<BusRoute>());
		setDescription("");
		setLongitude(0);
		setLatitude(0);
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
	 * @return the routes
	 */
	public ArrayList<BusRoute> getRoutes() {
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(ArrayList<BusRoute> routes) {
		this.routes = routes;
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

}
