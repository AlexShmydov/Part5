import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProductPageForm extends BaseForm {

    private final String MAIN_LOCATOR_XPATH = "//div[@class='catalog-masthead']";
    private final String OFFERS_XPATH_LOCATOR = "//div[@class='product-aside__group']/div";
    private final String BUTTON_ADD_TO_TRASH_CSS = ".product-aside__item-button";
    private final String PRICE_CLASS = "product-aside__price";
    private final String TRASH_CSS = ".product-aside__item-button";

    public ProductPageForm(Browser browser, String productName) {
        super(browser);
        Assert.assertNotNull(browser.getElement(browser.XPATH_TYPE, MAIN_LOCATOR_XPATH),
                "Main form is not opened!");
        Assert.assertTrue(browser.getElement(browser.XPATH_TYPE, MAIN_LOCATOR_XPATH).getText().contains(productName),
                "Not that which is necessary.");
    }

    public ProductPageForm(Browser browser) {
        super(browser);
        Assert.assertNotNull(browser.getElement(browser.XPATH_TYPE, MAIN_LOCATOR_XPATH),
                "Main form is not opened!");
    }

    public List<WebElement> getOffers() {
        return browser.getElementsWithoutWaiting(browser.XPATH_TYPE, OFFERS_XPATH_LOCATOR);
    }

    public String addProductToTrash(int offersNumber) {
        browser.markElement(getOffers().get(offersNumber).findElement(By.cssSelector(BUTTON_ADD_TO_TRASH_CSS))).click();
        return getOffers().get(offersNumber).findElement(By.className(PRICE_CLASS)).getText();
    }

    public void openTrash() {
        browser.markElement(browser.getElementWithoutWaiting(browser.CSS_TYPE, TRASH_CSS)).click();
    }
}
