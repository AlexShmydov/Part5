import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class ResultOfSearchingForm extends BaseForm {
    private final String MAIN_LOCATOR_XPATH = "//div[@class='schema-grid']";
    private final String ITEMS_PRODUCTS_LOCATOR_CSSSELECTOR = ".schema-product__group";

    public ResultOfSearchingForm(Browser browser) {
        super(browser);
        Assert.assertNotNull(browser.getElement(browser.XPATH_TYPE, MAIN_LOCATOR_XPATH),
                "Fast search form is not opened!");
    }

    public Product selectRandomProduct() {
        List<WebElement> products = browser.getElementsWithoutWaiting(browser.CSS_TYPE, ITEMS_PRODUCTS_LOCATOR_CSSSELECTOR);
        ProductItemSearchingResult productItemSearchingResult = new ProductItemSearchingResult(products.get(Helper.getRandomValue(products.size()-1,0)));
        browser.scrollToElement(productItemSearchingResult.getElement());
        Product product = new Product(
                productItemSearchingResult.getName(),
                productItemSearchingResult.getPriceWithCurrency(),
                productItemSearchingResult.getDescription()
        );
        product.setLink(productItemSearchingResult.getLink());
        return product;
    }
}
