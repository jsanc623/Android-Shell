package com.jsanc623.shabo.shell;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
	  				//TODO: Save passwords to database and clear password_a_input and password_b_input
	  				//TODO: Also, check 'state' and make it default based on state at present
	  			} else {
	  				AlertDialog alertDialog = new AlertDialog.Builder(LockActivity.this).create();
	  				alertDialog.setTitle("Error!");
	  				alertDialog.setMessage("Passwords do not match!");
	  				alertDialog.show();
	  			}
	  		}
	   
	  	});
    }
}
