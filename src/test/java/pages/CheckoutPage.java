package pages;

import base.Page;
import org.openqa.selenium.*;
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
    @FindBy(css = "input[name='hsco-giftcard']")
    private WebElement GiftCard;
    @FindBy(css = "button.hsco-apply-giftcard-button")
    private WebElement ApplyGiftCard;
    @FindBy(css = "span.hsco-order-gc")
    private WebElement GiftCardApplied;
    @FindBy(css = "p.hsco-add-coupon")
    private WebElement AddDiscount;
    @FindBy(css = "input[name='hsco-coupon']")
    private WebElement CouponCode;
    @FindBy(css = "button.button.hsco-apply-giftcard-button.discount__button")
    private WebElement ApplyCoupon;
    @FindBy(css = "span.hsco-order-discount")
    private WebElement CouponApplied;
    @FindBy(css = "span.hsco-order-tax-amount")
    private WebElement Tax;
    @FindBy(css = "span.hsco-order-shipping")
    private WebElement ShippingPrice;

    /**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public CheckoutPage(final WebDriver driver) {
		super(driver);
        wait = new WebDriverWait(driver, 15);
	}
	
	/**
	 * Load the Herschel Checkout Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
        String prefix = "";
        String baseURL;
        if(getPropertyManager().getProperty("testEnv").equals("prod")) {
            prefix = "prod.";
        }
		if(country.equals("US")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.US");
		}
		else if(country.equals("UK")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.UK");
		}
		else if(country.equals("EU")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.EU");
		}
		else {
            baseURL = getPropertyManager().getProperty(prefix+"url.CA");
		}
        driver.get(baseURL+"/shop/checkout");
		//wait for page loading to complete
        waitForLoadingOverlayToDisappear();
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
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(ShippingAddressNext));
	}

	/**
	 * Selects the default shipping address.
	 */
	public void selectDefaultShippingAddress() {
		//Need to wait for the loading overlay to disappear
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(ShippingAddresses));
		ShippingAddresses.click();
        waitForLoadingOverlayToDisappear();
		for (WebElement option : ShippingAddresses.findElements(By.tagName("option"))) {
			if (!option.getText().equals("Select an address")) {
				option.click();
			}
		}
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(ShippingAddressNext));
		ShippingAddressNext.click();
		//If click failed (fairly common occurrence in checkout), attempt to click again
        waitForLoadingOverlayToDisappear();
		if (driver.findElements(By.cssSelector("button[id='hsco-section2-next']")).size() > 0 ) {
		    if (driver.findElements(By.cssSelector("button[id='hsco-section2-next']")).get(0).isDisplayed()) {
                try {
                    ShippingAddressNext.click();
                } catch (ElementNotVisibleException e) {
                    System.out.print("Shipping Next Button did not disappear immediately after click");
                }
            }
		}
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
	}

	/**
	 * Selects the default shipping method.
	 */
	public void selectDefaultShippingMethod() {
		//Need to wait for the loading overlay to disappear
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
        //Loading Overlay can appear twice.  Adding logic to prevent false failures
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
        try {
            ShippingMethods.click();
        } catch (WebDriverException e) {
            System.out.print("Shipping Method Dropdown was reported to be unclickable");
        }
        waitForLoadingOverlayToDisappear();
		for (WebElement option : ShippingMethods.findElements(By.tagName("option"))) {
			if (option.getText().equals("Standard Shipping - FREE")) {
				option.click();
			}
		}
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
		ShippingMethodNext.click();
		//Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
	}

	/**
	 * Selects the default payment methods.
	 */
	public void selectDefaultPaymentMethod() {
		//Need to wait for the loading overlay to disappear
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
        //Loading Overlay can appear twice.  Adding logic to prevent false failures
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
        try {
            PaymentMethods.click();
        } catch (WebDriverException e) {
            System.out.print("Payment Method Dropdown was reported to be unclickable");
        }
        waitForLoadingOverlayToDisappear();
		for (WebElement option : PaymentMethods.findElements(By.tagName("option"))) {
			if (option.getText().equals("Visa ending in *4242")) {
				option.click();
			}
		}
		//Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
		PaymentNext.click();
		//If click failed (fairly common occurrence in checkout), attempt to click again
        waitForLoadingOverlayToDisappear();
        if (driver.findElements(By.cssSelector("button[id='hsco-section4-next']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-section4-next']")).get(0).isDisplayed()) {
                try {
                    PaymentNext.click();
                } catch (ElementNotVisibleException e) {
                    System.out.print("Payment Next Button did not disappear immediately after click");
                }
            }
        }
		//Need to wait for the loading overlay to disappear and the Place Order button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
	}

    /**
     * Completes checkout by clicking on the Place Order button.
     */
    public void placeOrder() {
    	//Need to wait for the loading overlay to disappear and the Place Order button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
        } catch (TimeoutException e) {
            System.out.print("Place Order Button was not clickable for over 15 seconds");
        }
        try {
            PlaceOrder.click();
        } catch (WebDriverException e) {
            System.out.print("Place Order Button was reported to be unclickable");
        }
		//If click failed (fairly common occurrence in checkout), attempt to click again
        waitForLoadingOverlayToDisappear();
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
        waitForLoadingOverlayToDisappear();
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
        //On smaller screens, the Add Address Button appears below the fold, making it unclickable.
        //Scrolling down a little makes the button appear on screen and be clickable.
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", ShippingCity);
        AddAddressButton.click();
        //If click failed (fairly common occurrence in checkout), attempt to click again
        waitForLoadingOverlayToDisappear();
        if (driver.findElements(By.cssSelector("button[id='hsco-add-address']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-add-address']")).get(0).isDisplayed()) {
                AddAddressButton.click();
            }
        }
        //Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
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
        waitForLoadingOverlayToDisappear();
        if (driver.findElements(By.cssSelector("button[id='hsco-add-payment']")).size() > 0 ) {
            if (driver.findElements(By.cssSelector("button[id='hsco-add-payment']")).get(0).isDisplayed()) {
                AddPaymentButton.click();
            }
        }
		//Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
        } catch (TimeoutException e) {
            System.out.print("Place Order Button was not clickable for over 15 seconds");
        }
    }

    /**
     * Selects the specified shipping method.
     *
     * @param shippingMethod option in the shipping method dropdown to select
     */
    public void selectShippingMethod(String shippingMethod) {
        //Need to wait for the loading overlay to disappear
        waitForLoadingOverlayToDisappear();
        ShippingMethods.click();
        waitForLoadingOverlayToDisappear();
        for (WebElement option : ShippingMethods.findElements(By.tagName("option"))) {
            if (option.getText().equals(shippingMethod)) {
                option.click();
            }
        }
        //Need to wait for the loading overlay to disappear and the Shipping Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(ShippingMethodNext));
        ShippingMethodNext.click();
        //Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
    }

	/**
	 * Gets the first product name displayed in the Cart.
	 *
	 * @return String containing the first product name displayed in the Cart
	 */
	public String getCartProduct() {
        waitForLoadingOverlayToDisappear();
        String product = "no cart product found";
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
        	try {
				product = driver.findElement(By.cssSelector("p[class='m-y-0 text-cta bfx-product-name']")).getText();
                result = true;
				break;
			} catch(StaleElementReferenceException e) {
        		System.out.print("Stale Element Exception occurred on Cart Product");
			}
			attempts++;
		}
		return product;
	}

	/**
	 * Gets the first SKU name displayed in the Cart.
	 *
	 * @return String containing the first SKU name displayed in the Cart
	 */
	public String getCartSku() {
		String sku = "no cart sku found";
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
                sku = driver.findElement(By.cssSelector("p[class='m-y-0 bfx-product-color']")).getText();
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				System.out.print("Stale Element Exception occurred on Cart SKU");
			}
			attempts++;
		}
		return sku;
	}

    /**
     * Apply a Gift Card to the order.
     *
     * @param giftCard gift card code to be applied to the order
     */
    public void applyGiftCard(String giftCard) {
        wait.until(ExpectedConditions.elementToBeClickable(GiftCard));
        GiftCard.sendKeys(giftCard);
        ApplyGiftCard.click();
        //Need to wait for the loading overlay to disappear and Gift Card to appear before proceeding
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.visibilityOf(GiftCardApplied));
    }

    /**
     * Checks if the Gift Card was applied to the order.
     *
     * @return boolean if the Gift Card is Applied
     */
    public boolean isGiftCardApplied() {
        String orderText = driver.findElement(By.cssSelector("div.h-order-subtotals")).getText();
        if(GiftCardApplied.isDisplayed() && orderText.contains("Gift Card") ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Selects the gift card as the payment method.
     */
    public void selectGiftCardPaymentMethod() {
        //Need to wait for the loading overlay to disappear
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
        PaymentMethods.click();
        waitForLoadingOverlayToDisappear();
        for (WebElement option : PaymentMethods.findElements(By.tagName("option"))) {
            if (option.getText().equals("Gift Card")) {
                option.click();
            }
        }
        //Need to wait for the loading overlay to disappear and the Payment Method Next button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(PaymentNext));
        PaymentNext.click();
        //Need to wait for the loading overlay to disappear and the Place Order button to be clickable before proceeding
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(PlaceOrder));
    }

    /**
     * Apply a discount to the order.
     *
     * @param couponCode to be applied to the order
     */
    public void applyDiscount(String couponCode) {
        wait.until(ExpectedConditions.elementToBeClickable(AddDiscount));
        driver.findElement(By.linkText("Add a discount")).click();
        wait.until(ExpectedConditions.elementToBeClickable(CouponCode));
        CouponCode.sendKeys(couponCode);
        ApplyCoupon.click();
        //Need to wait for the loading overlay to disappear and Discount to appear before proceeding
        waitForLoadingOverlayToDisappear();
        wait.until(ExpectedConditions.visibilityOf(CouponApplied));
    }

    /**
     * Apply a shipping discount to the order.
     *
     * @param couponCode to be applied to the order
     */
    public void applyShippingDiscount(String couponCode) {
        //wait.until(ExpectedConditions.elementToBeClickable(AddDiscount));
        driver.findElement(By.linkText("Add a discount")).click();
        wait.until(ExpectedConditions.elementToBeClickable(CouponCode));
        CouponCode.sendKeys(couponCode);
        ApplyCoupon.click();
        //Need to wait for the loading overlay to disappear and Discount to appear before proceeding
        waitForLoadingOverlayToDisappear();
    }

    /**
     * Checks if the Coupon was applied to the order.
     *
     * @return boolean true if discount is applied
     */
    public boolean isDiscountApplied() {
        String orderText = driver.findElement(By.cssSelector("div.h-order-subtotals")).getText();
        if(CouponApplied.isDisplayed() && orderText.contains("Discount") ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the Shipping Price.
     *
     * @return String shipping price
     */
    public String getShippingPrice() {
        return ShippingPrice.getText();
    }

    /**
     * Gets the Tax Amount.
     *
     * @return String tax amount
     */
    public String getTax() {
        return Tax.getText();
    }

    /**
     * Waits for the loading overlay to disappear.
     */
    public void  waitForLoadingOverlayToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loadingoverlay")));
    }

}