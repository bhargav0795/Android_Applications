package com.example.akhilajana.inclass08_group22;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by akhilajana on 10/9/17.
 */

public class RequestParams {

    String method, baseUrl;
    static Map<String,String> params = new LinkedHashMap<String, String>(); // to manage parameters

    public RequestParams(String method, String baseUrl) {
        this.method = method;
        this.baseUrl = baseUrl;
    }

    //adds values to hashmap
    public void addParam(String key,String val)
    {
        params.put(key, val);
    }

    public static String  getEncodedParams()
    {

        StringBuilder sb = new StringBuilder();
        for (String key:params.keySet())
        {
            String value= params.get(key);
            try {
                String encodedValue = URLEncoder.encode(value,"UTF-8");
                if (sb.length()>0)
                {
                    sb.append("&");
               }
                sb.append(key+"="+encodedValue);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    //Method which handles all my requests
    public String getEncodedURL()
    {
        Log.d("demo", "getEncodedURL: "+baseUrl+getEncodedParams());
        return this.baseUrl+getEncodedParams();
    }

    public HttpURLConnection setUpConnection() throws IOException {
        if (method.equals("GET"))
        {
            URL url = new URL(getEncodedURL());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//
//            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//            writer.write(getEncodedParams());
//            writer.flush();
            return conn;
        }
        else
        {//POST
            URL url = new URL(this.baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //connection will set to do output
            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(getEncodedParams());
            writer.flush();
            return conn;
        }
    }



}
