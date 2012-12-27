package com.jsanc623.shabo.shell;




/*
To get al installed apps you can use Package Manager..

    List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
To run you can use package name

Intent LaunchApp = getPackageManager().getLaunchIntentForPackage(“package name”)
startActivity( LaunchApp );
For more detail you can read this blog 
http://blog.wisecells.com/2012/05/30/get-list-of-all-installed-apps-android/
*/


import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends Activity {
    private static final int CAMERA_REQUEST = 1337;
    @SuppressWarnings("unused")
	private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        // Assign the take picture button an action (open camera, get picture return)
        Button take_picture = (Button) findViewById(R.id.take_picture);
        take_picture.setOnClickListener(onClickListener);
        
        // Assign the screen capture button an action (take screen shot)
        Button screen_capture = (Button) findViewById(R.id.screen_capture);
        screen_capture.setOnClickListener(onClickListener);
        
        // Assign the my files button an action (open file manager, return file)
        Button my_files = (Button) findViewById(R.id.my_files);
        my_files.setOnClickListener(onClickListener);
        
        // Assign the app lock button an action (define password, save password)
        Button app_lock = (Button) findViewById(R.id.app_lock);
        app_lock.setOnClickListener(onClickListener);
        
        // Assign the app sound button an action (open file manager, return sound)
        Button app_sound = (Button) findViewById(R.id.app_sound);
        app_sound.setOnClickListener(onClickListener);
        
        // Assign the app paint button an action (send intent for paint program)
        Button app_paint = (Button) findViewById(R.id.app_paint);
        app_paint.setOnClickListener(onClickListener);
    }
    
    
	public OnClickListener onClickListener = new OnClickListener() {
	    public void onClick(final View v) {
	             switch(v.getId()){
	                 case R.id.take_picture: {
	                	 Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	                     startActivityForResult(cameraIntent, CAMERA_REQUEST); 
	                 }
	                 case R.id.screen_capture: {
	                	 if(Build.VERSION.RELEASE <= 4.0){
	                		 showDialog("Function supported", "Attempting to take a screenshot now.");
		                	 Bitmap bitmap;
		                	 View v1 = v.getRootView();
		                	 v1.setDrawingCacheEnabled(true);
		                	 bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		                	 v1.setDrawingCacheEnabled(false);
		                	 saveImage(bitmap);
	                	 } else {
	                		 showDialog("Function not supported", "This function requires Android 4.0 and up. Your version is " + Build.VERSION.RELEASE);
	                	 }
	                 }
	                 case R.id.my_files: {
	                	 // implements: http://code.google.com/p/android-file-dialog/
	                 }
	                 case R.id.app_lock: {
	                	 
	                 }
	                 case R.id.app_sound: {
	                	 // implements: http://code.google.com/p/android-file-dialog/
	                 }
	                 case R.id.app_paint: {
	                	 
	                 }
	                 break;
	              }

	    }
	};
	
	private void saveImage(Bitmap finalBitmap){
		File baseDirectory = Environment.getExternalStorageDirectory();
		File directory = new File(baseDirectory, "/aaShaboShell/screenshots/");
	    if (!directory.exists()) {
	        if (!directory.mkdirs()) {
	        	Log.e("ShaboShell :: ", "Problem creating Screenshots folder, probably exists");
	        }
	    }
		
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fileName = "Screenshot-" + n + ".jpg";
		File file = new File(directory, fileName);
		if(file.exists()) file.delete();
		try{
			FileOutputStream out = new FileOutputStream(file);
			finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			showDialog("New file created!", "Your screenshot has been saved at: " + baseDirectory.toString() + "/aaShaboShell/screenshots/");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void showDialog(String title, String message){
		 AlertDialog alertDialog = new AlertDialog.Builder(MenuActivity.this).create();
		 alertDialog.setTitle(title);
		 alertDialog.setMessage(message);
		 alertDialog.show();
	}
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    if (requestCode == CAMERA_REQUEST){
	    	// The columns we want
	    	String[] projection = {
    			 MediaStore.Images.Thumbnails._ID,
    			 MediaStore.Images.Thumbnails.IMAGE_ID,
    			 MediaStore.Images.Thumbnails.KIND,
    			 MediaStore.Images.Thumbnails.DATA
	    	};

	    	// Select only mini's
			String selection = MediaStore.Images.Thumbnails.KIND + "=" + 
					 		   MediaStore.Images.Thumbnails.MINI_KIND;
			String sort = MediaStore.Images.Thumbnails._ID + " DESC";

    		// At the moment, this is a bit of a hack, as I'm returning ALL images, 
			// and just taking the latest one.
    	    Cursor myCursor = this.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, 
    	   		 	 	   	 				    projection, selection, null, sort);

			long imageId = 0l;
			long thumbnailImageId = 0l;
			@SuppressWarnings("unused")
			String thumbnailPath = "";

			try{
				myCursor.moveToFirst();
				imageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID));
				thumbnailImageId = myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
				thumbnailPath = myCursor.getString(myCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
			}
			finally{ myCursor.close(); }

			// Create new Cursor to obtain the file Path for the large image
			String[] largeFileProjection = {
					 MediaStore.Images.ImageColumns._ID,
					 MediaStore.Images.ImageColumns.DATA
			};

			String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
			myCursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
					 					 largeFileProjection, null, null, largeFileSort);
			@SuppressWarnings("unused")
			String largeImagePath = "";

			try{
		         myCursor.moveToFirst();

		         // This will actually give you the file path location of the image.
		         largeImagePath = myCursor.getString(myCursor.getColumnIndexOrThrow(
		        		 								MediaStore.Images.ImageColumns.DATA));
			}
			finally{ myCursor.close(); }

			// These are the two URI's you'll be interested in. They give you a handle to the actual images
			@SuppressWarnings("unused")
			Uri uriLargeImage = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
												     String.valueOf(imageId));
			@SuppressWarnings("unused")
			Uri uriThumbnailImage = Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, 
					                                 String.valueOf(thumbnailImageId));
		}
	}    
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
