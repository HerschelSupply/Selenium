package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

/**
 * Test for browsing the Herschel sites
 * 
 * @author tmorris
 */
public class SearchTest extends TestBase {

    private HomePage homePage;
    private ProductPage productPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
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
    }

	/**
     * Clicks on the Search Icon to open the Search Box.
     *
     */
    @Test
    public void openSearch() {
        homePage.openSearch();
    }

    /**
     * Enters the specified word(s) in to the Search Box.
     *
     * @param searchTerm term to be entered in to the Search Box
     */
    @Test
    @Parameters("searchTerm")
    public void inputSearchTerm(String searchTerm) {
        homePage.inputSearchTerm(searchTerm);
    }

    /**
     * Submits the search request.
     *
     */
    @Test
    public void performSearch() {
        homePage.performSearch();
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
}
