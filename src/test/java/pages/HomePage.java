package pages;

import base.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
	@FindBy(css = "a[data-modal='search']")
	private WebElement SearchIcon;
	@FindBy(css = "input[id='js-site-search-query-input']")
	private WebElement SearchBox;
	@FindBy(linkText = "Backpacks")
	private WebElement Backpacks;
	@FindBy(css = "a[href='/shop/backpacks/little-america-backpack?v=10014-00007-OS']")
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
	 * Clicks on the Search icon to open the Search Textbox.
	 */
	public void openSearch() {
		SearchIcon.click();
	}

	/**
	 * Clicks on the Search icon to open the Search Textbox.
	 *
	 * @param searchTerm String to enter in to the Search box
	 */
	public void inputSearchTerm(String searchTerm) {
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		SearchBox.sendKeys(searchTerm);
	}

	/**
	 * Performs a Search Request.
	 */
	public void performSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		SearchBox.sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
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
			new Actions(driver).moveToElement(LittleAmericaBackpack).perform();
			driver.findElement(By.cssSelector("a[href='/shop/backpacks/little-america-backpack?v=10014-00007-OS'] div[class='col-xs-6 product__title']")).click();
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
