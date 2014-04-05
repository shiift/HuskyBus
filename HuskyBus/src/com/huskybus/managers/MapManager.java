package com.huskybus.managers;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MapManager extends Thread {

	// variables from the fragment
	private GoogleMap _map;
	private ArrayList<BusRoute> _initBusRoutes;
	
	// variables handled by this manager
	private ArrayList<BusRoute> _busRoutes;
	private MarkerManager _markerManager;
	
	public MapManager(GoogleMap map){
		_map = map;
		_busRoutes = new ArrayList<BusRoute>();
		_initBusRoutes = new ArrayList<BusRoute>();
		_markerManager = new MarkerManager(map);
	}
	
	@Override
	public void run(){
		for(int i = 0; i < _initBusRoutes.size(); i++){
			PolylineOptions polyLineOptions = new PolylineOptions();
			BusRoute cRoute = _initBusRoutes.get(i);
			
			polyLineOptions.color(Color.parseColor(cRoute.mapLineColor));
			polyLineOptions.visible(false);
			if(cRoute.description.matches("Blue")){
				polyLineOptions.visible(true);
			}
			
			for(int j = 0; j < cRoute.stops.length; j++){
				BusStop cStop = cRoute.stops[j];
				if(!cStop.textingKey.matches("ARJE")){
					for(int k = 0; k < cStop.mapPoints.length; k++){
						double lat = cStop.mapPoints[k].latitude;
						double lng = cStop.mapPoints[k].longitude;
							polyLineOptions.add(new LatLng(lat,lng));
					}
				}
				_markerManager.addMarker(cStop, cRoute);
			}
			Polyline polyline = _map.addPolyline(polyLineOptions);
			cRoute.polyline = polyline;
			_busRoutes.add(cRoute);
		}
	}
	
	public ArrayList<BusRoute> getRoutes(){
		return _busRoutes;
	}
	
	public ArrayList<MapMarker> getMarkers(){
		return _markerManager.getMarkers();
	}
	
	public MarkerManager getMarkerManager(){
		return _markerManager;
	}
	
	public void initBusRoutes(ArrayList<BusRoute> newBusRoutes){
		_initBusRoutes = newBusRoutes;
	}
	
}
