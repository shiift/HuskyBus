package com.huskybus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.huskybus.MultiSpinner.MultiSpinnerListener;
import com.huskybus.generators.BusRoute;
import com.huskybus.generators.BusStop;
import com.huskybus.generators.GetRoutesTask;
import com.huskybus.generators.GetRoutesTask.AsyncResponse;
import com.huskybus.managers.MapManager;
import com.huskybus.R;

public class BusMapFragment extends Fragment implements AsyncResponse, MultiSpinnerListener{

	MapManager _mapManager;
	View _rootView;
	GetRoutesTask _dft;

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
		
		FragmentManager manager = getActivity().getSupportFragmentManager();
		SupportMapFragment mapFragment =(SupportMapFragment) manager.findFragmentById(R.id.busmap);
		GoogleMap map = mapFragment.getMap();
		adjustMap(map);
		
		_mapManager = new MapManager(map);

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

	public void createLines(GoogleMap map, ArrayList<BusRoute> busRoutes){
		_mapManager.createLines(busRoutes);
	}

	@Override
	public void processFinish(ArrayList<BusRoute> busRoutes) {
		if(busRoutes == null){
			new AlertDialog.Builder(this.getActivity())
		    .setTitle("Could not connect to the network")
		    .setMessage("Please enable network and click refresh")
		    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        	// do nothing
		        }
		     })
		    .show();
			
//			MultiSpinner ms = (MultiSpinner) _rootView.findViewById(R.id.multi_spinner);
//			ms.setItems(null, "No Routes Found", this);
		}else{
			FragmentManager manager = getActivity().getSupportFragmentManager();
			SupportMapFragment mapFragment =(SupportMapFragment) manager.findFragmentById(R.id.busmap);
			GoogleMap map = mapFragment.getMap();
			createLines(map, busRoutes);
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

}