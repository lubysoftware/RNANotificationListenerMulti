package com.lesimoes.androidnotificationlistener;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootUpReceiver extends BroadcastReceiver {
    private static final String TAG = "BootUpReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if(intent!=null && Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(new Intent(context, RNAndroidNotificationListener.class));
                        return;
                    }
                    context.startService(new Intent(context, RNAndroidNotificationListener.class));
            }
        }catch (Throwable t){
            Log.e(TAG, "Error starting service: " + t.getMessage());
        }

    }
}