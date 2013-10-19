package com.pogodaandek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ForecastActivity extends Activity {

	private static String WEATHER_URL = "http://api.wunderground.com/api/c9d15b10ff3ed303/alerts/conditions/forecast/astronomy/lang:PL/q/Poland/";
	public String nazwaMiasta;
	public String json_string_response = null;
	public Location2 disLoc = null;
	public Location2 obsLoc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		Intent intent = getIntent();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			nazwaMiasta = extras.getString("Lokacja");
			TextView tv = (TextView) this.findViewById(R.id.lokacja2TB);
			tv.setText(nazwaMiasta);
			pobierzPrognoze(nazwaMiasta);
		}

	}

	public void pobierzPrognoze(String city) {
		// TODO Auto-generated method stub
		final String GET_WEATHER_URL = WEATHER_URL + city + ".json";

		// TODO Auto-generated method stub
		String request = GET_WEATHER_URL;
		HttpResponse rp = null;
		JSONObject jObject = null;
		try {
			rp = (new DefaultHttpClient()).execute(new HttpPost(request));
			Log.i("COS", "W pierwszym traju");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (rp != null
				&& rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			Log.i("Pogoda pobrana", "Pogoda pobrana");
			HttpEntity entity = rp.getEntity();
			InputStream inputStream = null;
			try {
				inputStream = entity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader r = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuilder total = new StringBuilder();
			String line;
			try {
				while ((line = r.readLine()) != null) {
					total.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inputStream.close();
				json_string_response = total.toString();
				Log.i("COS", json_string_response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jObject = new JSONObject(json_string_response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				JSONObject current_observation = jObject
						.getJSONObject("current_observation");
				Log.i("COS", "CURRENT OBS." + current_observation.toString());
				JSONObject forecast = jObject.getJSONObject("forecast");
				Log.i("COS", "FORECAST " + forecast.toString());

				// Obrabianie conditions

				disLoc=new Location2();
				JSONObject display_location = current_observation
						.getJSONObject("display_location");
				disLoc.setFull(display_location.getString("full"));
				disLoc.setCity(display_location.getString("city"));
				disLoc.setCountry(display_location.getString("country"));
				disLoc.setCountry_iso3166(display_location.getString("country_iso3166"));
				disLoc.setElevation(display_location.getString("elevation"));
				disLoc.setLatitude(display_location.getString("latitude"));
				disLoc.setLongitude(display_location.getString("longitude"));				
				Log.i("disLoc",disLoc.getLatitude()+" "+disLoc.getLongitude()+" "+disLoc.getFull());
				
				obsLoc = new Location2();
				JSONObject observation_location = current_observation
						.getJSONObject("observation_location");
				obsLoc.setFull(observation_location.getString("full"));
				obsLoc.setCity(observation_location.getString("city"));
				obsLoc.setCountry(observation_location.getString("country"));
				obsLoc.setCountry_iso3166(observation_location.getString("country_iso3166"));
				obsLoc.setElevation(observation_location.getString("elevation"));
				obsLoc.setLatitude(observation_location.getString("latitude"));
				obsLoc.setLongitude(observation_location.getString("longitude"));
				Log.i("obsLoc",obsLoc.getLatitude()+" "+obsLoc.getLongitude()+" "+obsLoc.getFull());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forecast, menu);
		return true;
	}

}
