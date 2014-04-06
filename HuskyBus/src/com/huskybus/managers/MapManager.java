package com.huskybus.managers;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;

public class MapManager extends AsyncTask<Void, Void, ArrayList<MapMarker>> {

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
	protected ArrayList<MapMarker> doInBackground(Void... params) {
		for(int i = 0; i < _initBusRoutes.size(); i++){
			PolylineOptions polylineOptions = new PolylineOptions();
			BusRoute cRoute = _initBusRoutes.get(i);

			Log.d("mapviewinit-buslines", "adding line: " + cRoute.getDescription());

			polylineOptions.color(Color.parseColor(cRoute.getMapLineColor()));
			polylineOptions.visible(false);

			for(int j = 0; j < cRoute.getStops().length; j++){
				BusStop cStop = cRoute.getStops()[j];
				for(int k = 0; k < cStop.getMapPoints().length; k++){
					double lat = cStop.getMapPoints()[k].latitude;
					double lng = cStop.getMapPoints()[k].longitude;
					polylineOptions.add(new LatLng(lat,lng));
				}
				_markerManager.addMarker(cStop, cRoute);
			}
			cRoute.setPolylineOptions(polylineOptions);
			_busRoutes.add(cRoute);
		}
		return _markerManager.getMarkers();
	}

	protected void onPostExecute(ArrayList<MapMarker> result){
		_mmr.addLines(_busRoutes, result);
	}

	public interface MapManagerResponse{
		void addLines(ArrayList<BusRoute> busRoutes, ArrayList<MapMarker> mapMarkers);
	}

}
