package tests;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

import java.net.MalformedURLException;

/**
 * Test for verifying component rendering
 * 
 * @author tmorris
 */
public class ComponentRenderingTest extends TestBase {

    private Wait<WebDriver> wait;
    private HomePage homePage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() throws MalformedURLException {
        homePage = new HomePage(getDriver());
        wait = new WebDriverWait(getDriver(), 10);
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
     * Loads the Component Rendering Page.
     *
     */
    @Test
    public void loadComponentPage() throws MalformedURLException {
        getDriver().get("https://qa.herschel.com/component-test");
    }

    /**
     * Verify the Hero Video loads correctly.
     */
    @Test
    public void verifyHeroVideo() throws MalformedURLException {
        Assert.assertTrue(getDriver().findElements(By.cssSelector("video[class='hero-video__video loaded']")).size() > 0,
                "The Hero Video component did not render on the Component Rendering Page; https://qa.herschel.com/component-test");
    }
}
