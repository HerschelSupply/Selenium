package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GiftCardPage;
import pages.HomePage;
import pages.ProductPage;

/**
 * Test for purchasing Gift Cards
 * 
 * @author tmorris
 */
public class GiftCardTest extends TestBase {

    private HomePage homePage;
    private GiftCardPage giftCardPage;

    /**
     * Initialises the pages needed for the test.
     */
    @BeforeTest
    public void setUp() {
        homePage = new HomePage(getDriver());
        giftCardPage = new GiftCardPage(getDriver());
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
     * Loads the Gift Card Page for the specified country site.
     *
     * @param country Herschel country site to load
     */
    @Test
    @Parameters("country")
    public void loadGiftCard(String country) {
        giftCardPage.load(country);
    }

    /**
     * Selects the specified Gift Card amount.
     *
     * @param amount Gift Card amount
     */
    @Test
    @Parameters("amount")
    public void selectAmount(String amount) {
        giftCardPage.selectAmount(amount);
    }

    /**
     * Enters the GC Recipient Information.
     *
     * @param recipientFirstName value to enter in to the Recipient First Name field
     * @param recipientLastName value to enter in to the Recipient Last Name field
     * @param recipientEmail value to enter in to the Recipient Email field
     */
    @Test
    @Parameters({"recipientFirstName", "recipientLastName", "recipientEmail"})
    public void enterRecipientInfo(String recipientFirstName, String recipientLastName, String recipientEmail) {
        giftCardPage.enterRecipientInfo(recipientFirstName, recipientLastName, recipientEmail);
    }

    /**
     * Enters the GC Sender Information.
     *
     * @param senderFirstName value to enter in to the Sender First Name field
     * @param senderLastName value to enter in to the Sender Last Name field
     * @param senderEmail value to enter in to the Sender Email field
     */
    @Test
    @Parameters({"senderFirstName", "senderLastName", "senderEmail"})
    public void enterSenderInfo(String senderFirstName, String senderLastName, String senderEmail) {
        giftCardPage.enterSenderInfo(senderFirstName, senderLastName, senderEmail);
    }

    /**
     * Enters the GC Message.
     *
     * @param message Gift Card Message
     */
    @Test
    @Parameters("message")
    public void enterMessage(String message) {
        giftCardPage.enterMessage(message);
    }

    /**
     * Adds the gift card to cart.
     */
    @Test
    public void addToCart() {
        giftCardPage.addToCart();
    }
}
