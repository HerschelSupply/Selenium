package tests;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;

/**
 * Test for adding products to the cart
 *
 * @author tmorris
 */
public class AddToCartTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
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
     * Clicks on the specified sku option.
     *
     * @param skuName name of product to select
     */
    @Test
    @Parameters("skuName")
    public void selectSKU(String skuName) {
        productPage.selectSKU(skuName);
    }

    /**
     * Adds the product to cart.
     */
    @Test
    public void addToCart() {
        productPage.addToCart();
    }

    /**
     * Verifies the product name and SKU name displayed in the Toaster.
     *
     * @param expectedProductName verify this productName appears in the Toaster
     * @param expectedSkuName verify this skuName appears in the Toaster
     */
    @Test
    @Parameters({"productName", "skuName"})
    public void verifyToaster(String expectedProductName, String expectedSkuName) {
        Assert.assertEquals(productPage.getToasterProduct(), expectedProductName, String.format(
                "The actual product name displayed in the Toaster did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getToasterProduct(), expectedProductName));
        Assert.assertEquals(productPage.getToasterSku(), expectedSkuName, String.format(
                "The actual SKU name displayed in the Toaster did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getToasterSku(), expectedSkuName));
    }

    /**
     * Opens the side drawer.
     */
    @Test
    public void openDrawer() {
        productPage.openDrawer();
    }

    /**
     * Verifies the first product name and SKU name displayed in the Drawer.
     *
     * @param expectedProductName verify this productName appears in the Drawer
     * @param expectedSkuName verify this skuName appears in the Drawer
     */
    @Test
    @Parameters({"productName", "drawerSkuName"})
    public void verifyDrawer(String expectedProductName, String expectedSkuName) {
        Assert.assertEquals(productPage.getDrawerProduct(), expectedProductName, String.format(
                "The actual product name displayed in the Drawer did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getDrawerProduct(), expectedProductName));
        Assert.assertEquals(productPage.getDrawerSku(), expectedSkuName, String.format(
                "The actual SKU name displayed in the Drawer did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getDrawerSku(), expectedSkuName));
    }

    /**
     * Clicks on the Checkout Link in the Drawer.
     */
    @Test
    public void clickDrawerCheckoutLink() {
        productPage.clickDrawerCheckoutLink();
    }

    /**
     * Verifies the first product name and SKU name displayed in the Cart.
     *
     * @param expectedProductName verify this productName appears in the Cart
     * @param expectedSkuName verify this skuName appears in the Cart
     */
    @Test
    @Parameters({"productName", "drawerSkuName"})
    public void verifyCart(String expectedProductName, String expectedSkuName) {
        Assert.assertEquals(checkoutPage.getCartProduct(), expectedProductName, String.format(
                "The actual product name displayed in the Cart did not match the expected.  Actual: '%s'. Expected: '%s'", checkoutPage.getCartProduct(), expectedProductName));
        Assert.assertEquals(checkoutPage.getCartSku(), expectedSkuName, String.format(
                "The actual SKU name displayed in the Cart did not match the expected.  Actual: '%s'. Expected: '%s'", checkoutPage.getCartSku(), expectedSkuName));
    }

}
