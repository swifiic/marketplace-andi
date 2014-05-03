package com.example.recent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutAppActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutapp);
		TextView abouttvappname,abouttvbrief,abouttvversion,abouttvphone;
		//ImageView ivDp = (ImageView)findViewById(R.id.aboutivProfile);
		abouttvappname = (TextView)findViewById(R.id.abouttvappname);
		abouttvversion = (TextView)findViewById(R.id.abouttvversion);
		abouttvbrief = (TextView)findViewById(R.id.abouttvbrief);
		
		abouttvappname.setText("Market Place");
		abouttvversion.setText("1.0(beta)");
		abouttvbrief.setText("This application called market place.\n People can download thier required applcation in " +
				"home page, if not available can request the admin about the app they want to download.\n" +
				"The login button is only for the admin and for the normal end user.\n" +
				"The app requests can also be seen in @ SEE ALL REQUESTS @ page.\n" +
				"Some of the screen shots of the application are also given above ");
		
		
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
	      finish();
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
