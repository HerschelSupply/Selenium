package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.net.MalformedURLException;

/**
 * Test for purchasing Gift Cards
 * 
 * @author tmorris
 */
public class GiftCardTest extends TestBase {

    private HomePage homePage;
    private GiftCardPage giftCardPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() throws MalformedURLException {
        homePage = new HomePage(getDriver());
        giftCardPage = new GiftCardPage(getDriver());
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
        homePage.setCookie(country);
    }

	/**
     * Loads the Gift Card Page for the specified country site.
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void loadGiftCard(String country) {
        giftCardPage.load(country);
    }

    /**
     * Selects the specified Gift Card amount.
     *
     * @param amount Gift Card amount
     */
    @Test
    @Parameters("amount")
    public void selectAmount(String amount) {
        giftCardPage.selectAmount(amount);
    }

    /**
     * Enters the GC Recipient Information.
     *
     * @param recipientFirstName value to enter in to the Recipient First Name field
     * @param recipientLastName value to enter in to the Recipient Last Name field
     * @param recipientEmail value to enter in to the Recipient Email field
     */
    @Test
    @Parameters({"recipientFirstName", "recipientLastName", "recipientEmail"})
    public void enterRecipientInfo(String recipientFirstName, String recipientLastName, String recipientEmail) {
        giftCardPage.enterRecipientInfo(recipientFirstName, recipientLastName, recipientEmail);
    }

    /**
     * Enters the GC Sender Information.
     *
     * @param senderFirstName value to enter in to the Sender First Name field
     * @param senderLastName value to enter in to the Sender Last Name field
     * @param senderEmail value to enter in to the Sender Email field
     */
    @Test
    @Parameters({"senderFirstName", "senderLastName", "senderEmail"})
    public void enterSenderInfo(String senderFirstName, String senderLastName, String senderEmail) {
        giftCardPage.enterSenderInfo(senderFirstName, senderLastName, senderEmail);
    }

    /**
     * Enters the GC Message.
     *
     * @param message Gift Card Message
     */
    @Test
    @Parameters("message")
    public void enterMessage(String message) {
        giftCardPage.enterMessage(message);
    }

    /**
     * Adds the gift card to cart.
     */
    @Test
    public void addToCart() {
        giftCardPage.addToCart();
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
     * Proceeds with the Checkout as Guest option.
     * @param email String value to enter in email field
     */
    @Test
    @Parameters("senderEmail")
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
    @Parameters({"recipientFirstName", "recipientLastName", "streetAddress", "extendedAddress", "city", "region", "mailingCode", "shippingCountry", "phoneNumber"})
    public void addShippingAddress(String givenName, String familyName, String streetAddress, String extendedAddress, String city, String region, String mailingCode, String shippingCountry, String phoneNumber) {
        checkoutPage.addShippingAddress(givenName, familyName, streetAddress, extendedAddress, city, region, mailingCode, shippingCountry, phoneNumber);
    }

    /**
     * Selects the specified Shipping Method and proceeds to Payment Method selection.
     *
     * @param shippingMethod option in the shipping method dropdown to select
     */
    @Test
    @Parameters("shippingMethod")
    public void selectShippingMethod(String shippingMethod) {
        checkoutPage.selectShippingMethod(shippingMethod);
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