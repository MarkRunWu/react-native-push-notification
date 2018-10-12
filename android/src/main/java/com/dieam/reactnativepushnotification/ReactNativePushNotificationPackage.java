package com.dieam.reactnativepushnotification;

import android.content.Intent;

import com.dieam.reactnativepushnotification.modules.RNPushNotification;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

public class ReactNativePushNotificationPackage implements ReactPackage {
    private static RNPushNotification mNotification;
    private static Intent mUnhandledIntent;

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        mNotification = new RNPushNotification(reactContext);
        if (mUnhandledIntent != null) {
            mNotification.onNewIntentExplicitly(mUnhandledIntent);
            mUnhandledIntent = null;
        }
        return Collections.<NativeModule>singletonList(mNotification);
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    public static void onNewIntent(Intent intent ) {
        if (mNotification != null) {
            mNotification.onNewIntentExplicitly(intent);
        } else {
            mUnhandledIntent = intent;
        }
    }
}


