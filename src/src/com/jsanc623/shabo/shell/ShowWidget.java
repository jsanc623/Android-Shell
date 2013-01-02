package com.jsanc623.shabo.shell;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ShowWidget extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	// change to our configure view
    	setContentView(R.layout.widget_show);
    	
    	// don't call 'this', use 'getApplicationContext()', the activity-object is 
    	// bigger than just the context because the activity also stores the UI elements
    	Toast.makeText(getApplicationContext(), "Loading your settings.", Toast.LENGTH_SHORT).show();
    }
}
