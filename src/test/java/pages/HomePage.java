package pages;

import base.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
		String prefix = "";
		if(getPropertyManager().getProperty("testEnv").equals("prod")) {
			prefix = "prod.";
		}
        if(country.equals("US")) {
			String urlUS = getPropertyManager().getProperty(prefix+"url.US");
            driver.get(urlUS);
        }
        else if(country.equals("UK")) {
			String urlUK = getPropertyManager().getProperty(prefix+"url.UK");
			driver.get(urlUK);
        }
        else if(country.equals("EU")) {
			String urlEU = getPropertyManager().getProperty(prefix+"url.EU");
			driver.get(urlEU);
        }
        else {
			String urlCA = getPropertyManager().getProperty(prefix+"url.CA");
			driver.get(urlCA);
        }
	}

	public void setCookie(String country) {
		String prefix = "";
		if(getPropertyManager().getProperty("testEnv").equals("prod")) {
			prefix = "prod.";
		}
		if(country.equals("US")) {
			String urlUS = getPropertyManager().getProperty(prefix+"url.US");
			driver.get(urlUS);
			Cookie region = new Cookie("geo-region", "US");
			driver.manage().addCookie(region);
		}
		else if(country.equals("UK")) {
			String urlUK = getPropertyManager().getProperty(prefix+"url.UK");
			driver.get(urlUK);
			Cookie region = new Cookie("geo-region", "UK");
			driver.manage().addCookie(region);
		}
		else if(country.equals("EU")) {
			String urlEU = getPropertyManager().getProperty(prefix+"url.EU");
			driver.get(urlEU);
			Cookie region = new Cookie("geo-region", "EU");
			driver.manage().addCookie(region);
		}
		else {
			String urlCA = getPropertyManager().getProperty(prefix+"url.CA");
			driver.get(urlCA);
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        jse.executeScript("arguments[0].scrollIntoView()", LittleAmericaBackpack);

		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		if (productName.equals("Little America Backpack")) {
			//new Actions(driver).moveToElement(LittleAmericaBackpack).perform();
			driver.findElement(By.cssSelector("a[href='/shop/backpacks/little-america-backpack?v=10014-00007-OS']")).click();
			//driver.findElement(By.cssSelector("a[href='/shop/backpacks/little-america-backpack?v=10014-00007-OS'] div[class='col-xs-6 product__title']")).click();
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

	/**
	 * Checks of the specified product appears in the Search Results.
	 *
	 * @param productTitle used to check if this product is displayed in the Search Results
	 */
	public boolean isSearchResultDisplayed(String productTitle) {
		List<WebElement> searchResults = driver.findElements(By.cssSelector("div[class='col-xs-6 product__title']"));
		for (WebElement searchResult : searchResults) {
			if(searchResult.getText().equals(productTitle)) {
				return true;
			}
		}
		//return false if the search result is not found
		return false;
	}

	/**
	 * Checks of the specified product appears in the Modal Search Results.
	 *
	 * @param productTitle used to check if this product is displayed in the Modal Search Results
	 */
	public boolean isModalSearchResultDisplayed(String productTitle) {
		//wait for Modal Search Rsults ot appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='js-search-category-results']")));
		List<WebElement> modalSearchResults = driver.findElements(By.cssSelector("div[class='js-search-category-results'] p[class='m-y-0']"));
		for (WebElement modalSearchResult : modalSearchResults) {
			if(modalSearchResult.getText().equals(productTitle)) {
				return true;
			}
		}
		//return false if the modal search result is not found
		return false;
	}
}
