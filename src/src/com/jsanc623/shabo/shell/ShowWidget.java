package com.jsanc623.shabo.shell;

import java.io.IOException;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class ShowWidget extends ListActivity  {
	DataProvider db = new DataProvider(ShowWidget.this);
	public static String sound;
	MediaPlayer mp = new MediaPlayer();
	private ListView LV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	// change to our configure view
    	setContentView(R.layout.widget_show);
    	
    	/*db.open();
    	Cursor cursor = db.getRecord(1);
    	ShowWidget.sound  = cursor.getString(cursor.getColumnIndex("sound"));
    	play();
    	cursor.close();
    	db.close();*/
    	
    	/*Handler handler = new Handler(); 
        handler.postDelayed(new Runnable() { 
             public void run() { 
         		mp.pause();
         		mp.reset();
             } 
        }, 2500);*/
        
    	Toast.makeText(getApplicationContext(), "Loading your settings.", Toast.LENGTH_SHORT).show();
    	
    	/*LV = (ListView)findViewById(R.id.listView1);

        setListAdapter(new ArrayAdapter<String>(this, R.id.listView1, PENS));

        getListView().setTextFilterEnabled(true);*/
    }
    
    static final String[] PENS = new String[]{
    	"MONT Blanc", "Gucci", "Parker", "Sailor",
    	"Porsche Design", "Rotring", "Sheaffer", "Waterman"
    };
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	/*super.onListItemClick(l, v, position, id);
    	Object o = this.getListAdapter().getItem(position);
    	String pen = o.toString();
    	Toast.makeText(this, "You have chosen the pen: " + " " + pen, Toast.LENGTH_LONG).show();*/
    }
    
	public void play(){
		// Set data source -
		try { mp.setDataSource(ShowWidget.sound);
		} catch(IOException e){}
		
		// Play audio
		mp.start();
    }
}
