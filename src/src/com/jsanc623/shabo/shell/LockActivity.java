package com.jsanc623.shabo.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.View;
import android.view.View.OnClickListener;

public class LockActivity extends Activity {
    private ToggleButton toggleButton;
    private String password_a_value;
    private String password_b_value;
    private EditText password_a;
    private EditText password_b;
    private Button btnSave;
    @SuppressWarnings("unused")
	private Boolean state;    

	// Create DB object
    DataProvider db = new DataProvider(LockActivity.this);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
      	addListenerOnButton();
    }
    
    public void addListenerOnButton() {
    	toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
    	btnSave = (Button) findViewById(R.id.btnSave);
   
    	btnSave.setOnClickListener(new OnClickListener() {
   
	  		@Override
	  		public void onClick(View v) {
	  			state = toggleButton.isChecked();
	  			password_a = (EditText)findViewById(R.id.password_a_input);
	  			password_a_value = password_a.getText().toString();
	  			password_b = (EditText)findViewById(R.id.password_b_input);
	  			password_b_value = password_b.getText().toString();
	   	   
	  			if(password_a_value.equals(password_b_value)){
	  				try{
	  				    String destPath = "/data/data/" + getPackageName() + "/databases/ShaboShellDB";
	  				    File f = new File(destPath);
	  				    if(!f.exists()){
	  				        CopyDB(getBaseContext().getAssets().open("ShaboShellDB"), new FileOutputStream(destPath));
	  				    }
	  				} catch (FileNotFoundException e){
	  				    e.printStackTrace();
	  				} catch (IOException e){
	  				    e.printStackTrace();
	  				}
	  				
	  				// Update password
	  		        db.open();
	  		        if (db.updateRecord(1, "", password_a_value, "yes", ""))
	  		            Toast.makeText(LockActivity.this, "Password Update Successful.", Toast.LENGTH_LONG).show();
	  		        else
	  		            Toast.makeText(LockActivity.this, "Password Update Failed.", Toast.LENGTH_LONG).show();        
	  		        db.close();
	  			} else {
	  				AlertDialog.Builder builder = new AlertDialog.Builder(LockActivity.this);
	  				builder.setMessage("Passwords do not match!").setTitle("Error!")
	  				   .setCancelable(true)
	  				   .setPositiveButton("Ok", null);
	  				AlertDialog alert = builder.create();
	  				alert.show();
	  			}
	  		}
	  		
	  	    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
    	        //---copy 1K bytes at a time---
    	        byte[] buffer = new byte[1024];
    	        int length;
    	        while ((length = inputStream.read(buffer)) > 0) {
    	            outputStream.write(buffer, 0, length);
    	        }
    	        inputStream.close();
    	        outputStream.close();
	  	    }
	   
	  	});
    }
}
