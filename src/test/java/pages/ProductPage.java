package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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
    @FindBy(css = "div[class='hsco-product-details'] h1[class='product-overview__title']")
    private WebElement ProductName;
    @FindBy(css = "div[class='hsco-product-details'] h4[class='hsco-product-color'] span")
    private WebElement SkuName;
    @FindBy(css = "div[id='toast-container']")
    private WebElement Toaster;
    @FindBy(css = "a[data-modal='cart']")
    private WebElement CartIcon;
    @FindBy(css = "p[class='m-y-0 bfx-product-name']")
    private WebElement DrawerProductName;
    @FindBy(css = "p[class='m-y-0 text-copy2 bfx-product-color']")
    private WebElement DrawerSkuName;
    @FindBy(css = "a[href='/shop/checkout']")
    private WebElement CheckoutLink;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public ProductPage(final WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 12);
	}
	
	/**
	 * Load the Herschel Little America Backpack Product Page for the specified country.
	 *
	 * @param country Herschel country site to load
	 */
	public void load(String country) {
		if(country.equals("US")) {
			driver.get("https://qa.herschel.com/shop/backpacks/little-america-backpack?v=10014-00007-OS");
		}
		else if(country.equals("UK")) {
			driver.get("https://qa.herschelsupplyco.co.uk/shop/backpacks/little-america-backpack?v=10014-00007-OS");
		}
		else if(country.equals("EU")) {
			//driver.get("https://qa.herschel.eu/shop/backpacks/little-america-backpack?v=");
			//driver.get("https://qa.herschel.eu/shop/backpacks/mammoth-backpack-medium?v=");
			driver.get("https://qa.herschel.eu/shop/backpacks/little-america-backpack?v=10014-00007-OS");
		}
		else {
			driver.get("https://qa.herschel.ca/shop/backpacks/little-america-backpack?v=10014-00007-OS");
		}
	}

	/**
	 * Clicks on the specified product SKU.
	 *
	 * @param skuName name of the SKU to select
	 */
	public void selectSKU(String skuName) {
		driver.findElement(By.cssSelector("input[data-color='"+skuName+"']")).findElement(By.xpath("../img")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
		wait.until(ExpectedConditions.elementToBeClickable(AddToCart));
	}

	/**
	 * Add the product to cart.
	 */
	public void addToCart() {
	    wait.until(ExpectedConditions.elementToBeClickable(AddToCart));
		AddToCart.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
	}

    /**
     * Gets the product name on the Product Page.
     *
     * @return String containing the product name
     */
    public String getProductName() {
        //return driver.findElement(By.cssSelector("div[class='hsco-product-details'] h1[class='product-overview__title']")).getText();
        return ProductName.getText();
    }

    /**
     * Gets the SKU name on the Product Page.
     *
     * @return String containing the SKU name
     */
    public String getSkuName() {
        return SkuName.getText();
    }

    /**
     * Gets the product name displayed in the Toaster.
     *
     * @return String containing the product name displayed in the Toaster
     */
    public String getToasterProduct() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
        List<WebElement> toasterInfo = Toaster.findElements(By.cssSelector("div[class='col-xs-5'] p"));
        return toasterInfo.get(0).getText();
    }

    /**
     * Gets the SKU name displayed in the Toaster.
     *
     * @return String containing the SKU name displayed in the Toaster
     */
    public String getToasterSku() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loadingoverlay']")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
        List<WebElement> toasterInfo = Toaster.findElements(By.cssSelector("div[class='col-xs-5'] p"));
        return toasterInfo.get(1).getText();
    }

	/**
	 * Opens the side drawer.
	 */
	public void openDrawer() {
        //wait for Toaster to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='hsco-product-add text-grey4']")));
        CartIcon.click();
        //wait for drawer to open
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/shop/checkout']")));
	}

    /**
     * Gets the first product name displayed in the Drawer.
     *
     * @return String containing the first product name displayed in the Drawer
     */
    public String getDrawerProduct() {
        return DrawerProductName.getText();
    }

    /**
     * Gets the first SKU name displayed in the Drawer.
     *
     * @return String containing the first SKU name displayed in the Drawer
     */
    public String getDrawerSku() {
        return DrawerSkuName.getText();
    }

    /**
     * Clicks the Checkout Link in the Drawer.
     */
    public void clickDrawerCheckoutLink() {
        CheckoutLink.click();
    }
}
