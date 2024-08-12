package com.lesimoes.androidnotificationlistener;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

public class RNAndroidNotificationListenerPackage implements ReactPackage {
    private static final String TAG = "RNAndroidNotificationListenerPackage";

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        try {
            modules.add(new RNAndroidNotificationListenerModule(reactContext));
        }catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }

        return modules;
    }

}
