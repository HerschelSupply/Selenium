package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertFalse;

/**
 * Page is a structural class for setting up page interactions on a web site.
 * 
 * @param <T> the type of the object extending Page
 * @author tmorris
 * 
 */
public abstract class Page<T extends Page<T>> {

    /** The driver. */
	protected WebDriver driver;
    private final PropertyManager pm = new PropertyManager();

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public Page(final WebDriver driver) {
		super();
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	/**
     * Set the WebDriver object.
     * 
     * @param driver WebDriver object
     */
    protected final void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return the Property Manager
     */
    public PropertyManager getPropertyManager() {
        return pm;
    }

    /**
     * Waits for the page to load
     */
    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        try {
            wait.until(expectation);
        }
        catch(Throwable error) {
            assertFalse(true);
        }
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };
    }

}
