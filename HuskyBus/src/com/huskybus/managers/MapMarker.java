package com.huskybus.managers;

import java.util.ArrayList;

import com.google.android.gms.maps.model.Marker;
import com.huskybus.generators.BusRoute;

public class MapMarker {

	public String description, textingKey;
	public Marker marker;
	public ArrayList<BusRoute> routes;
	
	public MapMarker(){
		routes = new ArrayList<BusRoute>();
		description = "";
	}

}
