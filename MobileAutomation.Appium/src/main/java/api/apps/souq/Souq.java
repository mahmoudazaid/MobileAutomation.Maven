package api.apps.souq;

import api.android.Android;
import api.interfaces.Application;

public class Souq implements Application {

    @Override
    public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }

    @Override
    public void clearData() {
        Android.adb.clearAppsData(packageID());
    }

    @Override
    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    @Override
    public String packageID() {
        return "com.souq.app";
    }

    @Override
    public String activityID() {
        return "com.souq.app.activity.HomeActivity";
    }
}
