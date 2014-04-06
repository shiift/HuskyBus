package com.huskybus.generators;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class BusRoute{
	private String description;
	private String mapLineColor;
	private String textingKey;
	private boolean hideRouteLine;
	private boolean isCheckedOnMap;
	private boolean isVisibleOnMap;
	private boolean showPolygon;
	private double mapLatitude;
	private double mapLongitude;
	private int mapZoom;
	private int order;
	private int routeID;
	private BusStop[] stops;
	public PolylineOptions polylineOptions;
	public Polyline polyline;

	public BusRoute(){
		setDescription(setMapLineColor(setTextingKey(null)));
		setHideRouteLine(setCheckedOnMap(setShowPolygon(false)));
		setVisibleOnMap(true);
		setMapLatitude(setMapLongitude(-1));
		setMapZoom(setOrder(setRouteID(-1)));
		setStops(null);
	}

	@Override
	public String toString(){
		return getDescription() + "(" + getRouteID() + ")";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTextingKey() {
		return textingKey;
	}

	public String setTextingKey(String textingKey) {
		this.textingKey = textingKey;
		return textingKey;
	}

	public String getMapLineColor() {
		return mapLineColor;
	}

	public String setMapLineColor(String mapLineColor) {
		this.mapLineColor = mapLineColor;
		return mapLineColor;
	}

	public boolean isHideRouteLine() {
		return hideRouteLine;
	}

	public void setHideRouteLine(boolean hideRouteLine) {
		this.hideRouteLine = hideRouteLine;
	}

	public boolean isCheckedOnMap() {
		return isCheckedOnMap;
	}

	public boolean setCheckedOnMap(boolean isCheckedOnMap) {
		this.isCheckedOnMap = isCheckedOnMap;
		return isCheckedOnMap;
	}

	public boolean isVisibleOnMap() {
		return isVisibleOnMap;
	}

	public void setVisibleOnMap(boolean isVisibleOnMap) {
		this.isVisibleOnMap = isVisibleOnMap;
	}

	public boolean isShowPolygon() {
		return showPolygon;
	}

	public boolean setShowPolygon(boolean showPolygon) {
		this.showPolygon = showPolygon;
		return showPolygon;
	}

	public double getMapLatitude() {
		return mapLatitude;
	}

	public void setMapLatitude(double mapLatitude) {
		this.mapLatitude = mapLatitude;
	}

	public double getMapLongitude() {
		return mapLongitude;
	}

	public double setMapLongitude(double mapLongitude) {
		this.mapLongitude = mapLongitude;
		return mapLongitude;
	}

	public int getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(int mapZoom) {
		this.mapZoom = mapZoom;
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

	public BusStop[] getStops() {
		return stops;
	}

	public void setStops(BusStop[] stops) {
		this.stops = stops;
	}
}