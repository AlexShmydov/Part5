import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class TrashForm extends BaseForm {
    private final String MAIN_LOCATOR_CSS = ".cart-header__title";
    private final String PRODUCT_ITEM_CLASS = ".cart-panel";
    private final String TITLE_XPATH = "//div[@class='cart-product-title']";
    private final String COST_XPATH = "//div[@class='cart-product__price']";
    private final String DESCRIPTION_XPATH = "//div[@class='cart-product__description']";

    public TrashForm(Browser browser) {
        super(browser);
        browser.waitToLoadPage();
        Assert.assertNotNull(browser.getElement(browser.CSS_TYPE, MAIN_LOCATOR_CSS),
                "Trash form is not opened!");
    }

    public List<Product> getProductsFromTrash() {
        List<Product> products = new ArrayList<>();
        for (WebElement productItem : browser.getElements(browser.CSS_TYPE, PRODUCT_ITEM_CLASS)) {
            products.add(new Product(
                    productItem.findElement(By.xpath(TITLE_XPATH)).getText(),
                    productItem.findElement(By.xpath(COST_XPATH)).getText(),
                    productItem.findElement(By.xpath(DESCRIPTION_XPATH)).getText())
            );
        }
        return products;
    }

}
