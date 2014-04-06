package com.huskybus.generators;

import java.io.IOException;
import java.io.Reader;
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
		
		newRoute.setDescription(jo.get("Description").asString());
		newRoute.setTextingKey(jo.get("TextingKey").asString());
		newRoute.setMapLineColor(jo.get("MapLineColor").asString());
		
		newRoute.setHideRouteLine(jo.get("HideRouteLine").asBoolean());
		newRoute.setCheckedOnMap(jo.get("IsCheckedOnMap").asBoolean());
		newRoute.setVisibleOnMap(jo.get("IsVisibleOnMap").asBoolean());
		newRoute.setShowPolygon(jo.get("ShowPolygon").asBoolean());
		
		newRoute.setMapLatitude(jo.get("MapLatitude").asDouble());
		newRoute.setMapLongitude(jo.get("MapLongitude").asDouble());
		
		newRoute.setMapZoom(jo.get("MapZoom").asInt());
		newRoute.setOrder(jo.get("Order").asInt());
		newRoute.setRouteID(jo.get("RouteID").asInt());
		newRoute.setStops(getStops(jo.get("Stops").asArray()));
		
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
		
		newStop.setDescription(jo.get("Description").asString());
		newStop.setTextingKey(jo.get("TextingKey").asString());
		
		newStop.setLatitude(jo.get("Latitude").asDouble());
		newStop.setLongitude(jo.get("Longitude").asDouble());
		newStop.setHeading(jo.get("Heading").asDouble());
		
		newStop.setAddressID(jo.get("AddressID").asInt());
		newStop.setMaxZoomLevel(jo.get("MaxZoomLevel").asInt());
		newStop.setOrder(jo.get("Order").asInt());
		newStop.setRouteID(jo.get("RouteID").asInt());
		newStop.setRouteStopID(jo.get("RouteStopID").asInt());
		newStop.setSecondsAtStop(jo.get("SecondsAtStop").asInt());
		newStop.setSecondsToNextStop(jo.get("SecondsToNextStop").asInt());
		
		newStop.setShowDefaultedOnMap(jo.get("ShowDefaultedOnMap").asBoolean());
		newStop.setShowEstimatesOnMap(jo.get("ShowEstimatesOnMap").asBoolean());
		newStop.setMapPoints(getPoints(jo.get("MapPoints").asArray()));
		
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
