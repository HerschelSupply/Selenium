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
 * Handles interactions with the Herschel Checkout Page.
 * 
 * @author tmorris
 * 
 */
public class CheckoutPage extends Page<CheckoutPage> {

    private Wait<WebDriver> wait;
	//WebElements for Registered Checkout
	@FindBy(css = "input[name='hsco-checkout-email']")
	private WebElement EmailField;
	@FindBy(css = "input[name='hsco-checkout-password']")
	private WebElement PasswordField;
    @FindBy(css = "button[id='hsco-signin']")
    private WebElement SignInButton;
    @FindBy(css = "button[class='button button--primary button--submit hsco-account-signout']")
    private WebElement SignOutButton;
	@FindBy(css = "button[id='hsco-section2-next']")
	private WebElement ShippingAddressNext;
	@FindBy(css = "button[id='hsco-section3-next']")
	private WebElement ShippingMethodNext;
	@FindBy(css = "button[id='hsco-section4-next']")
	private WebElement PaymentNext;
	@FindBy(css = "button[id='hsco-section5-next']")
	private WebElement PlaceOrder;
	@FindBy(css = "select[id='shippingMethods']")
	private WebElement ShippingMethods;
	@FindBy(css = "select[id='paymentmethods']")
	private WebElement PaymentMethods;

	//WebElements for Guest Checkout
	@FindBy(css = "input[id='email_register']")
	private WebElement GuestEmailField;
	@FindBy(css = "button[id='hsco-checkout-register-submit']")
	private WebElement GuestCheckoutButton;
	@FindBy(css = "input[id='shipto_givenName']")
	private WebElement ShippingAddressGivenName;
	@FindBy(css = "input[id='shipto_familyName']")
	private WebElement ShippingAddressFamilyName;
	@FindBy(css = "input[id='shipto_streetAddress']")
	private WebElement ShippingStreetAddress;
	@FindBy(css = "input[id='shipto_extendedAddress']")
	private WebElement ShippingStreetAddressCont;


    /**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public CheckoutPage(final WebDriver driver) {
		super(driver);
        wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Load the Herschel Checkout Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
		if(country.equals("US")) {
			driver.get("https://qa.herschel.com/shop/checkout");
		}
		else if(country.equals("UK")) {
			driver.get("https://qa.herschelsupplyco.co.uk/shop/checkout");
		}
		else if(country.equals("EU")) {
			driver.get("https://qa.herschel.eu/shop/checkout");
		}
		else {
			driver.get("https://qa.herschel.ca/shop/checkout");
		}
		//wait for page loading to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
	}

	/**
	 * Sign in using the specified credentials.
     *
     * @param email email address used to sign in
     * @param password password used to sign in
	 */
	public void signIn(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(EmailField));
		EmailField.sendKeys(email);
		PasswordField.sendKeys(password);
		SignInButton.click();
		//Need to wait for the loading overlay to disappear and the Shipping Address Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(ShippingAddressNext));
	}

	/**
	 * Selects the default shipping address.
	 */
	public void selectDefaultShippingAddress() {
		//Need to wait for the loading overlay to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		ShippingAddressNext.click();
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
	}

	/**
	 * Selects the default shipping method.
	 */
	public void selectDefaultShippingMethod() {
		//Need to wait for the loading overlay to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		for (WebElement option : ShippingMethods.findElements(By.tagName("option"))) {
			if (option.getText().equals("Expedited Shipping - $25")) {
				option.click();
			}
		}

		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
		//duplicate wait below, is this necessary?
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		//wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
		ShippingMethodNext.click();
		//Need to wait for the loading overlay to disappear and the Payment Methods Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
	}

	/**
	 * Selects the default payment methods.
	 */
	public void selectDefaultPaymentMethod() {
		//Need to wait for the loading overlay to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		for (WebElement option : PaymentMethods.findElements(By.tagName("option"))) {
			if (option.getText().contains("MasterCard")) {
				option.click();
			}
		}
		//Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
		//duplicate wait below, is this necessary?
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
		PaymentNext.click();
		//Need to wait for the loading overlay to disappear and the Place Order button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
	}

    /**
     * Completes checkout by clicking on the Place Order button.
     */
    public void placeOrder() {
    	//Need to wait for the loading overlay to disappear and the Place Order button to be clickable before proceeding
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
		PlaceOrder.click();
    }
}
