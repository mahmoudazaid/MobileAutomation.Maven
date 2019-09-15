package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    AppiumDriver<WebElement> driver;

    public void openDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.1.1");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus6p");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.APP, "D:\\Automation Framewroks\\Java\\MobileAutomation.Maven\\MobileAutomation.Appium\\APK\\Souq.apk");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }


    public void firstTestCase()
    {
        driver.findElement(By.id("lanAndCountryTitle")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("action_apply")).click();
    }


    public  void closeDriver()
    {
        driver.quit();
    }
}