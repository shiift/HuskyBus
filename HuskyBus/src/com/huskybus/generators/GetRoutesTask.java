package com.huskybus.generators;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.AsyncTask;

import com.huskybus.tools.URLFileSaver;
import com.huskybus.R;


public class GetRoutesTask extends AsyncTask<Void, Void, ArrayList<BusRoute>> {

	public Activity activity;
	public AsyncResponse delegate;
	
	public GetRoutesTask(Activity activity, AsyncResponse delegate){
		this.activity = activity;
		this.delegate = delegate;
	}

	@Override
	protected ArrayList<BusRoute> doInBackground(Void ... args) {
		File root = android.os.Environment.getExternalStorageDirectory();
		File file = new File(root.getAbsolutePath() + activity.getString(R.string.storage_path) + activity.getString(R.string.routes_file));
		
		URLFileSaver.activity = activity;
		
		Calendar lastModDate = Calendar.getInstance();
		lastModDate.setTime(new Date(file.lastModified()));
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.MONTH, -1);
		
		if(!(file.exists() && !file.isDirectory())
				|| lastModDate.compareTo(currentDate) < 0) {
			String urlString = activity.getString(R.string.routes_url);
			try {
				URLFileSaver.downloadFromURL(urlString, "bus_routes.txt");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		InputStreamReader isr = new InputStreamReader(fis);

		ArrayList<BusRoute> busRoutes = RouteGenerator.generateRoutes(isr);
		return busRoutes;
	}

	@Override
	protected void onPostExecute(ArrayList<BusRoute> busRoutes) {
		super.onPostExecute(busRoutes);
		delegate.processFinish(busRoutes);
		return;
	}

	public interface AsyncResponse{
		void processFinish(ArrayList<BusRoute> busRoutes);
	}

}
