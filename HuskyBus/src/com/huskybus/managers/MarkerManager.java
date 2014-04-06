package com.huskybus.managers;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huskybus.R;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MarkerManager{
	private ArrayList<MapMarker> _markers;
	
	public MarkerManager(){
		_markers = new ArrayList<MapMarker>();
	}
	
	public void addMarker(BusStop busStop, BusRoute busRoute){
		int mPosition = getStopMarkerIndex(busStop);
		if(mPosition != -1){			// marker already in array at position mPosition
			MapMarker mapMarker = _markers.get(mPosition);
			mapMarker.getRoutes().add(busRoute);
			if(mapMarker.getTextingKey().equals("") && !busStop.getTextingKey().equals("")){
				mapMarker.setTextingKey(busStop.getTextingKey());
				mapMarker.getMarkerOptions().title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()));
			}
			if(mapMarker.getDescription().length() < busStop.getDescription().length()){
				mapMarker.setDescription(busStop.getDescription());
				mapMarker.getMarkerOptions().title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()));
			}
		}else{							// marker not in array
			MapMarker mapMarker = new MapMarker();
			copyStopInfo(busStop, mapMarker);
			mapMarker.getRoutes().add(busRoute);
			MarkerOptions newOptions = new MarkerOptions()
											.anchor(0.5f, 1.0f)
											.position(new LatLng(busStop.getLatitude(), busStop.getLongitude()))
											.title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()))
											.icon(BitmapDescriptorFactory.fromResource(R.drawable.stopmarker))
											.visible(true);
			mapMarker.setMarkerOptions(newOptions);
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
		stop.setDescription(cStop.getDescription());
		stop.setTextingKey(cStop.getTextingKey());
		stop.setLatitude(cStop.getLatitude());
		stop.setLongitude(cStop.getLongitude());
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
		String description1 = stop.getDescription().replaceAll("\\W", "");
		String description2 = mapMarker.getDescription().replaceAll("\\W", "");
		if(description1.equalsIgnoreCase(description2)){
			return true;
		}
		if(stop.getTextingKey().equalsIgnoreCase(mapMarker.getTextingKey()) ||
				(stop.getTextingKey().equals("") != mapMarker.getTextingKey().equals(""))){
			if(stop.getLatitude() == mapMarker.getLatitude() && stop.getLongitude() == mapMarker.getLongitude()){
				return true;
			}
			if(!stop.getTextingKey().equals("") && !mapMarker.getTextingKey().equals("")){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MapMarker> getMarkers(){
		return _markers;
	}
}
