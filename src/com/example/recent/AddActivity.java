package com.example.recent;




//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
//import java.net.URI;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {

	
	String appname,brief,longd,version;
	int count = 0;
	EditText etPath,etbrief,etlongd,etversion,etappname;
	Button bUpload,bSave,bList;
	ImageView ivSel;
	static int RESULT_LOAD_IMAGE =1;
	Bitmap bmp=null;
	DbAdd helper =null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);
		helper = new DbAdd(this);
		
		//etPath = (EditText) findViewById(R.id.etPath);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.androidimage);
		etappname = (EditText) findViewById(R.id.etappname);
		etbrief = (EditText) findViewById(R.id.etbrief);
		etlongd = (EditText) findViewById(R.id.etlongd);
		etversion = (EditText) findViewById(R.id.etversion);
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
		Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
		ivSel.setImageBitmap(bmp);
	}

	
	

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bUpload:
		Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i,RESULT_LOAD_IMAGE);	break;	
		
		case R.id.bSave:
			
			appname = etappname.getText().toString();
			brief = etbrief.getText().toString();
			longd = etlongd.getText().toString();
			version = etversion.getText().toString();
			if(version.equals(""))
				version = "1";
					
			
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
					helper.insert(appname, brief, longd, version);
					helper.uploadImage(appname,bmp); 
					/*Intent p = new Intent();
					p.setClass(AddActivity.this,HomeActivity.class);
					startActivity(p);*/
					Toast.makeText(AddActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
				}
			
			
			
			break;
			
			
			
			
		case R.id.bList :
			startActivity(new Intent(this,HomeActivity.class));
		
		
		
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
	       
	        break;
		
		case R.id.login:
	         startActivity(new Intent(getApplicationContext(), LoginActivity.class));
	    
	    break;
	    
		case R.id.seeallrequests:
	         startActivity(new Intent(getApplicationContext(), RequestListActivity.class));
	   break;
		case R.id.aboutapp:
	         startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
		
		}
		return true;
		
	
	}
	

    
    public void attemptLogin() {

		// Reset errors.
    	
    	appname = etappname.getText().toString();
		brief = etbrief.getText().toString();
		longd = etlongd.getText().toString();
		version = etversion.getText().toString();
		if(version.equals(""))
			version = "1";
			
		etappname.setError(null);
		etlongd.setError(null);
		etbrief.setError(null);
		etversion.setError(null);
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
		if (longd.equals("")) {
			etlongd.setError(getString(R.string.error_field_required));
			focusView = etlongd;
			cancel = true;
		}
		else if (longd.length() < 3) {
			etlongd.setError(getString(R.string.too_short));
			focusView = etlongd;
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
		
		if (version.equals("")) {
			etversion.setError(getString(R.string.error_field_required));
			focusView = etversion;
			cancel = true;
		}/*else if (versionstring.length() < 1) {
			etversion.setError(getString(R.string.error_invalid_version_number));
			focusView = etversion;
			cancel = true;
		}
		*/

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
