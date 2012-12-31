package com.jsanc623.shabo.shell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class MainActivity extends Activity {
    private Handler mHandler = new Handler();

	// Create DB object
    DataProvider db = new DataProvider(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.open();
        db.insertRecord("default", "", "no", "default");
        db.close();
        mHandler.postDelayed(new Runnable() {
            public void run() {
            	Intent myIntent = new Intent(MainActivity.this, MenuActivity.class);
            	MainActivity.this.startActivity(myIntent);
            }
        }, 800);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
