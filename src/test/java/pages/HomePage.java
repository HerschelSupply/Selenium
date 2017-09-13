package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles interactions with the Herschel Home Page.
 * 
 * @author tmorris
 * 
 */
public class HomePage extends Page<HomePage> {

	private Wait<WebDriver> wait;
	@FindBy(linkText = "Shop")
	private WebElement Shop;
	@FindBy(linkText = "Backpacks")
	private WebElement Backpacks;
	@FindBy(css = "div[data-product-title='Little America Backpack']")
	private WebElement LittleAmericaBackpack;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public HomePage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Load the Herschel Home Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
        if(country.equals("US")) {
            driver.get("https://qa.herschel.com/");
        }
        else if(country.equals("UK")) {
            driver.get("https://qa.herschelsupplyco.co.uk/");
        }
        else if(country.equals("EU")) {
            driver.get("https://qa.herschel.eu/");
        }
        else {
            driver.get("https://qa.herschel.ca/");
        }
	}

	public void setCookie(String country) {
		if(country.equals("US")) {
			driver.get("https://qa.herschel.com");
			Cookie region = new Cookie("geo-region", "US");
			driver.manage().addCookie(region);
		}
		else if(country.equals("UK")) {
			driver.get("https://qa.herschelsupplyco.co.uk/");
			Cookie region = new Cookie("geo-region", "UK");
			driver.manage().addCookie(region);
		}
		else if(country.equals("EU")) {
			driver.get("https://qa.herschel.eu/");
			Cookie region = new Cookie("geo-region", "EU");
			driver.manage().addCookie(region);
		}
		else {
			driver.get("https://qa.herschel.ca/");
			Cookie region = new Cookie("geo-region", "CA");
			driver.manage().addCookie(region);
		}
	}

	/**
	 * Navigates to the Backpacks section using the navigation dropdown.
	 */
	public void navigateToBackpacks() {
		Shop.click();
		wait.until(ExpectedConditions.elementToBeClickable(Backpacks));
		Backpacks.click();
	}

	/**
	 * Clicks on the specified product tile.
	 *
	 * @param productName name of the product to select
	 */
	public void selectProductTile(String productName) {
		if (productName.equals("Little America Backpack")) {
			driver.findElement(By.cssSelector("a[href='/shop/backpacks/little-america-backpack?v=10014-00007-OS']")).click();
		}
		else {
			System.out.println("selectProduct method has not been configured for your specified product: " + productName);
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
	}

	/**
	 * Click the Little America Backpack Tile.
	 */
	public void selectLittleAmericaBackpack() {
		LittleAmericaBackpack.click();
	}
}
