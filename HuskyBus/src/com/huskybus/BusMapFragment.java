package com.huskybus;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.huskybus.MultiSpinner.MultiSpinnerListener;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.GetRoutesTask;
import com.huskybus.generators.GetRoutesTask.AsyncResponse;
import com.huskybus.managers.MapManager;
import com.huskybus.managers.MapManager.MapManagerResponse;
import com.huskybus.managers.MapMarker;

public class BusMapFragment extends Fragment implements AsyncResponse, MultiSpinnerListener, MapManagerResponse{

	//threads and tasks
	MapManager _mapManager;
	GoogleMap _map;
	GetRoutesTask _dft;

	//other member variables
	View _rootView;

	public BusMapFragment() {
		_dft = null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_busmap, container,
				false);
		_rootView = rootView;

		Log.d("mapviewinit", "getting routes from web");

		_dft = new GetRoutesTask(getActivity(), this);
		_dft.execute();

		Log.d("mapviewinit", "creating map manager");

		FragmentManager manager = getActivity().getSupportFragmentManager();
		SupportMapFragment mapFragment =(SupportMapFragment) manager.findFragmentById(R.id.busmap);
		_map = mapFragment.getMap();
		adjustMap(_map);

		_mapManager = new MapManager(this);

		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
	}

	public void adjustMap(GoogleMap map){
		map.moveCamera(CameraUpdateFactory.zoomTo(14));
		map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41.81,-72.254)));
		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}

	public void createLines(ArrayList<BusRoute> busRoutes){
		Log.d("mapviewinit-buslines","adding bus lines to the map");
		_mapManager.initBusRoutes(busRoutes);
		_mapManager.execute();
		Log.d("mapviewinit-buslines", "done adding bus lines");
	}

	@Override
	public void processFinish(ArrayList<BusRoute> busRoutes) {
		if(busRoutes == null){
			new AlertDialog.Builder(this.getActivity())
			.setTitle("Could not connect to the network")
			.setMessage("Press okay to retry")
			.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					// Do nothing
				}
			})
			.setPositiveButton(com.huskybus.R.string.retry, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					_dft.execute();
				}
			})
			.show();

			//			MultiSpinner ms = (MultiSpinner) _rootView.findViewById(R.id.multi_spinner);
			//			ms.setItems(null, "No Routes Found", this);
		}else{
			createLines(busRoutes);
		}
	}

	@Override
	public void onItemsSelected(boolean[] selected) {
		//		for(int i = 0; i < _lineList.size(); i++){
		//			Polyline cLine = _lineList.get(i);
		//			cLine.setVisible(selected[i]);
		//			for(int j = 0; j < _lineToMarker.get(cLine).size(); j++){
		//				_lineToMarker.get(cLine).get(j).setVisible(selected[i]);
		//			}
		//		}
	}

	@Override
	public void addLines(ArrayList<BusRoute> busRoutes, ArrayList<MapMarker> mapMarkers){
		for(int i = 0; i < busRoutes.size(); i++){
			_map.addPolyline(busRoutes.get(i).polylineOptions);
		}
		for(int i = 0; i < mapMarkers.size(); i++){
			Log.d("mapviewinit", mapMarkers.get(i).getDescription());
			_map.addMarker(mapMarkers.get(i).getMarkerOptions());
		}
	}

}