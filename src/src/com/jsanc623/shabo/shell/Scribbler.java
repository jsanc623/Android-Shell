package com.jsanc623.shabo.shell;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

@SuppressWarnings("unused")
public class Scribbler extends Activity {
    DrawView drawView;
    MenuActivity MenuActivity;
    //Bitmap b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scribbler_main);
        
	   	RelativeLayout parent = (RelativeLayout) findViewById(R.id.layout);
		DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        //parent.addView(drawView); // < causes force close waiting for focus
        setContentView(drawView);
        //drawView.requestFocus();
	   	 
	   	/*parent.setDrawingCacheEnabled(true);
	   	b = parent.getDrawingCache();*/
    }
    
    @Override
    protected void onStop(){
 	   //MenuActivity.saveImage(b, "custom");
       super.onStop();
    }

    //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
       super.onDestroy();
    }
}