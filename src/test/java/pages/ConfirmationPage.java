package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles interactions with the Herschel Order Confirmation Page.
 * 
 * @author tmorris
 * 
 */
public class ConfirmationPage extends Page<ConfirmationPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "h1[class='header--md--mobile']")
	private WebElement ConfirmationTitle;
	@FindBy(css = "p[class='h4']")
	private WebElement OrderNumber;
	@FindBy(css = "p[class='text-copy]")
	private WebElement ConfirmationMessage;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public ConfirmationPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Returns the Confirmation Page Title
	 *
	 * @return String Confirmation Page Title
	 */
	public String getConfirmationTitle() {
        return ConfirmationTitle.getText();
	}

    /**
     * Returns the Order Number
     *
     * @return String Order Number
     */
    public String getOrderNumber() {
        System.out.print(OrderNumber.getText().split("#")[0]);
        System.out.print(" / ");
        System.out.print(OrderNumber.getText().split("#")[1]);
        return OrderNumber.getText().split("#")[1];
    }

    /**
     * Returns the Order Confirmation Message displayed on the Order Confirmation Page
     *
     * @return String Order Confirmation Message
     */
    public String getConfirmationMessage() {
		//wait for Order Summary Page to load
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='account order-summary container--fluid text-grey4 js-order-confirmation']")));
        return ConfirmationMessage.getText();
    }
}
