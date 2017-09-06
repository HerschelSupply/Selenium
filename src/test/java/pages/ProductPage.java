package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

/**
 * Handles interactions with the Herschel Product Page.
 * 
 * @author tmorris
 * 
 */
public class ProductPage extends Page<ProductPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "button[class='button button--primary hsco-add-to-cart']")
	private WebElement AddToCart;
    @FindBy(css = "button[class='button button--primary hsco-add-to-cart1']")
    private WebElement AddToCart1;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public ProductPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 10);
	}
	
	/**
	 * Load the Herschel Little America Backpack Product Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
		if(country.equals("US")) {
			driver.get("https://qa.herschel.com/shop/backpacks/barlow-backpack-medium?v=");
		}
		else if(country.equals("UK")) {
			driver.get("https://qa.herschelsupplyco.co.uk/shop/backpacks/mammoth-backpack-large?v=");
		}
		else if(country.equals("EU")) {
			//driver.get("https://qa.herschel.eu/shop/backpacks/little-america-backpack?v=");
			//driver.get("https://qa.herschel.eu/shop/backpacks/mammoth-backpack-medium?v=");
			driver.get("https://qa.herschel.eu/shop/backpacks/barlow-backpack-medium?v=");
		}
		else {
			driver.get("https://qa.herschel.ca/shop/backpacks/barlow-backpack-medium?v=");
		}
	}

	/**
	 * Add the product to cart.
	 */
	public void addToCart() {
	    wait.until(ExpectedConditions.elementToBeClickable(AddToCart));
		AddToCart.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
	}
}
