package tests;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;

/**
 * Smoke Test for Herschel sites
 * 
 * @author tmorris
 */
public class DetailedSmokeTest extends TestBase {

    private Wait<WebDriver> wait;
    @FindBy(css = "a[data-model='shop']")
    private WebElement ShopNav;
    @FindBy(css = "a[href='/shop/mens/backpacks']")
    private WebElement BackpacksLink;
    @FindBy(css = "img[alt='Little America Backpack']")
    private WebElement LittleAmericaBackpack;


    private HomePage homePage;
/*
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private AccountPage accountPage;
*/
    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        //productPage = new ProductPage(getDriver());
        //checkoutPage = new CheckoutPage(getDriver());
        //accountPage = new AccountPage(getDriver());
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
     * Navigates to the Backpacks section.
     *
     */
    @Test
    public void navigateToBackpacks() {
        homePage.load("CA");
        ShopNav.click();
        BackpacksLink.click();
    }

    /**
     * Clicks on the specified product.
     *
     * @param productName name of product to select
     */
    @Test
    @Parameters("productName")
    public void selectProduct(String productName) {
        LittleAmericaBackpack.click();
    }

}
