
package com.lesimoes.androidnotificationlistener;

import android.content.Intent;
import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.app.Notification;
import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.facebook.react.HeadlessJsTaskService;

public class RNAndroidNotificationListener extends NotificationListenerService {
    private static final String TAG = "RNAndroidNotificationListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        try {
            Notification statusBarNotification = sbn.getNotification();

            if (statusBarNotification == null || statusBarNotification.extras == null) {
                Log.d(TAG, "The notification received has no data");
                return;
            }

            Context context = getApplicationContext();

            Intent serviceIntent = new Intent(context, RNAndroidNotificationListenerHeadlessJsTaskService.class);

            RNNotification notification = new RNNotification(context, sbn);

            Gson gson = new Gson();
            String serializedNotification = gson.toJson(notification);
            serviceIntent.putExtra("notification", serializedNotification);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                try {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                serviceIntent.putExtra("stops",true);
                                context.startForegroundService(serviceIntent);
                            } catch (Exception e) {

                            }
                        }
                    });
                } catch (Throwable e) {

                }
            } else {
                // Para versões anteriores, use startService
                context.startService(serviceIntent);
            }

            // Acorda o serviço headless JS
            HeadlessJsTaskService.acquireWakeLockNow(context);
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}