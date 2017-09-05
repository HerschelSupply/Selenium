package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

/**
 * Smoke Test for Herschel sites
 * 
 * @author tmorris
 */
public class GuestCheckoutTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        confirmationPage = new ConfirmationPage(getDriver());
    }

    /**
     * Sets the region cookie on the browser (so the region pop-up doesn't appear).
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void setCookie(String country) {
        homePage.setCookie(country);
    }
	
	/**
     * Loads the Little America Backpack for the specified country site.
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void loadLittleAmericaBackpack(String country) {
        productPage.load(country);
    }

    /**
     * Adds the product to cart.
     */
    @Test
    public void addToCart() {
        productPage.addToCart();
    }

    /**
     * Loads the Checkout Page for the specified country site.
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void goToCheckout(String country) {
        checkoutPage.load(country);
    }

    /**
     * Selects the default Shipping Address and proceeds to Shipping Method selection.
     * @param email String value to enter in email field
     */
    @Test
    @Parameters("email")
    public void checkoutAsGuest(String email) {
        checkoutPage.checkoutAsGuest(email);
    }

    /**
     * Enters a new Shipping Address and proceeds to the Shipping Method selection.
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
    @Test
    @Parameters({"givenName", "familyName", "streetAddress", "extendedAddress", "city", "region", "mailingCode", "shippingCountry", "phoneNumber"})
    public void addShippingAddress(String givenName, String familyName, String streetAddress, String extendedAddress, String city, String region, String mailingCode, String shippingCountry, String phoneNumber) {
        checkoutPage.addShippingAddress(givenName, familyName, streetAddress, extendedAddress, city, region, mailingCode, shippingCountry, phoneNumber);
    }

    /**
     * Selects the default Shipping Method and proceeds to Payment Method selection.
     */
    @Test
    public void selectDefaultShippingMethod() {
        checkoutPage.selectDefaultShippingMethod();
    }

    /**
     * Selects the default Payment Method and proceeds to Order Placement.
     */
    @Test
    public void selectDefaultPaymentMethod() {
        checkoutPage.selectDefaultPaymentMethod();
    }

    /**
     * Enter a new Credit Card and proceed to the Order Review section.
     *
     * @param cardName value to enter in credit card field
     * @param cardNumber value to enter in credit card field
     * @param expMonth value to enter in credit card field
     * @param expYear value to enter in credit card field
     * @param cvc value to enter in credit card fie
     */
    @Test
    @Parameters({"cardName", "cardNumber", "expMonth", "expYear", "cvc"})
    public void addCreditCard(String cardName, String cardNumber, String expMonth, String expYear, String cvc) {
        checkoutPage.addCreditCard(cardName, cardNumber, expMonth, expYear, cvc);
    }

    /**
     * Completes checkout by clicking on the Place Order button.
     */
    @Test
    public void placeOrder() {
        checkoutPage.placeOrder();
    }

    /**
     * Verifies the Title of the Order Confirmation Page.
     *
     * @param expectedConfirmationTitle Order Confirmation Title
     */
    @Test
    @Parameters("confirmationTitle")
    public void verifyOrderConfirmationTitle(String expectedConfirmationTitle) {
        String actualConfirmationTitle = confirmationPage.getConfirmationTitle();
        Assert.assertEquals(actualConfirmationTitle, expectedConfirmationTitle, String.format(
                "The actual Order Confirmation Title did not match the expected.  Actual: '%s'. Expected: '%s'", actualConfirmationTitle, expectedConfirmationTitle));

    }

    /**
     * Verifies the Order Confirmation Message on the Order Confirmation Page.
     *
     * @param expectedConfirmationMessage Order Confirmation Message
     */
    @Test
    @Parameters("confirmationMessage")
    public void verifyOrderConfirmationMessage(String expectedConfirmationMessage) {
        String actualConfirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage, String.format(
                "The actual Order Confirmation Message did not match the expected.  Actual: '%s'. Expected: '%s'", actualConfirmationMessage, expectedConfirmationMessage));
    }
}