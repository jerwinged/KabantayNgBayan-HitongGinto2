package com.demo.sampleproject.datahandler.util;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public abstract class KabantayJsonAsyncHttpHandler<N> extends AsyncTask<String, Integer, N> {

    private static final String TAG = "KABANTAYJSONASYNCHTTPHANDLER";

    public KabantayJsonAsyncHttpHandler() {
    }

    @Override
    protected N doInBackground(String... params) {
        N parseResult = null;
        try {
        	InputStream result = HttpHandler.queryHtml(params[0]);
            
            Log.d("SARO HTTP RESPONSE RETRIEVAL", "Processing response");
            
        	if (result != null) {
                try {
                    JsonReader jsonReader = new JsonReader(new InputStreamReader(result, "UTF-8"));
                    jsonReader.beginObject();

                    //check the success value
                    if (jsonReader.nextName().equals("status")) {
                        if (jsonReader.nextString().equals("success")) {
                            if (jsonReader.hasNext() && jsonReader.nextName().equals("data")) {
                                jsonReader.beginArray();
                                parseResult = processJsonDataArray(jsonReader);
                                jsonReader.endArray();
                            }
                            else {
                                Log.e(TAG, "Change in JSON structure? Unsupported name/value pair.");
                            }
                        }
                    }
                    jsonReader.endObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.d(TAG, "No data received");
            }
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Log.d("SARO HTTP RESPONSE RETRIEVAL", "Returning...");
        
        return parseResult;

    }

    protected abstract N processJsonDataArray(JsonReader reader);
}
