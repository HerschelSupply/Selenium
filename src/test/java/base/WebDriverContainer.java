package base;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

/**
 * Container for WebDriver.
 * 
 * @author tmorris
 * 
 */
public class WebDriverContainer {
	
	private static WebDriver driver;
	private final PropertyManager pm = new PropertyManager();
	private ChromeOptions options = new ChromeOptions();

    public static final String USERNAME = "herchelsupplyco";
    public static final String ACCESS_KEY = "38f9d6bb-603a-4b41-86c9-7ac11450b3f0";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    /**
     * Gets the webDriver.
     * 
     * @return WebDriver
     */
    public WebDriver getDriver() {
    	if (driver == null) {
            startDriver();
        }
        return driver;
    }
    
    /**
     * starts the webDriver.
     * 
     * @return WebDriver
     */
    public void startDriver() {
        final DesiredCapabilities firefoxCaps = DesiredCapabilities.firefox();
        final DesiredCapabilities htmlUnitCaps = DesiredCapabilities.htmlUnit();
    	final String driverType = pm.getProperty("selenium.driverType", "local").toLowerCase();

        if ("remote".equals(driverType)) {
            driver = getRemoteWebDriver(firefoxCaps);
        } else if ("headless".equals(driverType)) {
            driver = getHeadlessWebDriver(htmlUnitCaps);
        } else if ("chrome".equals(driverType)) {
            driver = getChromeWebDriver();
        } else if ("saucewindowschrome".equals(driverType)) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("platform", "Windows 10");
            //caps.setCapability("version", " 61.0");
            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if ("sauce".equals(driverType)) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setBrowserName(System.getenv("SELENIUM_BROWSER"));
            caps.setVersion(System.getenv("SELENIUM_VERSION"));
            caps.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));

            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            driver = getFirefoxWebDriver(firefoxCaps);
            driver.manage().window().maximize();
        }
    }

    private WebDriver getFirefoxWebDriver(final DesiredCapabilities capabilities) {
        return new FirefoxDriver(capabilities);
    }
    
    private WebDriver getChromeWebDriver() {
    	String chromeDriverPath = pm.getProperty("chrome.driver.path");
    	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	options.addArguments("--start-maximized"); 
    	return new ChromeDriver(options);
    }

    private WebDriver getHeadlessWebDriver(final DesiredCapabilities caps) {
        //disabling Javascript for HtmlUnit Driver as per Selenium recommended practice
        caps.setJavascriptEnabled(false);
        return new HtmlUnitDriver(caps);
    }
    
    private WebDriver getRemoteWebDriver(final DesiredCapabilities origCaps) {
        final DesiredCapabilities desCaps = new DesiredCapabilities();
        desCaps.merge(origCaps);
        desCaps.setBrowserName("firefox");
        final URL url = getSeleniumURL("/wd/hub");

        return new RemoteWebDriver(url, desCaps);
    }

    public Boolean isDriverHeadless() {
        final String driverType = pm.getProperty("selenium.driverType", "local").toLowerCase();
        if (driverType.equals("headless")) {
            return true;
        } else {
            return false;
        }
    }
    
    private URL getSeleniumURL(final String path) {
        final String host = pm.getProperty("selenium.server.host", "localhost");
        final String port = pm.getProperty("selenium.server.port", "4444");
        URL url = null;
        try {
            url = new URL("http", host, Integer.parseInt(port), path);
        } catch (MalformedURLException e) {
            Assert.fail(String.format("URL: 'http://%s:%s/%s', is malformed", host, port, path));
        }
        return url;
    }
    
    public void setDriverToNull() {
    	if(driver != null) {
    		driver = null;
    	}
    }
}
