package com.example.administrator.appfinal;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by bafs on 25/01/2017.
 */

public class HttpHandler {

    private static final String LOG_TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {

    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.setConnectTimeout(15000);
            connect.setReadTimeout(10000);

            InputStream in = new BufferedInputStream(connect.getInputStream());
            response = convertStreamToString(in);

        }catch (MalformedURLException e) {
            Log.e(LOG_TAG, "MalformedException " + e.getMessage());
        }catch (ProtocolException e) {
            Log.e(LOG_TAG, "Protocol Exception " + e.getMessage());
        }catch (IOException e) {
            Log.e(LOG_TAG, "IOException " + e.getMessage());
        }catch (Exception e) {
            Log.e(LOG_TAG, "Exception " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sBuilder = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null){
                sBuilder.append(line).append('\n');
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sBuilder.toString();
    }
}
