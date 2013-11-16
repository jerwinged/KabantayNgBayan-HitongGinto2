package com.demo.sampleproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ProjectInfo extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_info);
	}
	
	public void onClick_Yes(View view) {
		System.out.println("Yes");
    }
	
	public void onClick_No(View view) {
		System.out.println("No");
    }

}
