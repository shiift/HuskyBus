package com.huskybus.generators;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class RouteGenerator {
	
	public static ArrayList<BusRoute> generateRoutes(Reader reader){
		ArrayList<BusRoute> busRoutes = new ArrayList<BusRoute>();
		JsonArray ja = null;
		try {
			ja = JsonArray.readFrom(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<ja.size(); i++){
			busRoutes.add(generateRoute(ja.get(i).asObject()));
		}
		return busRoutes;
	}
	
	// Generates the Bus Route
	public static BusRoute generateRoute(JsonObject jo){
		BusRoute newRoute = new BusRoute();
		
		newRoute.description 	= jo.get("Description").asString();
		newRoute.textingKey		= jo.get("TextingKey").asString();
		newRoute.mapLineColor	= jo.get("MapLineColor").asString();
		
		newRoute.hideRouteLine	= jo.get("HideRouteLine").asBoolean();
		newRoute.isCheckedOnMap	= jo.get("IsCheckedOnMap").asBoolean();
		newRoute.isVisibleOnMap	= jo.get("IsVisibleOnMap").asBoolean();
		newRoute.showPolygon	= jo.get("ShowPolygon").asBoolean();
		
		newRoute.mapLatitude	= jo.get("MapLatitude").asDouble();
		newRoute.mapLongitude	= jo.get("MapLongitude").asDouble();
		
		newRoute.mapZoom		= jo.get("MapZoom").asInt();
		newRoute.order			= jo.get("Order").asInt();
		newRoute.routeID		= jo.get("RouteID").asInt();
		newRoute.stops			= getStops(jo.get("Stops").asArray());
		
		return newRoute;
	}
	
	public static BusStop[] getStops(JsonArray jsonStopArray){
		BusStop[] stopArray = new BusStop[jsonStopArray.size()];
		
		for(int i=0; i<jsonStopArray.size(); i++){
			stopArray[i] = generateStop(jsonStopArray.get(i).asObject());
		}
		return stopArray;
	}
	
	public static BusStop generateStop(JsonObject jo){
		BusStop newStop = new BusStop();
		
		newStop.description			= jo.get("Description").asString();
		newStop.textingKey			= jo.get("TextingKey").asString();
		
		newStop.latitude			= jo.get("Latitude").asDouble();
		newStop.longitude			= jo.get("Longitude").asDouble();
		newStop.heading				= jo.get("Heading").asDouble();
		
		newStop.addressID			= jo.get("AddressID").asInt();
		newStop.maxZoomLevel		= jo.get("MaxZoomLevel").asInt();
		newStop.order				= jo.get("Order").asInt();
		newStop.routeID				= jo.get("RouteID").asInt();
		newStop.routeStopID			= jo.get("RouteStopID").asInt();
		newStop.secondsAtStop		= jo.get("SecondsAtStop").asInt();
		newStop.secondsToNextStop	= jo.get("SecondsToNextStop").asInt();
		
		newStop.showDefaultedOnMap	= jo.get("ShowDefaultedOnMap").asBoolean();
		newStop.showEstimatesOnMap	= jo.get("ShowEstimatesOnMap").asBoolean();
		newStop.mapPoints			= getPoints(jo.get("MapPoints").asArray());
		
		return newStop;
	}
	
	public static MapPoint[] getPoints(JsonArray jsonPointArray){
		MapPoint[] pointArray = new MapPoint[jsonPointArray.size()];
		
		for(int i=0; i<jsonPointArray.size(); i++){
			pointArray[i] = generatePoint(jsonPointArray.get(i).asObject());
		}
		return pointArray;
	}
	
	public static MapPoint generatePoint(JsonObject jo){
		MapPoint newPoint = new MapPoint(jo.get("Heading").asDouble(),
				jo.get("Latitude").asDouble(), jo.get("Longitude").asDouble());
		
		return newPoint;
	}
}
