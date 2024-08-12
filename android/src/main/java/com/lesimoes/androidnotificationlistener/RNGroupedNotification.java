package com.lesimoes.androidnotificationlistener;
import android.util.Log;
import android.text.TextUtils;

public class RNGroupedNotification {
    protected String title;
    protected String text;
    private static final String TAG ="RNGroupedNotification";

    public RNGroupedNotification(RNNotification mainNotification, CharSequence message) {
        try {
            String formatedMessage = message.toString().trim();

            this.title = !TextUtils.isEmpty(mainNotification.title) ? mainNotification.title : "";
            this.text = !TextUtils.isEmpty(mainNotification.text) ? mainNotification.text : "";

            int endIndex = formatedMessage.indexOf(":");

            if (endIndex != -1) {
                this.title = formatedMessage.substring(0, endIndex).trim();
                this.text = formatedMessage.substring(endIndex + 1).trim();
            } else {
                this.text = formatedMessage;
            }
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }
}