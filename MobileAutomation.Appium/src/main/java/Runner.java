import api.android.Android;
import core.MyLogger;
import core.managers.DriverManager;
import org.apache.log4j.Level;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        MyLogger.log.setLevel(Level.INFO);
        Android driver = null;
        try {
            DriverManager.createDriver();
            Android.adb.openAppsActivity("com.souq.app","com.souq.app.activity.HomeActivity");
        } finally {
            if(driver != null) DriverManager.killDriver();
        }
    }
}
