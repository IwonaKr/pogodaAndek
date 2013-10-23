package com.pogodaandek;

import android.text.format.Time;

public class HourlyForecast {
	public Time czas;
	public String monAbbrev; //skr�t nazwy miesi�ca po ang
	public String monthAbbrev; //skr�t nazwy miesi�ca po polsku
	public String pretty;
	public String weekdayNameAbbrev; //skr�t dnia tygodnia
	public String weekdayNameNight; //na noc i wiecz�r
	//public String tz;// timezone
	//public String ampm;
	//public String epoch; //czas unixowy
	public String tempC;
	public String dewpointC; //punkt rosy
	public String condition; //s�owny kr�tki opis
	public String icon;
	public String iconUrl;
	public String fctcode;
	public String sky;
	public String windKph; 
	public String windDir;
	public String windDegrees;
	//public String uvi; //ultraviolet index (1-16, where 16 is extreme) 
	public String humidity;
	public String windchill; //if <-100 jest �le
	public String heatindex; //if <-100 jest �le
	public String feelslike;
	public String qpf;
	public String snow;
	public String pop;
	public String pressure; //MSLP - mean sea level pressure- ci�nienie?
}
