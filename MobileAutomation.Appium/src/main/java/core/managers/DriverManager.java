package core.managers;

import api.android.Android;
import core.ADB;
import core.MyLogger;
import core.constants.Arg;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DriverManager {

    private static String nodeJS = "C:\\Program Files\\nodejs\\node.exe";
    private static String appiumJS = "C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\lib\\appium.js";
    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;
    private static String unlockPackage = "io.appium.unlock";

    private static DesiredCapabilities getCaps(String deviceID) {
        MyLogger.log.info("Creating driver caps for device: " + deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceID);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/APK/Souq.apk");
        return caps;
    }


    private static URL host(String deviceID) throws MalformedURLException {
        if (hosts == null) {
            hosts = new HashMap<String, URL>();
            hosts.put("emulator-5554", new URL("http://127.0.0.1:4723/wd/hub"));
        }
        return hosts.get(deviceID);
    }

    private static ArrayList<String> getAvailableDevices() {
        MyLogger.log.info("Checking for available devices");
        ArrayList<String> avaiableDevices = new ArrayList<>();
        ArrayList connectedDevices = ADB.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            String device = connectedDevice.toString();
            ArrayList apps = new ADB(device).getInstalledPackages();
            if (!apps.contains(unlockPackage)) avaiableDevices.add(device);
            else
                MyLogger.log.info("Device: " + device + " has " + unlockPackage + " installed, assuming it is under testing");
        }
        if (avaiableDevices.size() == 0)
            throw new RuntimeException("Not a single device is available for testing at this time");
        return avaiableDevices;
    }

    private static DriverService createService() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeJS))
                .withAppiumJS(new File(appiumJS))
                .withIPAddress(host(deviceID).toString().split(":")[1].replace("//",""))
                .usingPort(Integer.parseInt(host(deviceID).toString().split(":")[2].replace("/wd/hub","")))
                .withArgument(Arg.TIMEOUT,"120")
                .withArgument(Arg.LOG_LEVEL,"warn")
                .build();
        return service;
    }

    public static void createDriver() throws MalformedURLException {
        ArrayList<String> devices = getAvailableDevices();
        for (String device : devices) {
            try {
                deviceID = device;
                MyLogger.log.info("Trying to create new Driver for device: " + device);
//                createService().start();
                Android.driver = new AndroidDriver(host(device), getCaps(device));
                Android.adb = new ADB(device);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                //Ignore and try next device
            }
        }
    }

    public static void killDriver() {
        if (Android.driver != null) {
            MyLogger.log.info("Killing Android Driver");
            Android.driver.quit();
            Android.adb.uninstallApp(unlockPackage);
            service.stop();
        } else MyLogger.log.info("Android Driver is not initialized, nothing to kill");
    }
}