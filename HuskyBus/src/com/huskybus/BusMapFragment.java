package com.huskybus;

import java.util.ArrayList;
import java.util.LinkedList;

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
import com.huskybus.datastructures.BusRoute;
import com.huskybus.datastructures.BusStop;
import com.huskybus.managers.MapManager;
import com.huskybus.managers.MapManager.MapManagerResponse;
import com.huskybus.tools.GetRoutesTask;
import com.huskybus.tools.GetRoutesTask.AsyncResponse;

public class BusMapFragment extends Fragment implements AsyncResponse, MultiSpinnerListener, MapManagerResponse{

	//threads and tasks
	private MapManager _mapManager;
	private GoogleMap _map;
	private GetRoutesTask _dft;
	private MultiSpinner _ms;

	//other member variables
	private View _rootView;
	private ArrayList<BusRoute> _busRoutes;
	private ArrayList<BusStop> _mapMarkers;

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

		_dft = new GetRoutesTask(getActivity(), this);
		_dft.execute();
		
		_ms = (MultiSpinner) _rootView.findViewById(R.id.multi_spinner);
		_ms.setItems(new ArrayList<BusRoute>(), "Loading Stops...", this);

		Log.d("mapviewinit", "Creating map manager");

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
		Log.d("mapviewinit-buslines","Adding bus lines to the map");
		_mapManager.initBusRoutes(busRoutes);
		_mapManager.execute();
		Log.d("mapviewinit-buslines", "Done adding bus lines");
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

			MultiSpinner ms = (MultiSpinner) _rootView.findViewById(R.id.multi_spinner);
			ms.setItems(null, "No Routes Found", this);
		}else{
			createLines(busRoutes);
		}
	}

	@Override
	public void addLines(ArrayList<BusRoute> busRoutes, ArrayList<BusStop> mapMarkers){
		_busRoutes = busRoutes;
		_mapMarkers = mapMarkers;
		for(int i = 0; i < busRoutes.size(); i++){
			_busRoutes.get(i).setPolyline(_map.addPolyline(busRoutes.get(i).getPolylineOptions()));
		}
		for(int i = 0; i < mapMarkers.size(); i++){
			Log.d("mapviewinit", "Added: " + mapMarkers.get(i).getDescription());
			_mapMarkers.get(i).setMarker(_map.addMarker(mapMarkers.get(i).getMarkerOptions()));
		}
		_ms.setItems(busRoutes, "Select Bus Routes", this);
	}

	@Override
	public void onItemsSelected(boolean[] selected) {
		LinkedList<BusRoute> enabledLines = new LinkedList<BusRoute>();
		for(int i = 0; i < _mapMarkers.size(); i++){
			_mapMarkers.get(i).getMarker().setVisible(false);
		}
		for(int i = 0; i < selected.length; i++){
			_busRoutes.get(i).getPolyline().setVisible(selected[i]);
			if(selected[i]){
				enabledLines.add(_busRoutes.get(i));
			}
		}
		for(int i = 0; i < _mapMarkers.size(); i++){
			for(int j = 0; j < enabledLines.size(); j++){
				if(_mapMarkers.get(i).containsBusRoute(enabledLines.get(j))){
					_mapMarkers.get(i).getMarker().setVisible(true);
				}
			}
		}
	}
}