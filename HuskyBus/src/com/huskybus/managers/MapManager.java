package com.huskybus.managers;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.huskybus.datastructures.BusGraph;
import com.huskybus.datastructures.BusRoute;
import com.huskybus.datastructures.BusStop;
import com.huskybus.datastructures.RouteStop;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class MapManager extends AsyncTask<Void, Void, ArrayList<BusStop>> {

	// variables from the fragment
	private MapManagerResponse _mmr;
	private ArrayList<BusRoute> _initBusRoutes;

	// variables handled by this manager
	private ArrayList<BusRoute> _busRoutes;
	private MarkerManager _markerManager;

	public MapManager(MapManagerResponse mmr){
		_mmr = mmr;
		_busRoutes = new ArrayList<BusRoute>();
		_initBusRoutes = new ArrayList<BusRoute>();
		_markerManager = new MarkerManager();
	}

	public void initBusRoutes(ArrayList<BusRoute> newBusRoutes){
		_initBusRoutes = newBusRoutes;
	}

	@Override
	protected ArrayList<BusStop> doInBackground(Void... params) {
		// For each BusRoute
		for(int i = 0; i < _initBusRoutes.size(); i++){
			PolylineOptions polylineOptions = new PolylineOptions();
			BusRoute cRoute = _initBusRoutes.get(i);

			Log.d("mapviewinit-buslines", "adding line: " + cRoute.getDescription());

			polylineOptions.color(Color.parseColor(cRoute.getMapLineColor()));
			polylineOptions.visible(false);
			
			// For each RouteStop
			for(int j = 0; j < cRoute.getStops().length; j++){
				RouteStop cStop = cRoute.getStops()[j];
				for(int k = 0; k < cStop.getMapPoints().length; k++){
					double lat = cStop.getMapPoints()[k].latitude;
					double lng = cStop.getMapPoints()[k].longitude;
					polylineOptions.add(new LatLng(lat,lng));
				}
				// Create the stop Marker (or add it to a current marker)
				BusStop newStop = _markerManager.addMarker(cStop, cRoute);
				cRoute.addBusStop(newStop);
			}
			cRoute.setPolylineOptions(polylineOptions);
			_busRoutes.add(cRoute);
		}
		return _markerManager.getMarkers();
	}

	protected void onPostExecute(ArrayList<BusStop> result){
		_mmr.addLines(_busRoutes, result);
	}

	public interface MapManagerResponse{
		void addLines(ArrayList<BusRoute> busRoutes, ArrayList<BusStop> mapMarkers);
	}

}
