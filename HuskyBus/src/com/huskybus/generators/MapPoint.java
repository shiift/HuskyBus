package com.huskybus.generators;

public class MapPoint{
	public double heading, latitude, longitude;

	public MapPoint(){
	}

	public MapPoint(double heading, double latitude, double longitude){
		this.heading = heading;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString(){
		return "(" + this.longitude + ", " + this.latitude + ")";
	}
}
