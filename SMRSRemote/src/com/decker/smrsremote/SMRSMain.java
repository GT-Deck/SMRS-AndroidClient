package com.decker.smrsremote;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SMRSMain extends FragmentActivity implements
		ServerInputDialog.NoticeDialogListener {

	public String newServerName;
	public String newServerIP;
	CustomArrayAdapter serverAdapter;
	TextView UniqueId;
	ListView usersServerList;
	ArrayList<StarMadeServer> servers;
	DBCommander DBManager;
	int ListSelection;

	AlertDialog.Builder inputDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smrsmain);
		DBManager = new DBCommander(this.getApplicationContext());
		servers = new ArrayList<StarMadeServer>();
		servers = DBManager.getList();

		UniqueId = (TextView) findViewById(R.id.IDLabel);
		UniqueId.setText("AndroidID: "
				+ Secure.getString(this.getContentResolver(), Secure.ANDROID_ID));

		usersServerList = (ListView) findViewById(R.id.listView);
		serverAdapter = new CustomArrayAdapter(this.getApplicationContext(),
				servers);
		usersServerList.setAdapter(serverAdapter);

		usersServerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				arg1.setSelected(true);
				ListSelection = arg2;

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.smrsmain, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete_button:
			if (ListSelection < 0) {
				ListSelection = servers.size() - 1;
			}
			if (servers.size() != 0) {
				DBManager.removeItemFromList(servers.get(ListSelection));
				servers = DBManager.getList();
				serverAdapter.clear();
				serverAdapter.addAll(servers);
				serverAdapter.notifyDataSetChanged();
				ListSelection = -1;
			}

			return true;
		case R.id.add_button:

			ServerInputDialog dialog = new ServerInputDialog();
			dialog.show(getFragmentManager(), "Input Server Info");

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onDialogPositiveClick(StarMadeServer server) {

		DBManager.addItemToList(server);
		servers = DBManager.getList();
		serverAdapter.clear();
		serverAdapter.addAll(servers);
		serverAdapter.notifyDataSetChanged();

	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// Do nothing user backed out of server input dialog

	}

}
