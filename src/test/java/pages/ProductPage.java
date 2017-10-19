package pages;

import base.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

/**
 * Handles interactions with the Herschel Product Page.
 * 
 * @author tmorris
 * 
 */
public class ProductPage extends Page<ProductPage> {

	private Wait<WebDriver> wait;
	@FindBy(css = "button.button.button--primary.hsco-add-to-cart")
    private WebElement AddToCart;
    @FindBy(css = "div.hsco-product-details h1.product-overview__title")
    private WebElement ProductName;
    @FindBy(css = "div.hsco-product-details h4.hsco-product-color span")
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
    //Mobile Device Elements
	@FindBy(css = "h1.product-overview__title")
	private WebElement ProductNameMobile;
	@FindBy(css = "h4.hsco-product-color")
	private WebElement SkuNameMobile;
	@FindBy(css = "div.colors-list")
	private WebElement SkuSelection;

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
			baseURL = getPropertyManager().getProperty(prefix+"url.EU");;
		}
		else {
			baseURL = getPropertyManager().getProperty(prefix+"url.CA");
		}
		driver.get(baseURL+"/shop/backpacks/little-america-backpack?v=10014-00007-OS");
	}

	/**
	 * Clicks on the specified product SKU.
	 *
	 * @param skuName name of the SKU to select
	 */
	public void selectSKU(String skuName) {
		waitForLoadingOverlayToDisappear();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", SkuSelection);
		jse.executeScript("window.scrollBy(0,-250)", "");
		driver.findElement(By.cssSelector("input[data-color='"+skuName+"']")).findElement(By.xpath("../img")).click();
		waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.hsco-add-to-cart")));
	}

	/**
	 * Add the product to cart.
	 */
	public void addToCart() {
		waitForLoadingOverlayToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.hsco-add-to-cart")));
		//If on the Little America Backpack, the Add to Card button may appear below the fold, making it unclickable.
		//Scrolling down a little makes the button appear on screen and be clickable.
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", AddToCart);
		jse.executeScript("window.scrollBy(0,-250)", "");
	    AddToCart.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.hsco-product-add")));
	}

    /**
     * Gets the product name on the Product Page.
     *
     * @return String containing the product name
     */
    public String getProductName() {
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
		String product = "no toaster product found";
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				product = driver.findElements(By.cssSelector("div.col-xs-5 p")).get(0).getText();
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				System.out.print("Stale Element Exception occurred on Toaster Product");
			}
			attempts++;
		}
		return product;
    }

    /**
     * Gets the SKU name displayed in the Toaster.
     *
     * @return String containing the SKU name displayed in the Toaster
     */
    public String getToasterSku() {
        String sku = "no toaster sku found";
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                sku = driver.findElements(By.cssSelector("div.col-xs-5 p")).get(1).getText();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
                System.out.print("Stale Element Exception occurred on Toaster SKU");
            }
            attempts++;
        }
        return sku;
    }

	/**
	 * Opens the side drawer.
	 */
	public void openDrawer() {
        //wait for Toaster to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.hsco-product-add")));
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

	/**
	 * Waits for the loading overlay to disappear.
	 */
	public void  waitForLoadingOverlayToDisappear() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loadingoverlay")));
	}

	/**
	 * Gets the product name on the Product Page on a mobile device.
	 *
	 * @return String containing the product name
	 */
	public String getProductNameMobile() {
		return ProductNameMobile.getText();
	}

	/**
	 * Gets the SKU name on the Product Page on a mobile device.
	 *
	 * @return String containing the SKU name
	 */
	public String getSkuNameMobile() {
		return SkuNameMobile.getText();
	}
}
