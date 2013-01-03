package com.jsanc623.shabo.shell;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
//import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowWidget extends ListActivity  {
	DataProvider db = new DataProvider(ShowWidget.this);
	public static String sound;
	MediaPlayer mp = new MediaPlayer();
	@SuppressWarnings("unused")
	private ListView LV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	
    	// change to our configure view
    	setContentView(R.layout.widget_show);
    	
    	Toast.makeText(getApplicationContext(), "Loading your settings.", Toast.LENGTH_SHORT).show();
    	
    	LV = (ListView)findViewById(android.R.id.list);
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PENS));
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, PENS));
        getListView().setTextFilterEnabled(true);
    	
    	/*db.open();
    	Cursor cursor = db.getRecord(1);
    	ShowWidget.sound  = cursor.getString(cursor.getColumnIndex("sound"));
    	cursor.close();
    	db.close();*/
    }
    
    static final String[] PENS = new String[]{
    	"MONT Blanc", "Gucci", "Parker", "Sailor",
    	"Porsche Design", "Rotring", "Sheaffer", "Waterman"
    };
        
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	Object o = this.getListAdapter().getItem(position);
    	String pen = o.toString();
    	Toast.makeText(this, "You have chosen the pen: " + " " + pen, Toast.LENGTH_LONG).show();
    }
    
	public void play(){
    }
}
