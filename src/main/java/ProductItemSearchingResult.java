import org.openqa.selenium.WebElement;

public class ProductItemSearchingResult extends BaseEntity {
    private final String ITEM_TITLE_CLASS = "schema-product__title";
    private final String ITEM_DESCRIPTION_CLASS = "schema-product__description";
    private final String ITEM_PRICE_CLASS = "schema-product__price";
    private final String PRODUCTS_LINK_XPATH = "//div[@class='%s']//a";
    private WebElement element;

    public ProductItemSearchingResult(WebElement element) {
        this.element = element;
    }

    public WebElement getElement() {
        return element;
    }

    public String getName() {
        return element.findElement(getBy(CLASS_NAME_TYPE, ITEM_TITLE_CLASS)).getText();
    }

    public String getDescription() {
        return element.findElement(getBy(CLASS_NAME_TYPE, ITEM_DESCRIPTION_CLASS)).getText();
    }

    public String getPriceWithCurrency() {
        return element.findElement(getBy(CLASS_NAME_TYPE, ITEM_PRICE_CLASS)).getText();
    }

    public String getLink() {
        return element.findElement(getBy(CLASS_NAME_TYPE, ITEM_TITLE_CLASS)).findElement(getBy(XPATH_TYPE, "a")).getAttribute("href");
    }
}
