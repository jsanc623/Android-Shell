package com.jsanc623.shabo.shell; 

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
//import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, ShowWidget.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.id.layout_widget_shabo);
            views.setOnClickPendingIntent(R.id.layout_widget_shabo, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        
		// Get all id
		/*ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// Build the intent to call the service
		Intent intent = new Intent(context.getApplicationContext(), UpdateWidgetService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
		
		// Update the widgets via the service
		context.startService(intent);*/
	}
}