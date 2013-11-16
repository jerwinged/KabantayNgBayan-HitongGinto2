package com.demo.sampleproject.datahandler.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public class HttpHandler {

    public static InputStream queryHtml(String url) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();
        InputStream responseStream = null;

        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            responseStream = response.getEntity().getContent();
        } else{
            //Closes the connection.
            throw new IOException(statusLine.getReasonPhrase());
        }

        return responseStream;
    }

}
