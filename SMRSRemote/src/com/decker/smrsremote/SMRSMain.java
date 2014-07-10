package com.decker.smrsremote;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SMRSMain extends ListActivity{
	
	ListView usersServerList; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smrsmain);
        usersServerList = getListView();
        
        usersServerList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO  Selection of which servers to remove will be done here
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.smrsmain, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId())
		{
		case R.id.delete_button:
			//TODO removal of selected server is done here
				return true;
		case R.id.add_button:	
			//TODO addition of new server is done here
				return true;
		default:
			return super.onOptionsItemSelected(item);
		}	
	}
    
}
