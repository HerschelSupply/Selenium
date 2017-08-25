package pages;

import base.Page;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Handles interactions with the Herschel Home Page.
 * 
 * @author tmorris
 * 
 */
public class HomePage extends Page<HomePage> {

	@FindBy(css = "div[data-product-title='Little America Backpack']")
	private WebElement LittleAmericaBackpack;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public HomePage(final WebDriver driver) {
		super(driver);
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
	 * Click the Little America Backpack Tile.
	 */
	public void selectLittleAmericaBackpack() {
		LittleAmericaBackpack.click();
	}
}
