package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Fragment. It provides for interactions of the same elements on multiple pages.
 *
 * @author tmorris
 *
 */
public abstract class Fragment<T extends Fragment<T>> extends LoadableComponent<T>  {

    /** The driver. */
    protected WebDriver driver;
    private final PropertyManager pm = new PropertyManager();
    private static WebElement fragmentRoot;

    /**
     * Default constructor.
     * @param driver the WebDriver
     */
    public Fragment(final WebDriver driver, WebElement root) {
        super();
        setDriver(driver);
        setFragmentRoot(root);
        PageFactory.initElements(driver, this);
    }

    /**
     * Set the webdriver object.
     *
     * @param driver webdriver object
     */
    protected final void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    public void setFragmentRoot(WebElement root) {
        fragmentRoot = root;
    }

    /**
     * Get the fragmentRoot.
     *
     * @return fragmentRoot element
     */
    public WebElement getFragmentRoot() {
        return fragmentRoot;
    }

    /**
     * check existence.
     *
     * @return true/false for existence.
     */
    public boolean exists() {
        try {
            fragmentRoot.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElement(By by, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
