package com.jsanc623.shabo.shell;


/*
To get all installed applications you can use Package Manager..

    List<PackageInfo> applications = getPackageManager().getInstalledPackages(0);
To run you can use package name

Intent LaunchApp = getPackageManager().getLaunchIntentForPackage(“package name”)
startActivity( LaunchApp );
For more detail you can read this 
http://blog.wisecells.com/2012/05/30/get-list-of-all-installed-apps-android/
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
//import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
//import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class MenuActivity extends Activity {
    private static final int CAMERA_REQUEST = 1337;
    private static final int REQUEST_FILE = 1338;
	private static String lastImageSaved = "";
	private static String imageFileLoc;
	private static String imageTmpLoc;
	private static String Folder = "aaShaboShell";
	private ImageView imageView;
    public Uri mImageCaptureUri1;

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
        
        // Assign the faq button an action (send intent for faq activity)
        Button app_faq = (Button) findViewById(R.id.app_faq);
        app_faq.setOnClickListener(onClickListener);
    }
    
    
	public OnClickListener onClickListener = new OnClickListener() {
	    public void onClick(final View v) {
	             switch(v.getId()){
	                 case R.id.take_picture: {
	                	 Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	                	 startActivityForResult(cameraIntent, CAMERA_REQUEST);  
	                	 
	                	 /*String storageState = Environment.getExternalStorageState();
	                     if(storageState.equals(Environment.MEDIA_MOUNTED)) {
		                	 String imageName = String.valueOf(System.currentTimeMillis()) + ".jpg";
		                	 MenuActivity.imageFileLoc = Environment.getExternalStorageDirectory().toString() + "/" + MenuActivity.Folder + "/photos/" + imageName;
		                	 MenuActivity.lastImageSaved = MenuActivity.imageFileLoc;
		                	 MenuActivity.imageTmpLoc = Environment.getExternalStorageDirectory().getPath() + "/" + imageName;
	                         String path = MenuActivity.imageFileLoc;
	                         File _photoFile = new File(MenuActivity.imageTmpLoc);
	                         try {
	                             if(_photoFile.exists() == false) {
	                                 _photoFile.getParentFile().mkdirs();
	                                 _photoFile.createNewFile();
	                             }
	                         } catch (IOException e) { }

	                         Uri _fileUri = Uri.fromFile(_photoFile);
	                         try{
		                         Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		                         
			                     //intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			                     
		                         intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, _fileUri);
		                    	 intent.putExtra("return-data", true);
		                    	 //Toast.makeText(getApplicationContext(), "Opening intent - " + _fileUri.toString(), Toast.LENGTH_LONG).show();
		                         startActivityForResult(intent, CAMERA_REQUEST);
		                     }
		                     catch(ActivityNotFoundException e){
		                         //e.printStackTrace();
		                     }
	                     }   else {
	                         new AlertDialog.Builder(MenuActivity.this)
	                         .setMessage("External Storage (SD Card) is required.\n\nCurrent state: " + storageState)
	                         .setCancelable(true).create().show();
	                     }*/
	                	 
	                	 
	                	 /*Toast.makeText(getApplicationContext(), MenuActivity.imageTmpLoc, Toast.LENGTH_LONG).show();
	                	 
	                	 File picLoc = new File(MenuActivity.imageTmpLoc);
	                	 
	                	 try{
		                	 if(picLoc.exists() == false) {
		                		 picLoc.getParentFile().mkdirs();
		                		 picLoc.createNewFile();
		                     }
		                 } catch (IOException e) {
		                	 Toast.makeText(getApplicationContext(), "No file create", Toast.LENGTH_LONG).show();
		                     Log.e("ShaboShellExp", "Could not create file.", e);
		                 }
	                     
	                     Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                     if (hasImageCaptureBug()) {
	                    	 cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(MenuActivity.imageTmpLoc)));
	                     } else {
	                    	 cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                     }
	                    
	                     // debug
	                     Toast.makeText(getApplicationContext(), MenuActivity.imageTmpLoc, Toast.LENGTH_LONG).show();
	                     
	                     try{
	                    	 cameraIntent.putExtra("return-data", true);
		                     startActivityForResult(cameraIntent, CAMERA_REQUEST);
	                     } catch (ActivityNotFoundException e) {
	                    	 e.printStackTrace();
	                     }*/
	                     
	                 } break;
	                 case R.id.screen_capture: {
	                	 if(Build.VERSION.SDK_INT >= 14){
		                	 Bitmap bitmap;
		                	 View v1 = v.getRootView();
		                	 v1.setDrawingCacheEnabled(true);
		                	 bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		                	 v1.setDrawingCacheEnabled(false);
		                	 saveImage(bitmap, "screenshot-");
	                	 } else {
	                		 showDialog("Function not supported", "This function requires Android 4.0 (SDK 14) and up. Your version is Android " + Build.VERSION.RELEASE + " (SDK " + Build.VERSION.SDK_INT + ")");
	                	 }
	                 } break;
	                 case R.id.my_files: {
	                	 openFileDialog(false, false, "");
	                 } break;
	                 case R.id.app_lock: {
	                	Intent lockIntent = new Intent(MenuActivity.this, LockActivity.class);
	                	MenuActivity.this.startActivity(lockIntent);
	                 } break;
	                 case R.id.app_sound: {
	                	openFileDialog(false, true, "/Music");
	                 } break;
	                 case R.id.app_paint: {
	                 	Intent scribblerIntent = new Intent(MenuActivity.this, Scribbler.class);
	                	MenuActivity.this.startActivity(scribblerIntent);
	                 } break;
	                 case R.id.app_faq: {
	                	Intent faqIntent = new Intent(MenuActivity.this, FAQActivity.class);
	                	MenuActivity.this.startActivity(faqIntent);
	                 } break;
	              }

	    }
	};
	
	private void openFileDialog(Boolean canSelectDirectories, Boolean setOnlyMP3, String additionalPath){
   	 	Intent intent = new Intent(getBaseContext(), FileDialog.class);
   	 	intent.putExtra(FileDialog.START_PATH, Environment.getExternalStorageDirectory().toString() + additionalPath);
     
     	//can user select directories or not
     	if(canSelectDirectories == true){
   	 	    intent.putExtra(FileDialog.CAN_SELECT_DIR, true);
     	} else {
   	 	    intent.putExtra(FileDialog.CAN_SELECT_DIR, false);
     	}
     	
     	//alternatively you can set file filter
     	if(setOnlyMP3 == true){
     	    intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "mp3" });
     	}
     	
     	startActivityForResult(intent, REQUEST_FILE);
	}
	
	public void saveImage(Bitmap finalBitmap, String filepreFix){
		File baseDirectory = Environment.getExternalStorageDirectory();
		File directory = new File(baseDirectory, "/" + MenuActivity.Folder + "/screenshots/");
	    if (!directory.exists()) {
	        if (!directory.mkdirs()) {
	        	Log.e("ShaboShell :: ", "Problem creating Screenshots folder, probably exists");
	        }
	    }
	    
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fileName = filepreFix + "-" + n + ".jpg";
		File file = new File(directory, fileName);
		MenuActivity.lastImageSaved = file.toString();
		if(file.exists()) file.delete();
		try{
			FileOutputStream out = new FileOutputStream(file);
			finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			DataProvider db = new DataProvider(MenuActivity.this);
			db.open();
			db.updateRecord(1, fileName, "", "", "");
			db.close();
			showDialog("New file created!", "Your image has been saved at: " + baseDirectory.toString() + "/" + MenuActivity.Folder + "/screenshots/");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void showDialog(String title, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
		builder.setMessage(message).setTitle(title)
		   .setCancelable(true)
		   .setPositiveButton("Ok", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void showImage(String imageLocation){
		Intent intent = new Intent();  
		intent.setAction(Intent.ACTION_VIEW);  
		Uri imgUri = Uri.parse("file://" + imageLocation);  
		intent.setDataAndType(imgUri, "image/*");  
		startActivity(intent);
	}
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    //Toast.makeText(getApplicationContext(), String.valueOf(requestCode) + " " + String.valueOf(resultCode), Toast.LENGTH_SHORT).show();
	    if (requestCode == CAMERA_REQUEST){
	    	//Toast.makeText(getApplicationContext(), "In onActivityResult()", Toast.LENGTH_LONG).show();
	    	
	    	/*Uri u;
            if (hasImageCaptureBug()) {
                File fi = new File(MenuActivity.lastImageSaved);
                try {
                    u = Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), fi.getAbsolutePath(), null, null));
                    if (!fi.delete()) {
                        Log.i("logMarker", "Failed to delete " + fi);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
               u = data.getData();
            }*/
	    	
	    	Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
	    	saveImage(thumbnail, "photo-");
	    	
	    	//DataProvider db = new DataProvider(MenuActivity.this);
	    	//db.updateRecord(1, data.getData().toString(), "", "", "");	    	
		    //finish();
		}
	    
	    if (requestCode == REQUEST_FILE){
	    	if (resultCode == Activity.RESULT_OK) {
				String filePath = data.getStringExtra(FileDialog.RESULT_PATH);
                Toast.makeText(getApplicationContext(), "filePathReturn: " + filePath, Toast.LENGTH_LONG).show();
	    	}
        }
	}    
	
	public boolean hasImageCaptureBug() {
	    // list of known devices that have the bug
	    ArrayList<String> devices = new ArrayList<String>();
	    devices.add("android-devphone1/dream_devphone/dream");
	    devices.add("generic/sdk/generic");
	    devices.add("vodafone/vfpioneer/sapphire");
	    devices.add("tmobile/kila/dream");
	    devices.add("verizon/voles/sholes");
	    devices.add("google_ion/google_ion/sapphire");

	    return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
	            + android.os.Build.DEVICE);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
