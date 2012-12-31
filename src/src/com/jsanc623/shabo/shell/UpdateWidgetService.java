package com.jsanc623.shabo.shell;

import java.util.Random;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	@Override
	public void onStart(Intent intent, int startId) {		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		ComponentName thisWidget = new ComponentName(getApplicationContext(),
				WidgetProvider.class);
		@SuppressWarnings("unused")
		int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId : allWidgetIds) {
			// Create some random data
			String number = String.valueOf((new Random().nextInt(100)));

			RemoteViews remoteViews = new RemoteViews(this .getApplicationContext().getPackageName(), 
					R.layout.widget_layout);
			
			// Set the text
			remoteViews.setTextViewText(R.id.update_widget_shabo, "Loading! [" + number + "]");

			// Register an onClickListener
			Intent clickIntent = new Intent(this.getApplicationContext(), WidgetProvider.class);

			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getApplicationContext(), 0, clickIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update_widget_shabo, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		stopSelf();

		// super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}