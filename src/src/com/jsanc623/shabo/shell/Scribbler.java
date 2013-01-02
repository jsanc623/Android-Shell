package com.jsanc623.shabo.shell;

import android.app.Activity;
import android.graphics.Color;
//import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressWarnings("unused")
public class Scribbler extends Activity {
    DrawView drawView;
    MenuActivity MenuActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scribbler_main);
        
		DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
    }
    
    @Override
    protected void onStop(){
       super.onStop();
    }

    //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
       super.onDestroy();
    }
}