package tests;

import base.TestBase;
import org.testng.Assert;
import pages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test to checkout as a registered guest on all Herschel sites
 * 
 * @author tmorris
 */
public class RegisteredCheckoutTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private AccountPage accountPage;
    private ConfirmationPage confirmationPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        accountPage = new AccountPage(getDriver());
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
     * Signs in at the Checkout Page for the specified country site.
     *
     * @param email email address used to sign in
     * @param password password used to sign in
     */
    @Test
    @Parameters({"email", "password"})
    public void signInAtCheckout(String email, String password) {
        checkoutPage.signIn(email, password);
    }

    /**
     * Loads the Account Page for the specified country
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void loadAccountPage(String country) {
        accountPage.load(country);
    }

    /**
     * Signs in from the Account Page
     *
     * @param email email address used to sign in
     * @param password password used to sign in
     */
    @Test
    @Parameters({"email", "password"})
    public void signInToAccount(String email, String password) {
        accountPage.signIn(email, password);
    }

    /**
     * Selects the default Shipping Address and proceeds to Shipping Method selection.
     */
    @Test
    public void selectDefaultShippingAddress() {
        checkoutPage.selectDefaultShippingAddress();
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
     * Completes checkout by clicking on the Place Order button.
     */
    @Test
    public void placeOrder() {
        checkoutPage.placeOrder();
    }

    /**
     * Verifies the Tax Amount is not $0.00.  Only applicable to US site
     */
    @Test
    public void validateTax() {
        String actualTax = checkoutPage.getTax();
        Assert.assertFalse(actualTax.equals("$0.00"),"Tax amount displayed was $0.00 but expected taxes to be applied");
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
