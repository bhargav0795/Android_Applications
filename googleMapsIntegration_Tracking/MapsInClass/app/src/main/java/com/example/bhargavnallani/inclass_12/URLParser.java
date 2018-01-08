package com.example.bhargavnallani.inclass_12;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bhargav Nallani on 11/27/2017.
 */


//public class URLParser extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
//
//
//    MapsActivity mapsActivity;
//    private GoogleMap mMap = mapsActivity.getMap();
//
//    @Override
//    protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
//
//        JSONObject jsonObject;
//        List<List<HashMap<String, String>>> routes = null;
//
//        try {
//            jsonObject = new JSONObject(jsonData[0]);
//            Log.d("ParserTask",jsonData[0].toString());
//
//
//            parserD parser = new parserD();
//
//            Log.d("ParserTask", parser.toString());
//
//            // Starts parsing data
//            routes = parser.parse(jsonObject);
//
//            Log.d("ParserTask","Executing routes");
//            Log.d("ParserTask",routes.toString());
//
//        } catch (Exception e) {
//            Log.d("ParserTask",e.toString());
//            e.printStackTrace();
//        }
//        return routes;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected void onPostExecute(List<List<HashMap<String, String>>> resultList) {
//        super.onPostExecute(resultList);
//
//        ArrayList<LatLng> points;
//        PolylineOptions lineOptions = null;
//
//        // Traversing through all the routes
//        for (int i = 0; i < resultList.size(); i++) {
//            points = new ArrayList<>();
//            lineOptions = new PolylineOptions();
//
//            // Fetching i-th route
//            List<HashMap<String, String>> path = resultList.get(i);
//
//            // Fetching all the points in i-th route
//            for (int j = 0; j < path.size(); j++) {
//                HashMap<String, String> point = path.get(j);
//
//                double lat = Double.parseDouble(point.get("lat"));
//                double lng = Double.parseDouble(point.get("lng"));
//                LatLng position = new LatLng(lat, lng);
//
//                points.add(position);
//            }
//
//            // Adding all the points in the route to LineOptions
//            lineOptions.addAll(points);
//            lineOptions.width(10);
//            lineOptions.color(Color.RED);
//
//            Log.d("onPostExecute","onPostExecute lineoptions decoded");
//
//        }
//
//        // Drawing polyline in the Google Map for the i-th route
//        if(lineOptions != null) {
//            mMap.addPolyline(lineOptions);
//        }
//        else {
//            Log.d("onPostExecute","without Polylines drawn");
//        }
//    }
//}
//
