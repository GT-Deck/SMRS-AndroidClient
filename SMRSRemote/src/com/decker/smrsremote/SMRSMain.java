package com.decker.smrsremote;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

//TODO extend fragmentActivity 
public class SMRSMain extends FragmentActivity implements
		ServerInputDialog.NoticeDialogListener {

	public String newServerName;
	public String newServerIP;
	ListView usersServerList;
	ArrayList<StarMadeServer> servers;
	DBCommander DBManager;
	int ListSelection;

	AlertDialog.Builder inputDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smrsmain);
		servers = new ArrayList<StarMadeServer>();
		usersServerList = (ListView) findViewById(R.id.listView);
		DBManager = new DBCommander(this.getApplicationContext());

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
			DBManager.removeItemFromList(servers.get(ListSelection));

			// TODO removal of selected server is done here
			return true;
		case R.id.add_button:
			// TODO input dialogs to capture new server details
			DBManager.addItemToList(new StarMadeServer(newServerName,
					newServerIP));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onDialogPositiveClick(StarMadeServer server) {

		DBManager.addItemToList(server);

	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// Do nothing

	}

}
