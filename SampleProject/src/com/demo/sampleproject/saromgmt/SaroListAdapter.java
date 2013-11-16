package com.demo.sampleproject.saromgmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.sampleproject.R;
import com.demo.sampleproject.datahandler.collection.SaroList;
import com.demo.sampleproject.datahandler.object.SaroEntry;

public class SaroListAdapter extends BaseAdapter {

	private SaroList entries;
	private LayoutInflater inflater = null;
	
	public SaroListAdapter(Context context, SaroList entries) {
		this.entries = entries;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return entries.size();
	}

	@Override
	public Object getItem(int position) {
		return entries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.list_item_saro, null);
		}
		
		TextView description = (TextView) vi.findViewById(R.id.description);
		TextView longDescription = (TextView) vi.findViewById(R.id.project_description);
		TextView allocation = (TextView) vi.findViewById(R.id.project_funds);
		
		SaroEntry entry = entries.get(position);
		
		description.setText(entry.getDescription());
		longDescription.setText(entry.getProgramDescription());
		allocation.setText(Long.toString(entry.getAmount()));
		
		return vi;
	}

}
