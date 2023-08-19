package com.example.networking;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends AsyncTask<String, String, String> {

    public interface JsonTaskListener {
        void onPostExecute(String json);
    }
    private Mountain[] mountains;
    private HttpURLConnection connection = null;
    private BufferedReader reader = null;
    private final JsonTaskListener listener;

    @SuppressWarnings("deprecation")
    public JsonTask(JsonTaskListener listener) {
        this.listener = listener;
    }

    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = new BufferedInputStream(connection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && !isCancelled()) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (MalformedURLException e) {
            /*e.printStackTrace();*/
            Log.d("TAG", e.toString());
        } catch (IOException e) {
            /*e.printStackTrace();*/
            Log.d("TAG", e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
               /* e.printStackTrace();*/
                Log.d("TAG", e.toString());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String json) {
        listener.onPostExecute(json);
        Gson gson = new Gson();
        mountains = gson.fromJson(json,Mountain[].class);

        for (int i = 0; i < mountains.length; i++){
            Log.d("TAG", "Hittade ett berg: "+mountains[i]);
        }

    }
}

