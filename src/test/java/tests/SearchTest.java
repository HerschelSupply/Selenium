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
     * Verifies that the specified product is displayed in the Search Results.
     *
     * @param productTitle used to check if this product is displayed in the Search Results
     */
    @Test
    @Parameters("productTitle")
    public void verifySearchResults(String productTitle) {
        Assert.assertTrue(homePage.isSearchResultDisplayed(productTitle), String.format(
                "The expected product was not displayed in the search results. Expected Product: '%s'", productTitle));
    }
}
