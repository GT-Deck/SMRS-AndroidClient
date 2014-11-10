package com.decker.smrsremote;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class CustomArrayAdapter extends ArrayAdapter<StarMadeServer>

{
	private Context context;
	private ArrayList<StarMadeServer> values;
	private UdpTransaction client;
	private ExecutorService pool;

	public CustomArrayAdapter(Context context, ArrayList<StarMadeServer> values) {
		super(context, R.layout.list_view_item, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		pool = Executors.newSingleThreadExecutor();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_view_item, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.item_title);
		Button startButton = (Button) rowView.findViewById(R.id.StartBtn);
		textView.setText(values.get(position).getName());
		startButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				client = new UdpTransaction(context, values.get(position)
						.getIP());

				try {
					pool.execute(client);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		return rowView;
	}

}
