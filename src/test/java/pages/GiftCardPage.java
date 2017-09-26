package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles interactions with the Gift Card Product Page.
 * 
 * @author tmorris
 * 
 */
public class GiftCardPage extends Page<GiftCardPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "button.button--primary")
    private WebElement AddToCart;
    @FindBy(css = "select[id='giftCardValue']")
    private WebElement Amount;
    @FindBy(css = "input[id='giftCertificate_recipientFirstName']")
    private WebElement RecipientFirstName;
    @FindBy(css = "input[id='giftCertificate_recipientLastName']")
    private WebElement RecipientLastName;
    @FindBy(css = "input[id='giftCertificate_recipientEmail']")
    private WebElement RecipientEmail;
    @FindBy(css = "input[id='giftCertificate_senderFirstName']")
    private WebElement SenderFirstName;
    @FindBy(css = "input[id='giftCertificate_senderLastName']")
    private WebElement SenderLastName;
    @FindBy(css = "input[id='giftCertificate_senderEmail']")
    private WebElement SenderEmail;
    @FindBy(css = "textarea[id='giftCertificate_message']")
    private WebElement Message;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public GiftCardPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 12);
	}
	
	/**
	 * Load the Herschel Gift Card Product Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
        String prefix = "";
        String baseURL;
        if(getPropertyManager().getProperty("testEnv").equals("prod")) {
            prefix = "prod.";
        }
		if(country.equals("US")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.US");
		}
		else if(country.equals("UK")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.UK");
		}
		else if(country.equals("EU")) {
            baseURL = getPropertyManager().getProperty(prefix+"url.EU");
		}
		else {
            baseURL = getPropertyManager().getProperty(prefix+"url.CA");
		}
        driver.get(baseURL+"/shop/collections/gift-card?v=HSC-GC-25");
	}

	/**
	 * Selects the specified Gift Card amount.
	 *
	 * @param amount Gift Card amount
	 */
	public void selectAmount(String amount) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loadingoverlay")));
        for (WebElement option : Amount.findElements(By.tagName("option"))) {
            if (option.getText().equals(amount)) {
                option.click();
            }
        }
	}

	/**
     * Enters the GC Recipient Information.
     *
     * @param recipientFirstName value to enter in to the Recipient First Name field
     * @param recipientLastName value to enter in to the Recipient Last Name field
     * @param recipientEmail value to enter in to the Recipient Email field
     */
    public void enterRecipientInfo(String recipientFirstName, String recipientLastName, String recipientEmail) {
        RecipientFirstName.sendKeys(recipientFirstName);
        RecipientLastName.sendKeys(recipientLastName);
        RecipientEmail.sendKeys(recipientEmail);
    }

    /**
     * Enters the GC Sender Information.
     *
     * @param senderFirstName value to enter in to the Sender First Name field
     * @param senderLastName value to enter in to the Sender Last Name field
     * @param senderEmail value to enter in to the Sender Email field
     */
    public void enterSenderInfo(String senderFirstName, String senderLastName, String senderEmail) {
        SenderFirstName.sendKeys(senderFirstName);
        SenderLastName.sendKeys(senderLastName);
        SenderEmail.sendKeys(senderEmail);
    }

    /**
     * Enters a GC Message.
     *
     * @param message value to enter in to the Gift Card Message field
     */
    public void enterMessage(String message) {
        Message.sendKeys(message);
    }

    /**
     * Add the gift card to cart.
     */
    public void addToCart() {
        AddToCart.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.hsco-product-add")));
    }

}
