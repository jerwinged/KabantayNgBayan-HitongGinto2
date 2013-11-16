package com.demo.sampleproject.saromgmt;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.demo.sampleproject.R;
import com.demo.sampleproject.datahandler.SaroHttpHandler;
import com.demo.sampleproject.datahandler.collection.SaroList;


public class SaroRetrieval extends Activity {

	private ListView list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_saro_retrieval);
        list = (ListView)findViewById(R.id.saro_retrieval_list);
    }

	@Override
	protected void onResume() {
		super.onResume();
		SaroHttpHandler request = new SaroHttpHandler();
		request.execute("http://api.kabantayngbayan.ph/saro?app_id=528710895e13db245f366503&year=2013");
		try {
			SaroList resultList = request.get();
			if (resultList != null) {
				SaroListAdapter adapter = new SaroListAdapter(this, resultList);
				list.setAdapter(adapter);
			}
			else {
				Log.d("SARO RETRIEVAL", "Result returned null");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}
