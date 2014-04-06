package com.huskybus;

import com.huskybus.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

	public double home_lng = 0;
	public double home_lat = 0;
	public double home_zoom = 12;

	public SettingsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// check existing settings
		SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		getSettings(sharedPref);

		View rootView = inflater.inflate(R.layout.fragment_settings, container,
				false);

		return rootView;
	}

	public void getSettings(SharedPreferences sharedPref){
		home_lat = getDouble(sharedPref, "home_lat", 41.847711);
		home_lng = getDouble(sharedPref, "home_lng", -72.598783);
		home_zoom = sharedPref.getFloat("home_zoom", 20);
	}

	Editor putDouble(final Editor edit, final String key, final double value) {
		return edit.putLong(key, Double.doubleToRawLongBits(value));
	}

	double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
		return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
	}
}
