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
	public Conditions cndtns = null;

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

				disLoc = new Location2();
				JSONObject display_location = current_observation
						.getJSONObject("display_location");
				disLoc.setFull(display_location.getString("full"));
				disLoc.setCity(display_location.getString("city"));
				disLoc.setCountry(display_location.getString("country"));
				disLoc.setCountry_iso3166(display_location
						.getString("country_iso3166"));
				disLoc.setElevation(display_location.getString("elevation"));
				disLoc.setLatitude(display_location.getString("latitude"));
				disLoc.setLongitude(display_location.getString("longitude"));
				Log.i("disLoc",
						disLoc.getLatitude() + " " + disLoc.getLongitude()
								+ " " + disLoc.getFull());

				obsLoc = new Location2();
				JSONObject observation_location = current_observation
						.getJSONObject("observation_location");
				obsLoc.setFull(observation_location.getString("full"));
				obsLoc.setCity(observation_location.getString("city"));
				obsLoc.setCountry(observation_location.getString("country"));
				obsLoc.setCountry_iso3166(observation_location
						.getString("country_iso3166"));
				obsLoc.setElevation(observation_location.getString("elevation"));
				obsLoc.setLatitude(observation_location.getString("latitude"));
				obsLoc.setLongitude(observation_location.getString("longitude"));
				Log.i("obsLoc",
						obsLoc.getLatitude() + " " + obsLoc.getLongitude()
								+ " " + obsLoc.getFull());

				cndtns = new Conditions();
				cndtns.dewpointC = current_observation.getString("dewpoint_c");
				cndtns.dewpointString = current_observation
						.getString("dewpoint_string");
				cndtns.feelslikeString = current_observation
						.getString("feelslike_string");
				cndtns.feelslikeC = current_observation
						.getString("feelslike_c");
				cndtns.heatIndexC = current_observation
						.getString("heat_index_c");
				cndtns.heatIndexString = current_observation
						.getString("heat_index_string");
				cndtns.icon = current_observation.getString("icon");
				cndtns.iconURL = current_observation.getString("icon_url");
				cndtns.localTimeRfc822 = current_observation
						.getString("local_time_rfc822");
				cndtns.localTzLong = current_observation
						.getString("local_tz_long");
				cndtns.localTzOffset = current_observation
						.getString("local_tz_offset");
				cndtns.localTzShort = current_observation
						.getString("local_tz_short");
				cndtns.observationEpoch = current_observation
						.getString("observation_epoch");
				cndtns.observationTime = current_observation
						.getString("observation_time");
				cndtns.observationTimeRfc822 = current_observation
						.getString("observation_time_rfc822");
				cndtns.pressureMb = current_observation
						.getString("pressure_mb");
				cndtns.pressureTrend = current_observation
						.getString("pressure_trend");
				cndtns.relativeHumidity = current_observation
						.getString("relative_humidity");
				cndtns.tempC = current_observation.getString("temp_c");
				cndtns.temperatureString = current_observation
						.getString("temperature_string");
				cndtns.UV = current_observation.getString("UV");
				cndtns.visibilityKm = current_observation
						.getString("visibility_km");
				cndtns.windChillC = current_observation
						.getString("windchill_c");
				cndtns.windChillString = current_observation
						.getString("windchill_string");
				cndtns.windDegrees = current_observation
						.getString("wind_degrees");
				cndtns.windDir = current_observation.getString("wind_dir");
				cndtns.windKph = current_observation.getString("wind_kph");
				cndtns.windMph = current_observation.getString("wind_mph");
				cndtns.windString = current_observation
						.getString("wind_string");
				cndtns.weather = current_observation.getString("weather");
				Log.i("COS. Conditions:", cndtns.toString() + " "
						+ cndtns.feelslikeString + " " + cndtns.weather);

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
