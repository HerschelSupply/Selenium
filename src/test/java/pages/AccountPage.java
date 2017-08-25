package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles interactions with the Herschel Account Page.
 * 
 * @author tmorris
 * 
 */
public class AccountPage extends Page<AccountPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "input[id='email']")
	private WebElement EmailField;
	@FindBy(css = "input[id='password_login']")
	private WebElement PasswordField;
	@FindBy(css = "button[id='hsco-signin']")
	private WebElement SignInButton;
	@FindBy(css = "button[class='button button--primary button--submit hsco-account-signout']")
	private WebElement SignOutButton;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public AccountPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Load the Herschel Account Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
        if(country.equals("US")) {
            driver.get("https://qa.herschel.com/shop/account");
        }
        else if(country.equals("UK")) {
            driver.get("https://qa.herschelsupplyco.co.uk/shop/account");
        }
        else if(country.equals("EU")) {
            driver.get("https://qa.herschel.eu/shop/account");
        }
        else {
            driver.get("https://qa.herschel.ca/shop/account");
        }
	}

	/**
	 * Click the Little America Backpack Tile.
	 *
	 * @param email email address used to sign in
	 * @param password password used to sign in
	 */
	public void signIn(String email, String password) {
		EmailField.sendKeys(email);
		PasswordField.sendKeys(password);
		SignInButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(SignOutButton));
	}
}
