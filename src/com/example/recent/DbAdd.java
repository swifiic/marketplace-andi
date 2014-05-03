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

public class DbAdd extends SQLiteOpenHelper {

	final static String DB_NAME = "imagedetails" ;
	final static int DB_VERSION = 1;
	final static String PATH = "username";		
	final static String APPNAME_ADD = "appname";
	final static String BRIEF_ADD = "brief";
	final static String LONGD_ADD = "longd";
	final static String VERSION_ADD = "version";
	final static String IMAGENAME_ADD = "image";
	Context context = null ;
	
	public DbAdd(Context context) {
		super(context,DB_NAME+".db",null,DB_VERSION);
		this.context =  context ;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table "+DB_NAME+" ( _id integer primary key autoincrement , "+APPNAME_ADD+" text , "+BRIEF_ADD+" text , "+LONGD_ADD+" text , "+VERSION_ADD+" text , "+IMAGENAME_ADD+" blob)" ;
		db.execSQL(sql);
		Log.d("SR7","Table created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.d("SR7","Entered upgrade with old,new : "+oldVersion+", "+ newVersion);
	}
	
	
	public void insert(String appname,String brief,String longd,String version){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(APPNAME_ADD,appname);
		values.put(BRIEF_ADD,brief);
		values.put(LONGD_ADD,longd);
		values.put(VERSION_ADD,version);
		
		db.insertOrThrow(DB_NAME,null,values);
		Toast.makeText(context,"APP Details added",Toast.LENGTH_SHORT).show();
	}
	
	public void uploadImage(String appname,Bitmap bmp){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG,100, stream);
		byte barr[]=stream.toByteArray();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor ;
		String sql = "select * from "+DB_NAME+" where "+APPNAME_ADD+" = '"+appname+"'";
		cursor = db.rawQuery(sql,null);
		cursor.moveToFirst();
		String brief,longd;
		String version ;
		brief = cursor.getString(cursor.getColumnIndex(BRIEF_ADD));
		longd = cursor.getString(cursor.getColumnIndex(LONGD_ADD));
		version = cursor.getString(cursor.getColumnIndex(VERSION_ADD));
		ContentValues values = new ContentValues();
		values.put(APPNAME_ADD,appname);
		values.put(BRIEF_ADD,brief);
		values.put(LONGD_ADD,longd);
		values.put(VERSION_ADD,version);
		values.put(IMAGENAME_ADD,barr);
		db.update(DB_NAME, values,APPNAME_ADD +"= '"+appname+"'",null);
		
	}
	
	String getBrief(Cursor c){
		return c.getString(c.getColumnIndex(BRIEF_ADD));
	}
	
	String getLongd(Cursor c){
		return c.getString(c.getColumnIndex(LONGD_ADD));
	}

	String getAppname(Cursor c){
		return c.getString(c.getColumnIndex(APPNAME_ADD));
	}
	
	String getVersion(Cursor c){
		return c.getString(c.getColumnIndex(VERSION_ADD));
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
