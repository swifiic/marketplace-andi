package com.example.recent;




//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//import com.example.sample.UploadPic;
//import java.net.URI;

////import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
//import android.os.Build;
import android.os.Bundle;
//import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
//import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
//import android.view.WindowManager;
//import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
//import android.widget.TextView;
import android.view.MenuItem;
//import android.support.v4.app.NavUtils;

import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Menu;
import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RequestActivity extends Activity implements OnClickListener {

	String username,brief,appname,phonestring;
	long phone;
	int count = 0;
	static final int REQUSET_UPLOADPIC = 2;
	EditText etPath,etbrief,etusername,etphone,etappname;
	Button bUpload,bSave,bList;
	ImageView ivSel;
	static int RESULT_LOAD_IMAGE =1;
	Bitmap bmp=null;
	DbRequest helper;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request);
		helper = new DbRequest(this);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
		//etPath = (EditText) findViewById(R.id.etPath);
		etappname = (EditText) findViewById(R.id.eappname);
		etbrief = (EditText) findViewById(R.id.ebrief);
		etusername = (EditText) findViewById(R.id.eusername);
		etphone = (EditText) findViewById(R.id.ephone);
		bUpload = (Button) findViewById(R.id.bUpload);
		bList = (Button) findViewById(R.id.bList);
		bSave = (Button) findViewById(R.id.bSave);
		bUpload.setOnClickListener(this);
		bList.setOnClickListener(this);
		bSave.setOnClickListener(this);
		ivSel = (ImageView) findViewById(R.id.ivSelected);
		}

	@SuppressWarnings("unused")
	private void setImage(byte[] image) {
		//FileInputStream fis = ;
		 bmp = BitmapFactory.decodeByteArray(image,0,image.length);
		ivSel.setImageBitmap(bmp);
	}

	
	

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bUpload:
		
		//loginAttempt();	
			Intent image = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			 startActivityForResult(image,RESULT_LOAD_IMAGE);
			break;		
		case R.id.bSave:
			
			username = etusername.getText().toString();
			
			brief = etbrief.getText().toString();
			appname = etappname.getText().toString();
			phonestring = etphone.getText().toString();
			if(!phonestring.equals(""))
				phone = Long.parseLong(phonestring);
				else
					phone = 1;
			
			//Toast.makeText(RequestActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
			
			if(count==0)
			{
				attemptLogin();
				
			}
			if(count==0)
			{
				attemptLogin();
				
			}
				if(count ==1)
				{
					
					helper.insert(username, phone, appname, brief);
					helper.uploadImage(username,bmp);
					Intent z = new Intent();
					z.setClass(RequestActivity.this,HomeActivity.class);
					startActivity(z);
					Toast.makeText(RequestActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
				}

			
			 break;
			
			
		case R.id.bList :
			startActivity(new Intent(this,RequestListActivity.class));
		finish();
		break;
		
		}
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==RESULT_OK){
			if (requestCode==RESULT_LOAD_IMAGE){
				Uri uri = data.getData();
				InputStream is = null;
				try {
					is = getContentResolver().openInputStream(uri);
				} catch (FileNotFoundException e) {
					Toast.makeText(this,"File not found",Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				bmp = BitmapFactory.decodeStream(is);
				
				ivSel.setImageBitmap(bmp);
				Toast.makeText(this,"Selected...",Toast.LENGTH_SHORT).show();
				
				
			}
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
	         
	   break;
		case R.id.aboutapp:
	         startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
	    finish();
		
		}
		return true;
		
	
	}
	//------------------------------image check------------------------------------------------------------
   /* 
    public void loginAttemp()
    {
    	
    	
    	
    }
   Request 
    */
	//-------------------------------------------check start------------------------------------------------
    public void attemptLogin() {

		// Reset errors.
    	username = etusername.getText().toString();
		
		brief = etbrief.getText().toString();
		appname = etappname.getText().toString();
		phonestring = etphone.getText().toString();
		if(!phonestring.equals(""))
		phone = Long.parseLong(phonestring);
		else
			phone = 1;
		etappname.setError(null);
		etusername.setError(null);
		etbrief.setError(null);
		etphone.setError(null);
		//mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		//appname = etappname.getText().toString();
		//username = etusername.getText().toString();
	//	mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;
//		String path = null;

		// Check for a valid IdNo.
		if (appname.equals("")) {
			etappname.setError(getString(R.string.error_field_required));
			focusView = etappname;
			cancel = true;
		}
		else if (appname.length() < 3) {
			etappname.setError(getString(R.string.too_short));
			focusView = etappname;
			cancel = true;
		}

		// check for a non-empty course code
		if (username.equals("")) {
			etusername.setError(getString(R.string.error_field_required));
			focusView = etusername;
			cancel = true;
		}
		else if (username.length() < 3) {
			etusername.setError(getString(R.string.too_short));
			focusView = etusername;
			cancel = true;
		}

		if (brief.equals("")) {
			etbrief.setError(getString(R.string.error_field_required));
			focusView = etbrief;
			cancel = true;
		}
		else if (brief.length() < 3) {
			etbrief.setError(getString(R.string.too_short));
			focusView = etbrief;
			cancel = true;
		}
		
		if (phonestring.equals("")) {
			etphone.setError(getString(R.string.error_field_required));
			focusView = etphone;
			cancel = true;
		}else if (phonestring.length() != 10) {
			etphone.setError(getString(R.string.error_invalid_phone_number));
			focusView = etphone;
			cancel = true;
		}
		

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			count = 0;
			focusView.requestFocus();
		} else {
			
			count=1;			
		}
	}
    
    }
