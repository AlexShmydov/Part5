import org.openqa.selenium.By;
import org.testng.Assert;

public class BaseEntity {

    public final String XPATH_TYPE = "XPATH";
    public final String CSS_TYPE = "CSS";
    public final String ID_TYPE = "ID";
    public final String CLASS_NAME_TYPE = "CLASS_NAME";
    public final String NAME_TYPE = "NAME";

    protected By getBy(String byType, String locator) {
        switch (byType) {
            case XPATH_TYPE:
                return By.xpath(locator);
            case CSS_TYPE:
                return By.cssSelector(locator);
            case ID_TYPE:
                return By.id(locator);
            case CLASS_NAME_TYPE:
                return By.className(locator);
            case NAME_TYPE:
                return By.name(locator);
            default:
                Assert.assertTrue(false,
                        "Unsupported type of By");
                return null;
        }
    }
}
