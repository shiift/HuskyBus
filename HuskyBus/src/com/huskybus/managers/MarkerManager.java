package com.huskybus.managers;

import java.util.ArrayList;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huskybus.R;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MarkerManager{
	private ArrayList<MapMarker> _markers;
	private GoogleMap _map;
	
	public MarkerManager(GoogleMap map){
		_markers = new ArrayList<MapMarker>();
		_map = map;
	}
	
	public void addMarker(BusStop busStop, BusRoute busRoute){
		int mPosition = getStopMarkerIndex(busStop);
		if(mPosition != -1){			// marker already in array at position mPosition
			MapMarker mapMarker = _markers.get(mPosition);
			mapMarker.routes.add(busRoute);
			if(mapMarker.textingKey.equals("") && !busStop.textingKey.equals("")){
				mapMarker.textingKey = busStop.textingKey;
				mapMarker.marker.setTitle(generateMarkerTitle(busStop.description, busStop.textingKey));
			}
			if(mapMarker.description.length() < busStop.description.length()){
				mapMarker.description = busStop.description;
				mapMarker.marker.setTitle(generateMarkerTitle(busStop.description, busStop.textingKey));
			}
		}else{							// marker not in array
			MapMarker mapMarker = new MapMarker();
			copyStopInfo(busStop, mapMarker);
			mapMarker.routes.add(busRoute);
			MarkerOptions newOptions = new MarkerOptions()
											.anchor(0.5f, 1.0f)
											.position(new LatLng(busStop.latitude, busStop.longitude))
											.title(generateMarkerTitle(busStop.description, busStop.textingKey))
											.icon(BitmapDescriptorFactory.fromResource(R.drawable.stopmarker))
											.visible(true);
			Marker newMarker = _map.addMarker(newOptions);
			mapMarker.marker = newMarker;
			_markers.add(mapMarker);
		}
	}
	
	private String generateMarkerTitle(String description, String textingKey){
		if(textingKey.equals("")){
			return description;
		}
		if(description.equals("")){
			return textingKey;
		}
		return description + " (" + textingKey + ")";
	}

	private void copyStopInfo(BusStop cStop, MapMarker stop) {
		stop.description 	= cStop.description;
		stop.textingKey 	= cStop.textingKey;
		stop.latitude		= cStop.latitude;
		stop.longitude		= cStop.longitude;
	}

	private int getStopMarkerIndex(BusStop cStop) {
		for(int i = 0; i < _markers.size(); i++){
			if(isStopInMapMarker(cStop, _markers.get(i))){
				return i;
			}
		}
		return -1;
	}
	
	private boolean isStopInMapMarker(BusStop stop, MapMarker mapMarker){
		String description1 = stop.description.replaceAll("\\W", "");
		String description2 = mapMarker.description.replaceAll("\\W", "");
		if(description1.equalsIgnoreCase(description2)){
			return true;
		}
		if(stop.textingKey.equalsIgnoreCase(mapMarker.textingKey) ||
				(stop.textingKey.equals("") != mapMarker.textingKey.equals(""))){
			if(stop.latitude == mapMarker.latitude && stop.longitude == mapMarker.longitude){
				return true;
			}
			if(!stop.textingKey.equals("") && !mapMarker.textingKey.equals("")){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MapMarker> getMarkers(){
		return _markers;
	}
	
	public String toString(){
		String output = "";
		for(int i = 0; i < _markers.size(); i++){
			output += _markers.get(i).description + " [" + _markers.get(i).textingKey + "] (" + _markers.get(i).marker.getPosition().latitude + ", " + _markers.get(i).marker.getPosition().longitude + "): ";
			for(int j = 0; j < _markers.get(i).routes.size(); j++){
				output += _markers.get(i).routes.get(j).description + ", ";
			}
			output += "\n";
		}
		return output;
	}
	
	public void printToLog(){
		for(int i = 0; i < _markers.size(); i++){
			String output = "";
			output += _markers.get(i).description + " [" + _markers.get(i).textingKey + "] (" + _markers.get(i).marker.getPosition().latitude + ", " + _markers.get(i).marker.getPosition().longitude + "): ";
			for(int j = 0; j < _markers.get(i).routes.size(); j++){
				output += _markers.get(i).routes.get(j).description + ", ";
			}
			Log.d("mapinit", output);
		}
	}
}
