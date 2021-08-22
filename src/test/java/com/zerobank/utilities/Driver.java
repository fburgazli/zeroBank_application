package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //Creating the private constructor so
    // this class' object is not reachable from outside
    private Driver(){}

    //making our 'driver' instance private so that it is not reachable from outside of the class.
    //we make it static because we want it to run before everything else, and also we will use in a static method.
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    //creating re-usable utility method that will return same driver instance everytime we call it.
    public static WebDriver getDriver() {

        if(driverPool.get() == null){

            synchronized (Driver.class) {

                //we read our browser type from configuration.properties file
                // using .getProperty() method in ConfigurationReader.class
                String browserType = ConfigurationReader.getProperty("browser");

                //depending on the browser type our switch statement will determine to open
                //specific type of browser/driver.
                switch (browserType) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver());
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "chromeSSL":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions capability = new ChromeOptions();
                        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                        driverPool.set(new ChromeDriver(capability));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                }
            }
        }
        //same driver instance will be returned everytime we call Driver.getDriver(); method.
        return driverPool.get();
    }

    //this method makes sure we have some form of driver session or driver id has.
    //either null or not null it must exist.
    public static void closeDriver(){
        if(driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
