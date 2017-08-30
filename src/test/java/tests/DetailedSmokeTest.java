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
    @FindBy(css = "input[type='email']")
    private WebElement EmailField;
    @FindBy(css = "input[type='password']")
    private WebElement PasswordField;
    @FindBy(css = "span[class='RveJvd snByac']")
    private WebElement NextButton;

/*
    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private AccountPage accountPage;
*/
    /**
     * Initialises the pages needed for the test.
     */
/*    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        accountPage = new AccountPage(getDriver());
    }
*/
    /**
     * Sets the region cookie on the browser (so the region pop-up doesn't appear).
     *
     * @param country Herschel country site to load
     */
/*    @Test
    @Parameters("country")
    public void setCookie(String country) {
        homePage.setCookie(country);
    }
*/
	/**
     * Loads the Little America Backpack for the specified country site.
     *
     */
    @Test
    @Parameters({"email", "password"})
    public void gmailSignIn(String email, String password) {
        getDriver().get("http://gmail.com");
        getDriver().findElement(By.cssSelector("input[type='email']")).sendKeys(email);
        getDriver().findElement(By.cssSelector("span[class='RveJvd snByac']")).click();

        wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']"))); //elementToBeClickable(PasswordField));

        getDriver().findElement(By.cssSelector("input[type='password']")).sendKeys(password);
        getDriver().findElement(By.cssSelector("span[class='RveJvd snByac']")).click();
        //EmailField.sendKeys(email);
        //NextButton.click();
        //PasswordField.sendKeys(password);
        //NextButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='T-I J-J5-Ji T-I-KE L3']")));
    }

    /**
     * Loads the Little America Backpack for the specified country site.
     *
     */
    @Test
    @Parameters({"title", "orderNumber"})
    public void gmailEmailVerification() {
        //(String title, String orderNumber) {
        String actualTitle = getDriver().findElements(By.cssSelector("span[id=':2j']")).get(0).getText();

        System.out.print(actualTitle);
        String actualSubTitle = getDriver().findElements(By.cssSelector("span[class='y2']")).get(0).getText();
        System.out.print(actualSubTitle);

    }

}
