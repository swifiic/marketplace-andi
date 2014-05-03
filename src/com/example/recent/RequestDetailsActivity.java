package com.example.recent;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestDetailsActivity extends Activity{
	
	DbRequest helper;
	int gopo = RequestListActivity.rposition;
	//int gopo = 1;
	
	

	//Context context;
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.requestdetails);
		helper = new DbRequest(this);
		Cursor c = helper.getAll();
		if (c==null) Log.d("ERROR","Lcursor null"); 
		Log.d("ERROR","Length of cursor :"+c.getCount());
		c.moveToPosition(gopo);
		
		TextView rtvappname,rtvbrief,rtvusername,rtvphone;
		ImageView ivDp = (ImageView)findViewById(R.id.rivProfile);
		rtvappname = (TextView)findViewById(R.id.rtvappname);
		rtvusername = (TextView)findViewById(R.id.rtvusername);
		rtvbrief = (TextView)findViewById(R.id.rtvbrief);
		rtvphone = (TextView)findViewById(R.id.rtvphone);
		
		Log.d("ERROR", Integer.toString(gopo));
		
		rtvappname.setText(helper.getAppname(c));
		//String org = helper.getBrief(c);
		rtvbrief.setText(helper.getBrief(c));
		rtvusername.setText(helper.getUsername(c));
		//tvphone.setText(helper.getPhone(c));
		rtvphone.setText(Long.toString(helper.getPhone(c)));
		byte barr[] = helper.getImage(c);
		if (barr!=null && barr.length>0) {
		 Bitmap bmp = BitmapFactory.decodeByteArray(barr,0,barr.length);
		 ivDp.setImageBitmap(bmp); 
		}
		//context = this;
		//setListAdapter(adapter);
		//setListAda
		//tvbrief.set
		
		Log.d("KARTHIK","Changed..");
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        */
       
    }
	

	}
	
