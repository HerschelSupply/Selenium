package tests;

import base.TestBase;
import pages.HomePage;
import pages.ProductPage;
import pages.CheckoutPage;
import pages.AccountPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Smoke Test for Herschel sites
 * 
 * @author tmorris
 */
public class HerschelSmokeTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private AccountPage accountPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        accountPage = new AccountPage(getDriver());
    }

    /**
     * Sets the region cookie on the broswer (so the region pop-up doesn't appear).
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
}
