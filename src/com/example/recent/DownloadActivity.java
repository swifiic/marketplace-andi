package com.example.recent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DownloadActivity extends Activity {
	DbAdd download;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		
		
	}
	
	
	   @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	super.onCreateOptionsMenu(menu);
	    	
	    	MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.home, menu);
	        
	        
	    	return true;
	    	}
	    public boolean onOptionsItemSelected(MenuItem item)
		{

	    	switch (item.getItemId()) {
	    	

	case R.id.request:
		         startActivity(new Intent(getApplicationContext(), RequestActivity.class));
		       
		        break;
			
			case R.id.login:
		         startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		    finish();
		    break;
			
		    
			case R.id.seeallrequests:
		         startActivity(new Intent(getApplicationContext(), RequestListActivity.class));
		    finish();
		    break;
			case R.id.aboutapp:
		         startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
		    finish();
		    
			}
			return true;
			
		
		}
		

}
