package com.lesimoes.androidnotificationlistener;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import android.provider.Settings;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import java.util.Set;

public class RNAndroidNotificationListenerModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;
    private static final String TAG = "RNAndroidNotificationListenerModule";

    public RNAndroidNotificationListenerModule(ReactApplicationContext context) {
        super(context);
        
        this.reactContext = context;
    }

    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getPermissionStatus(Promise promise) {
        try {
            if (this.reactContext == null) {
                promise.resolve("unknown");
            } else {
                String packageName = this.reactContext.getPackageName();
                Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(this.reactContext);
                if (enabledPackages.contains(packageName)) {
                    promise.resolve("authorized");
                } else {
                    promise.resolve("denied");
                }
            }
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
            if(promise!=null) {
                promise.resolve("unknown");
            }
        }
    }
    
    @ReactMethod
    public void requestPermission() {
        try {
            if (this.reactContext != null) {
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                this.reactContext.startActivity(i);
            }
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
