package com.huskybus.managers;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huskybus.R;
import com.huskybus.datastructures.BusRoute;
import com.huskybus.datastructures.BusStop;
import com.huskybus.datastructures.RouteStop;

public class MarkerManager{
	private ArrayList<BusStop> _markers;

	public MarkerManager(){
		_markers = new ArrayList<BusStop>();
	}

	public BusStop addMarker(RouteStop busStop, BusRoute busRoute){
		int mPosition = getStopMarkerIndex(busStop);
		BusStop mapMarker;
		if(mPosition != -1){			// marker already in array at position mPosition
			mapMarker = _markers.get(mPosition);
			mapMarker.addBusRoute(busRoute, busStop);
			if(mapMarker.getTextingKey().equals("") && !busStop.getTextingKey().equals("")){
				mapMarker.setTextingKey(busStop.getTextingKey());
				mapMarker.getMarkerOptions().title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()));
			}
			if(mapMarker.getDescription().length() < busStop.getDescription().length()){
				mapMarker.setDescription(busStop.getDescription());
				mapMarker.getMarkerOptions().title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()));
			}
		}else{							// marker not in array
			mapMarker = new BusStop();
			mapMarker.copyFrom(busStop);
			mapMarker.addBusRoute(busRoute, busStop);
			MarkerOptions newOptions = new MarkerOptions()
				.anchor(0.5f, 1.0f)
				.position(new LatLng(busStop.getLatitude(), busStop.getLongitude()))
				.title(generateMarkerTitle(busStop.getDescription(), busStop.getTextingKey()))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.stopmarker))
				.visible(false);
			mapMarker.setMarkerOptions(newOptions);
			_markers.add(mapMarker);
		}
		return mapMarker;
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

	private int getStopMarkerIndex(RouteStop cStop) {
		for(int i = 0; i < _markers.size(); i++){
			if(isStopInMapMarker(cStop, _markers.get(i))){
				return i;
			}
		}
		return -1;
	}

	public static boolean isStopInMapMarker(RouteStop stop, BusStop mapMarker){
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

	public ArrayList<BusStop> getMarkers(){
		return _markers;
	}
}
