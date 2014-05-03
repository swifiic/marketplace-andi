package com.example.recent;


import java.util.Locale;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
//import android.app.Activity;
//import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
///import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
//import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
//import android.widget.ListView;
//import android.widget.SearchView;
//import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


import android.app.ListActivity;


//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.SimpleAdapter;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class HomeActivity extends Activity implements  OnItemSelectedListener {
	DbAdd helper ;
	ListView listview;
	Context context;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.d("karthik", " inside oncreate start");
		setContentView(R.layout.activity_home);
		helper = new DbAdd(this);
		listview = (ListView) findViewById(R.id.listview);
		Cursor c = helper.getAll();
		MyAdapter adapter = new MyAdapter(c);
		context =this;
		listview.setAdapter(adapter);
		listview.setOnItemSelectedListener(HomeActivity.this);//changed
		Log.d("KARTHIK","Changed.. inside oncreate");
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //--------------------------------------------testing navigation drawer start-----------------------------
        Log.d("karthik", " inside oncreate testing navigation start");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        Log.d("karthik", " mover1 testing");
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        
        // set up the drawer's list view with items and click listener
        
     //  mDrawerList.setAdapter(new ArrayAdapter<String>(this,
      //        R.layout.drawer_list_item, mPlanetTitles));
        Log.d("karthik", " mover2 testing");
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // enable ActionBar app icon to behave as action to toggle nav drawer
       // Log.d("karthik", " mover3 testing");
        
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
           
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
       
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        Log.d("karthik", " mover3 testing");
       /* if (savedInstanceState == null) {
            selectItem(0);
        }*/
        Log.d("karthik", " inside oncreate end aswell testing navigation end");
        //--------------------------------------------testing navigation drawer end-----------------------------
       }
    
	private class MyAdapter extends CursorAdapter{

		@SuppressWarnings("deprecation")
		public MyAdapter(Cursor c) {
			super(HomeActivity.this,c);
		}
		@Override
		public void bindView(View row, Context context, Cursor c) {
			Log.d("KARTHIK","Changed.. inside bindview");
			ImageView ivDp = (ImageView) row.findViewById(R.id.aivProfile);
			
			TextView tvappname,tvbrief,tvversion;
			tvappname = (TextView) row.findViewById(R.id.atvappname);
			Log.d("KARTHIK","Changed.. inside bindview ending");
			tvbrief = (TextView) row.findViewById(R.id.atvbrief);
			//tvTo = (TextView) row.findViewById(R.id.tvto);
			tvversion = (TextView) row.findViewById(R.id.atvversion);
			tvappname.setText(helper.getAppname(c));
			
			String trim = helper.getBrief(c);
			String org = "";
			
			
			//tvbrief.set
			if(trim.length()>30)
			{
				org = trim.substring(0, 30);
				tvbrief.setText(org+"...");
				
			}
			
			else
			{
				tvbrief.setText(helper.getBrief(c)+"...");
				
			}
			//tvbrief.setText(helper.getBrief(c)+"...");
			//tvTo.setText(helper.getTo(c));
			tvversion.setText(helper.getVersion(c));
			byte barr[] = helper.getImage(c);
			Bitmap bmp = MyUtilities.giveBitmap(barr);
			ivDp.setImageBitmap(bmp);
			
		}
		@Override
		public View newView(Context context, Cursor c, ViewGroup parent) {
			View row = getLayoutInflater().inflate(R.layout.list_row,parent,false);
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
    
    //---------------------------------testing again start-----------------------------------------------------
    

   
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    //-----------------------------------testing ended --------------------------------------------------------
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

	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View v, int arg2,
			long arg3) {
		Toast.makeText(HomeActivity.this, "REQUEST SUCCESSFUL",Toast.LENGTH_LONG).show();
		Log.d("KARTHIK","Changed.. inside onitemselected");

        // when user clicks on ListView Item , onItemClick is called
           // with position and View of the item which is clicked
           //// we can use the position parameter to get index of clicked item
          TextView appname=(TextView)v.findViewById(R.id.atvappname);
          TextView brief=(TextView)v.findViewById(R.id.atvbrief);
          TextView version=(TextView)v.findViewById(R.id.atvversion);
          String appName=appname.getText().toString();
          String Brief=brief.getText().toString();
          String Version = version.getText().toString();
          
          startActivity(new Intent(getApplicationContext(), DownloadActivity.class));
          
          
           // Show The Dialog with Selected SMS
           AlertDialog dialog = new AlertDialog.Builder(context).create();
           dialog.setTitle("SMS From :"+appName);
          // dialog.setIcon(android.R.drawable.ic_dialog_info);
           dialog.setMessage("jhkjjk" + Brief);
           dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                   new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which)
               {
              
                       dialog.dismiss();
                       return;
           }
		
           });
		
		
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}