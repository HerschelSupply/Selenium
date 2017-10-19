package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import java.net.MalformedURLException;

/**
 * Test for browsing the Herschel sites
 * 
 * @author tmorris
 */
public class BrowseTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() throws MalformedURLException {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
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
     * Closes the skinny banner.
     *
     */
    @Test
    public void closeBanner() {
        homePage.closeBanner();
    }

	/**
     * Navigates to the Backpacks section.
     *
     */
    @Test
    public void navigateToBackpacks() {
        homePage.navigateToBackpacks();
    }

    /**
     * Clicks on the specified product tile.
     *
     * @param productName name of product to select
     */
    @Test
    @Parameters("productName")
    public void selectProductTile(String productName) {
        homePage.selectProductTile(productName);
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
     * Verifies the product name on the product page.
     *
     * @param expectedProductName verify this productName appears on the product page
     */
    @Test
    @Parameters("productName")
    public void verifyProductName(String expectedProductName) {
        Assert.assertTrue(productPage.getProductName().equalsIgnoreCase(expectedProductName), String.format(
                "The actual product name displayed on the Product Page did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getProductName(), expectedProductName));
    }

    /**
     * Verifies the selected SKU color on the product page.
     *
     * @param expectedSkuName verify this skuName appears in the Toaster
     */
    @Test
    @Parameters("skuName")
    public void verifySkuColor(String expectedSkuName) {
        Assert.assertEquals(productPage.getSkuName(), expectedSkuName, String.format(
                "The actual SKU name displayed on the Product Page did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getSkuName(), expectedSkuName));
    }

    /**
     * Navigates to the Backpacks section on a mobile device.
     *
     */
    @Test
    public void navigateToBackpacksMobile() {
        homePage.navigateToBackpacksMobile();
    }

    /**
     * Verifies the product name on the product page on a mobile device.
     *
     * @param expectedProductName verify this productName appears on the product page
     */
    @Test
    @Parameters("productName")
    public void verifyProductNameMobile(String expectedProductName) {
        Assert.assertTrue(productPage.getProductNameMobile().equalsIgnoreCase(expectedProductName), String.format(
                "The actual product name displayed on the Product Page did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getProductName(), expectedProductName));
    }

    /**
     * Verifies the selected SKU color on the product page on a mobile device.
     *
     * @param expectedSkuName verify this skuName appears in the Toaster
     */
    @Test
    @Parameters("skuName")
    public void verifySkuColorMobile(String expectedSkuName) {
        Assert.assertEquals(productPage.getSkuNameMobile(), expectedSkuName, String.format(
                "The actual SKU name displayed on the Product Page did not match the expected.  Actual: '%s'. Expected: '%s'", productPage.getSkuName(), expectedSkuName));
    }
}
