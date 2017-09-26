package tests;

import base.TestBase;
import org.testng.Assert;
import pages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;

/**
 * Email Order Confirmation Test for Herschel sites
 * 
 * @author tmorris
 */
public class OrderConfirmationTest extends TestBase {

    @FindBy(css = "input[type='email']")
    private WebElement EmailField;
    @FindBy(css = "input[type='password']")
    private WebElement PasswordField;
    @FindBy(css = "span[class='RveJvd snByac']")
    private WebElement NextButton;

    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private AccountPage accountPage;
    private ConfirmationPage confirmationPage;
    private GMailPage gmailPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() throws MalformedURLException {
        homePage = new HomePage(getDriver());
        productPage = new ProductPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        accountPage = new AccountPage(getDriver());
        confirmationPage = new ConfirmationPage(getDriver());
        gmailPage = new GMailPage(getDriver());
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
     * Sign in to GMail using the credentials provided.
     *
     */
    @Test
    @Parameters({"email", "gmailPassword"})
    public void gmailSignIn(String email, String password) {
        gmailPage.load();
        gmailPage.gmailSignIn(email, password);
    }

    /**
     * Verifies the Sender's Name and Email Address for the most recent email in the user's inbox.
     *
     * @param expectedSenderName name of the email sender
     * @param expectedSenderEmail email address of the email sender
     */
    @Test
    @Parameters({"senderName", "senderEmail"})
    public void verifyEmailSender(String expectedSenderName, String expectedSenderEmail) {
        String actualSenderName = gmailPage.getSendersName();
        Assert.assertEquals(actualSenderName, expectedSenderName, String.format(
                "The actual Sender's Name did not match the expected.  Actual: '%s'. Expected: '%s'", actualSenderName, expectedSenderName));
        String actualSenderEmail = gmailPage.getSendersEmail();
        Assert.assertEquals(actualSenderEmail, expectedSenderEmail, String.format(
                "The actual Sender's Email did not match the expected.  Actual: '%s'. Expected: '%s'", actualSenderEmail, expectedSenderEmail));
    }

    /**
     * Verifies the Title of the most recent email in the user's inbox.
     *
     * @param expectedEmailTitle email title
     */
    @Test
    @Parameters("emailTitle")
    public void verifyEmailTitle(String expectedEmailTitle) {
        String actualEmailTitle = gmailPage.getEmailTitle();
        Assert.assertEquals(actualEmailTitle, expectedEmailTitle, String.format(
                "The actual Email Title did not match the expected.  Actual: '%s'. Expected: '%s'", actualEmailTitle, expectedEmailTitle));
    }

    /**
     * Verifies the SubTitle of the most recent email in the user's inbox.
     *
     * @param expectedEmailSubTitle email subtitle
     */
    @Test
    @Parameters("emailSubTitle")
    public void verifyEmailSubTitle(String expectedEmailSubTitle) {
        String actualEmailSubTitle = gmailPage.getEmailSubTitle();
        Assert.assertEquals(actualEmailSubTitle, expectedEmailSubTitle, String.format(
                "The actual Email SubTitle did not match the expected.  Actual: '%s'. Expected: '%s'", actualEmailSubTitle, expectedEmailSubTitle));
    }
}
