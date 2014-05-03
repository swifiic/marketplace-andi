package com.example.recent;

import java.io.ByteArrayOutputStream;
//import java.net.ContentHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
//import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class DbRequest extends SQLiteOpenHelper {

	final static String DB_NAME = "request" ;
	final static int DB_VERSION = 1;
	final static String PATH = "username";		
	final static String USERNAME_ADD = "username";
	final static String BRIEF_ADD = "brief";
	final static String APPNAME_ADD = "appname";
	final static String PHONE_ADD = "phone";
	final static String IMAGENAME_ADD = "image";
	Context context = null ;
	
	public DbRequest(Context context) {
		super(context,DB_NAME+".db",null,DB_VERSION);
		this.context =  context ;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table "+DB_NAME+" ( _id integer primary key autoincrement , "+USERNAME_ADD+" text ,"+PHONE_ADD+" long , "+APPNAME_ADD+" text , "+BRIEF_ADD+" text ,  "+IMAGENAME_ADD+" blob)" ;
		db.execSQL(sql);
		Log.d("karthik","Table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.d("karthik","Entered upgrade with old,new : "+oldVersion+", "+ newVersion);
	}
	
	
	public void insert(String username,long phone,String appname,String brief){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USERNAME_ADD,username);
		values.put(PHONE_ADD,phone);
		values.put(APPNAME_ADD,appname);
		values.put(BRIEF_ADD,brief);
		
		
		db.insertOrThrow(DB_NAME,null,values);
		Toast.makeText(context,"Submitted Request Successfully",Toast.LENGTH_SHORT).show();
	}
	
	public void uploadImage(String username,Bitmap bmp){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG,70, stream);
		byte barr[]=stream.toByteArray();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor ;
		String sql = "select * from "+DB_NAME+" where "+USERNAME_ADD+" = '"+username+"'";
		cursor = db.rawQuery(sql,null);
		cursor.moveToFirst();
		String appname,brief;
		long phone ;
		phone = cursor.getInt(cursor.getColumnIndex(PHONE_ADD));
		appname = cursor.getString(cursor.getColumnIndex(APPNAME_ADD));
		brief = cursor.getString(cursor.getColumnIndex(BRIEF_ADD));
		
		ContentValues values = new ContentValues();
		values.put(USERNAME_ADD,username);
		values.put(PHONE_ADD,phone);
		values.put(APPNAME_ADD,appname);
		values.put(BRIEF_ADD,brief);
		values.put(IMAGENAME_ADD,barr);
		db.update(DB_NAME, values,USERNAME_ADD +"= '"+username+"'",null);
		
	}
	
	
	
	String getUsername(Cursor c){
		return c.getString(c.getColumnIndex(USERNAME_ADD));
	}
	long getPhone(Cursor c){
		return c.getInt(c.getColumnIndex(PHONE_ADD));
	}
	String getAppname(Cursor c){
		return c.getString(c.getColumnIndex(APPNAME_ADD));
	}
	String getBrief(Cursor c){
		return c.getString(c.getColumnIndex(BRIEF_ADD));
	}
	
	
	byte[] getImage(Cursor c){
		return c.getBlob(c.getColumnIndex(IMAGENAME_ADD));
	}
	
	
	public Cursor getAll(){
		String sql = "select * from "+DB_NAME;
		SQLiteDatabase db = getWritableDatabase();
		return db.rawQuery(sql,null);
	}
	
}
