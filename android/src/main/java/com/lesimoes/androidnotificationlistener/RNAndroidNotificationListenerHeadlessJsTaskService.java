package com.lesimoes.androidnotificationlistener;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

import javax.annotation.Nullable;

public class RNAndroidNotificationListenerHeadlessJsTaskService extends HeadlessJsTaskService {
    private static final String TAG = "RNAndroidNotificationListenerHeadlessJsTaskService";

    @Override
    protected @Nullable HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                return new HeadlessJsTaskConfig(
                        "RNAndroidNotificationListenerHeadlessJs",
                        Arguments.fromBundle(extras),
                        15000, // timeout for the task
                        true // optional: defines whether or not  the task is allowed in foreground. Default is false
                );
            }
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}