package com.huskybus.managers;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.huskybus.MultiSpinner;
import com.huskybus.R;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MapManager {

	GoogleMap _map;
	ArrayList<BusRoute> _busRoutes;
	MarkerManager _markerManager;
	
	public MapManager(GoogleMap map){
		_map = map;
		_busRoutes = new ArrayList<BusRoute>();
		_markerManager = new MarkerManager(map);
	}
	
	public void createLines(ArrayList<BusRoute> busRoutes){
		for(int i = 0; i < busRoutes.size(); i++){
			PolylineOptions polyLineOptions = new PolylineOptions();
			BusRoute cRoute = busRoutes.get(i);
			
			polyLineOptions.color(Color.parseColor(cRoute.mapLineColor));
			polyLineOptions.visible(true);
			
			for(int j = 0; j < cRoute.stops.length; j++){
				BusStop cStop = cRoute.stops[j];
				for(int k = 0; k < cStop.mapPoints.length; k++){
					double lat = cStop.mapPoints[k].latitude;
					double lng = cStop.mapPoints[k].longitude;
					polyLineOptions.add(new LatLng(lat,lng));
				}
				_markerManager.addMarker(cStop, cRoute);
			}
			Polyline polyline = _map.addPolyline(polyLineOptions);
			cRoute.polyline = polyline;
			_busRoutes.add(cRoute);
		}
		String s = _markerManager.toString();
	}
	
}
