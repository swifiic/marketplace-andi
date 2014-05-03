package com.example.recent;



import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
//import android.app.Activity;
//import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
///import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
//import android.widget.ListView;
//import android.widget.SearchView;
//import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
//import android.widget.Toast;

import android.app.ListActivity;


//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.SimpleAdapter;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class RequestListActivity extends ListActivity {
	//static final int rposition;
	
	DbRequest helpers;
	ListView list;
	public static int rposition;
	Context context;
	//ListView = list;
	//Context  context;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.requestlist);
		helpers = new DbRequest(this);	
		Cursor c = helpers.getAll();
		MyAdapter adapter = new MyAdapter(c);
		context = this;
		setListAdapter(adapter);
		//setListAdapter(adapter);
		Log.d("KARTHIK","Changed..");
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
       }
    private class MyAdapter extends CursorAdapter{

		@SuppressWarnings("deprecation")
		public MyAdapter(Cursor c) {
			super(RequestListActivity.this,c);
		}
		@Override
		public void bindView(View row, Context context, Cursor c) {
			ImageView ivDp = (ImageView) row.findViewById(R.id.rlivProfile);
			TextView tvappname,tvbrief,tvusername,tvphone;
			tvappname = (TextView) row.findViewById(R.id.rltvappname);
			tvbrief = (TextView) row.findViewById(R.id.rltvbrief);
			tvusername = (TextView) row.findViewById(R.id.rltvusername);
			
			tvphone = (TextView) row.findViewById(R.id.rltvphone);
			tvappname.setText(helpers.getAppname(c));
			String trim = helpers.getBrief(c);
			String org = "";
			
			
			//tvbrief.set
			if(trim.length()>30)
			{
				org = trim.substring(0, 30);
				tvbrief.setText(org+"...");
				
			}
			
			else
			{
				tvbrief.setText(helpers.getBrief(c)+"...");
				
			}
			
			
			//Log.d("SR","Got "+helpers.getBrief(c)+"...");
			tvusername.setText(helpers.getUsername(c));
			//tvphone.setText(helpers.getPhone(c));
			tvphone.setText(Long.toString(helpers.getPhone(c)));
			byte barr[] = helpers.getImage(c);
			Bitmap bmp = MyUtilities1.giveBitmap(barr);
			ivDp.setImageBitmap(bmp);
		}

		@Override
		public View newView(Context context, Cursor c, ViewGroup parent) {
			View row = getLayoutInflater().inflate(R.layout.requestlist_row,parent,false);
			return row;
		}
		
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
    @SuppressWarnings("unused")
	private void CreateMenu(Menu menu)
	{
		MenuItem mnu1 = menu.add(0, 0, 0, "request");
		{
		mnu1.setIcon(R.drawable.ic_launcher);
		mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		
		MenuItem mnu2 = menu.add(0, 0, 0, "login");
		{
		mnu2.setIcon(R.drawable.ic_launcher);
		mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}	
	}
	//-----------------------------------onclick-------------------------------------------------------
    @Override
   	protected void onListItemClick(ListView l, View v, int position, long id) {
   		// TODO Auto-generated method stub
   		super.onListItemClick(l, v, position, id);
   		Toast.makeText(RequestListActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
 rposition = position;
Log.d("Inside method requestlistactivity", " "+rposition);
Toast.makeText(RequestListActivity.this, "which is" + rposition,Toast.LENGTH_LONG).show();

            // when user clicks on ListView Item , onItemClick is called
               // with position and View of the item which is clicked
               //// we can use the position parameter to get index of clicked item
              TextView appname=(TextView)v.findViewById(R.id.rltvappname);
              TextView username=(TextView)v.findViewById(R.id.rltvusername);
              TextView phone=(TextView)v.findViewById(R.id.rltvphone);
              TextView brief=(TextView)v.findViewById(R.id.rltvbrief);
              String Appname=appname.getText().toString();
              String Brief=brief.getText().toString();
            String Username = username.getText().toString();
            String Phone = phone.getText().toString();
              
               
           Intent i = new Intent(this, RequestDetailsActivity.class);
           startActivity(i);
               // Show The Dialog with Selected SMS
            /*   AlertDialog dialog = new AlertDialog.Builder(context).create();
               dialog.setTitle("SMS From :"+Appname);
              dialog.setIcon(android.R.drawable.ic_dialog_info);
               dialog.setMessage("hahaha" + Brief + Username + Phone);
               dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                       new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which)
                   {                	   
                           dialog.dismiss();
                           return;
               }
                
               }
      );
               dialog.show();
          
       */
      
       }
       
    //----------------------------------onclick ends --------------------------------------------------

    
	}