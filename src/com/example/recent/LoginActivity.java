package com.example.recent;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private String username;
	private String password;
	
	
	private EditText usernameview;
	private EditText passwordview;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Button b=(Button)findViewById(R.id.elogin);
		usernameview = (EditText)findViewById(R.id.eusername);
		passwordview = (EditText)findViewById(R.id.epassword);
		
		
		
	
	b.setOnClickListener(new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		try{
			String username=usernameview.getText().toString();
			String password=passwordview.getText().toString();
			
			
			if(username.equals("karthik") && password.equals("karthik"))
			{
				Intent i = new Intent();
				i.setClass(LoginActivity.this,AddActivity.class);
				startActivity(i);
				Toast.makeText(LoginActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
				finish();				
			}
			else
			{
				AlertDialog.Builder alertBuilder=new AlertDialog.Builder(LoginActivity.this);
				alertBuilder.setTitle("Invalid Data");
				alertBuilder.setMessage("Please, Enter valid data");
				alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					}
				});
				alertBuilder.create().show();
			}
			
			
			}catch(Exception e){
			Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
		}}
	});
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
		   break;
			case R.id.aboutapp:
		         startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
		    
			
			}
			return true;
			
		
		}
	
}
