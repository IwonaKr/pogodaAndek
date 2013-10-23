package com.pogodaandek;

public class ForecastDay {
	public Date data;
	public String period;
	public String icon;
	public String iconUrl;
	public String title;
	public String fcttext;
	public String fcttextMetric;
	public String pop; // prawdopodobie�stwo opad�w atmosferycznych
	
	//dla wynik�w forecast z SIMPLE FORECAST
	public String highTempC;
	public String lowTempC;
	public String conditions;
	public String skyIcon;
	public String qpfAllDay; //ilo�ciowa prognoza opad�w w przeci�gu 3 nast�pnych godzin
	public String snowAllDay; //ilo�� opad�w �niegu w przeci�gu ddnia
	
	//Wilgotno�� powietrza
	public int avehumidity;
	public int maxhumidity;
	public int minhumidity;
	
	//Wiatr
	public int maxwind_mph; //MILE na h
	public int maxwind_kph;
	public String maxwind_degrees; //stopnie kierunku wiatru
	public String maxwind_dir; //kierunek wiatru
	
	public int avewind_mph;
	public int avewind_kph;
	public String avewind_dir;
	public String avewind_degrees;
	
	
}
