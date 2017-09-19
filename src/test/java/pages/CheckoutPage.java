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
	@FindBy(css = "select[id='addresses']")
	private WebElement ShippingAddresses;
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
	private WebElement ShippingGivenName;
	@FindBy(css = "input[id='shipto_familyName']")
	private WebElement ShippingFamilyName;
	@FindBy(css = "input[id='shipto_streetAddress']")
	private WebElement ShippingStreetAddress;
	@FindBy(css = "input[id='shipto_extendedAddress']")
	private WebElement ShippingStreetAddressCont;
	@FindBy(css = "input[id='shipto_locality']")
	private WebElement ShippingCity;
    @FindBy(css = "select[id='shipto_region']")
    private WebElement ShippingRegion;
    @FindBy(css = "input[id='shipto_postalCode']")
    private WebElement ShippingPostalCode;
    @FindBy(css = "select[id='shipto_country']")
    private WebElement ShippingCountry;
    @FindBy(css = "input[id='shipto_phoneNumber']")
    private WebElement ShippingPhoneNumber;
    @FindBy(css = "button[id='hsco-add-address']")
    private WebElement AddAddressButton;
    @FindBy(css = "input[id='addressNew']")
    private WebElement EnterNewAddress;
    @FindBy(css = "input[id='hsco-card-name']")
    private WebElement CardName;
    @FindBy(css = "input[id='hsco-card-number']")
    private WebElement CardNumber;
    @FindBy(css = "input[id='hsco-exp-MM']")
    private WebElement CardExpMonth;
    @FindBy(css = "input[id='hsco-exp-YY']")
    private WebElement CardExpYear;
    @FindBy(css = "input[id='hsco-card-cvc']")
    private WebElement CardCVC;
    @FindBy(css = "button[id='hsco-add-payment']")
    private WebElement AddPaymentButton;
    @FindBy(css = "input[id='newCard']")
    private WebElement EnterNewCard;

    //Cart WebElements
    @FindBy(css = "p[class='m-y-0 text-cta bfx-product-name']")
    private WebElement CartProductName;
    @FindBy(css = "p[class='m-y-0 bfx-product-color']")
    private WebElement CartSkuName;

    /**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public CheckoutPage(final WebDriver driver) {
		super(driver);
        wait = new WebDriverWait(driver, 12);
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
        wait.until(ExpectedConditions.elementToBeClickable(ShippingAddresses));
		ShippingAddresses.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		for (WebElement option : ShippingAddresses.findElements(By.tagName("option"))) {
			if (!option.getText().equals("Select an address")) {
				option.click();
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(ShippingAddressNext));
		ShippingAddressNext.click();
		//If click failed (fairly common occurrence in checkout), attempt to click again
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		if (driver.findElements(By.cssSelector("button[id='hsco-section2-next']")).size() > 0 ) {
		    if (driver.findElements(By.cssSelector("button[id='hsco-section2-next']")).get(0).isDisplayed()) {
                ShippingAddressNext.click();
            }
		}
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
		ShippingMethods.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		for (WebElement option : ShippingMethods.findElements(By.tagName("option"))) {
			if (option.getText().equals("Standard Shipping - FREE")) {
				option.click();
			}
		}
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
		ShippingMethodNext.click();
		//If click failed (fairly common occurrence in checkout), attempt to click again
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        if (driver.findElements(By.cssSelector("button[id='hsco-section3-next']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-section3-next']")).get(0).isDisplayed()) {
                ShippingMethodNext.click();
            }
        }
		//Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
	}

	/**
	 * Selects the default payment methods.
	 */
	public void selectDefaultPaymentMethod() {
		//Need to wait for the loading overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
		PaymentMethods.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		for (WebElement option : PaymentMethods.findElements(By.tagName("option"))) {
			if (option.getText().equals("Visa ending in *4242")) {
				option.click();
			}
		}
		//Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
		PaymentNext.click();
		//If click failed (fairly common occurrence in checkout), attempt to click again
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        if (driver.findElements(By.cssSelector("button[id='hsco-section4-next']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-section4-next']")).get(0).isDisplayed()) {
                PaymentNext.click();
            }
        }
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
		//If click failed (fairly common occurrence in checkout), attempt to click again
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        if (driver.findElements(By.cssSelector("button[id='hsco-section5-next']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-section5-next']")).get(0).isDisplayed()) {
                PlaceOrder.click();
            }
        }
    }

    /**
     * Continue Checkout as a Guest.
     *
     * @param email email address used to checkout as a guest
     */
    public void checkoutAsGuest(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(GuestEmailField));
        GuestEmailField.sendKeys(email);
        GuestCheckoutButton.click();
        //Need to wait for the loading overlay to disappear and the Add Address button to be clickable before proceeding
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        wait.until(ExpectedConditions.elementToBeClickable(AddAddressButton));
    }

    /**
     * Enter a new Shipping Address.
     *
     * @param givenName value to enter in shipping address field
     * @param familyName value to enter in shipping address field
     * @param streetAddress value to enter in shipping address field
     * @param extendedAddress value to enter in shipping address field
     * @param city value to enter in shipping address field
     * @param region value to enter in shipping address field
     * @param mailingCode value to enter in shipping address field
     * @param shippingCountry value to enter in shipping address field
     * @param phoneNumber value to enter in shipping address field
     */
    public void addShippingAddress(String givenName, String familyName, String streetAddress, String extendedAddress, String city, String region, String mailingCode, String shippingCountry, String phoneNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(AddAddressButton));
        ShippingGivenName.sendKeys(givenName);
        ShippingFamilyName.sendKeys(familyName);
        ShippingStreetAddress.sendKeys(streetAddress);
        ShippingStreetAddressCont.sendKeys(extendedAddress);
        ShippingCity.sendKeys(city);
        if(driver.getCurrentUrl().startsWith("https://qa.herschel.com") || driver.getCurrentUrl().startsWith("https://qa.herschel.ca")) {
            for (WebElement option : ShippingRegion.findElements(By.tagName("option"))) {
                if (option.getText().equals(region)) {
                    option.click();
                }
            }
        }
        ShippingPostalCode.sendKeys(mailingCode);
        for (WebElement option : ShippingCountry.findElements(By.tagName("option"))) {
            if (option.getText().equals(shippingCountry)) {
                option.click();
            }
        }
        ShippingPhoneNumber.sendKeys(phoneNumber);
        AddAddressButton.click();
        //If click failed (fairly common occurrence in checkout), attempt to click again
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        if (driver.findElements(By.cssSelector("button[id='hsco-add-address']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-add-address']")).get(0).isDisplayed()) {
                AddAddressButton.click();
            }
        }
        //Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
    }

    /**
     * Enter a new Credit Card.
     *
     * @param cardName value to enter in credit card field
     * @param cardNumber value to enter in credit card field
     * @param expMonth value to enter in credit card field
     * @param expYear value to enter in credit card field
     * @param cvc value to enter in credit card field
     */
    public void addCreditCard(String cardName, String cardNumber, String expMonth, String expYear, String cvc) {
        wait.until(ExpectedConditions.elementToBeClickable(AddPaymentButton));
        CardName.sendKeys(cardName);
        CardNumber.sendKeys(cardNumber);
        CardExpMonth.sendKeys(expMonth);
        CardExpYear.sendKeys(expYear);
        CardCVC.sendKeys(cvc);
        AddPaymentButton.click();
        //If click failed (fairly common occurrence in checkout), attempt to click again
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        if (driver.findElements(By.cssSelector("button[id='hsco-add-payment']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-add-payment']")).get(0).isDisplayed()) {
                AddPaymentButton.click();
            }
        }
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
    }

	/**
	 * Gets the first product name displayed in the Cart.
	 *
	 * @return String containing the first product name displayed in the Cart
	 */
	public String getCartProduct() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		return driver.findElement(By.cssSelector("p[class='m-y-0 text-cta bfx-product-name']")).getText();
	}

	/**
	 * Gets the first SKU name displayed in the Cart.
	 *
	 * @return String containing the first SKU name displayed in the Cart
	 */
	public String getCartSku() {
		return driver.findElement(By.cssSelector("p[class='m-y-0 bfx-product-color']")).getText();
	}
}