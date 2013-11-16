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

    private AsyncHttpHandler httpHandler;

    public KabantayJsonAsyncHttpHandler() {
        this.httpHandler = new AsyncHttpHandler();
    }

    @Override
    protected N doInBackground(String... params) {
        N parseResult = null;
        boolean isCancelled = false;
        httpHandler.execute(params);
        while (httpHandler.getStatus() == Status.PENDING || httpHandler.getStatus() == Status.RUNNING) {
            if (this.isCancelled()) {
                isCancelled = true;
                break;
            }
        }

        if (!isCancelled) {
            InputStream result = null;
            try {
                result = httpHandler.get();
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return parseResult;

    }

    protected abstract N processJsonDataArray(JsonReader reader);
}
