package com.huskybus.managers;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MarkerManager {
	private ArrayList<MapMarker> _stops;
	private GoogleMap _map;
	
	public MarkerManager(GoogleMap map){
		_stops = new ArrayList<MapMarker>();
		_map = map;
	}
	
	public void addMarker(BusStop busStop, BusRoute busRoute){
		int mPosition = getStopMarkerIndex(busStop);
		if(mPosition != -1){	// marker already in array at position mPosition
			MapMarker mapMarker = _stops.get(mPosition);
			mapMarker.routes.add(busRoute);
		}else{					// marker not in array
			MapMarker mapMarker = new MapMarker();
			copyStopInfo(busStop, mapMarker);
			mapMarker.routes.add(busRoute);
			Marker newMarker = _map.addMarker(new MarkerOptions()
								.anchor(0.5f, 1.0f)
								.position(new LatLng(busStop.latitude, busStop.longitude))
								.title(busStop.textingKey)
								.visible(true));
			mapMarker.marker = newMarker;
		}
	}

	private void copyStopInfo(BusStop cStop, MapMarker stop) {
		stop.description 	= cStop.description;
		stop.textingKey 	= cStop.textingKey;
	}

	private int getStopMarkerIndex(BusStop cStop) {
		for(int i = 0; i < _stops.size(); i++){
			if(_stops.get(i).textingKey.equals(cStop.textingKey)){
				return i;
			}
		}
		return -1;
	}
	
	public String toString(){
		String output = "";
		for(int i = 0; i < _stops.size(); i++){
			output += _stops.get(i).textingKey + ": ";
			for(int j = 0; j < _stops.get(i).routes.size(); j++){
				output += _stops.get(i).routes.get(j).description + ", ";
			}
			output += "\n";
		}
		return output;
	}
}
