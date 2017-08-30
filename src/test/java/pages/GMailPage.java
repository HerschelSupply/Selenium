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
 * Handles interactions with GMail for email validation.
 * 
 * @author tmorris
 * 
 */
public class GMailPage extends Page<GMailPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "input[type='email']")
	private WebElement EmailField;
	@FindBy(css = "input[type='password']")
	private WebElement PasswordField;
	@FindBy(css = "span[class='RveJvd snByac']")
	private WebElement NextButton;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public GMailPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Load GMail.
	 */
	public void load() {
        driver.get("https://gmail.com");
	}

	/**
	 * Sign in to GMail using the credentials provided.
	 *
	 * @param email email address used to sign in
	 * @param password password used to sign in
	 */
	public void gmailSignIn(String email, String password) {
		EmailField.sendKeys(email);
		NextButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(PasswordField));
        //Sometimes the line of code above returns a stale element reference error.  If the error becomes common, let's switch to commented out line of code below
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
		PasswordField.sendKeys(password);
		NextButton.click();
		//wait until GMail Email Summary Page loads
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='T-I J-J5-Ji T-I-KE L3']")));
	}

	/**
	 * Returns the Sender's Name for the most recent email in the user's inbox.
	 *
	 * @return String Name of Email Sender
	 */
	public String getSendersName() {

	    //If ids change on Gmail site often, let's use the following identifier driverfindElement(By.cssSelector("tbody")).findElements(By.cssSelector("tr")).get(1).findElements(By.cssSelector("td")).get(3).findElements(By.cssSelector("div")).get(1).findElements(By.cssSelector("span")).get(0).getText()
        WebElement td = driver.findElements(By.cssSelector("td[class='yX xY ']")).get(0);
        WebElement div = td.findElements(By.cssSelector("div")).get(1);
        return div.findElements(By.cssSelector("span")).get(0).getText();
    }

	/**
	 * Returns the Sender's Email Address for the most recent email in the user's inbox.
	 *
	 * @return String Sender's Email Address
	 */
	public String getSendersEmail() {
		return driver.findElements(By.cssSelector("span[class='zF']")).get(0).getAttribute("email");
	}

	/**
	 * Returns the Email Title for the most recent email in the user's inbox.
	 *
	 * @return String Email Title
	 */
	public String getEmailTitle() {
		return driver.findElements(By.cssSelector("span[id=':2j']")).get(0).getText();
	}

	/**
	 * Returns the Email SubTitle for the most recent email in the user's inbox.
	 *
	 * @return String Email SubTitle
	 */
	public String getEmailSubTitle() {
		return driver.findElements(By.cssSelector("span[class='y2']")).get(0).getText();
	}
}
