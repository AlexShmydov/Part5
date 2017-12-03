import org.testng.Assert;

public class MainForm extends BaseForm {
    private final String MAIN_LOCATOR_XPATH = "//img[@alt='Onliner']";
    private final String ENTER_BUTTON_LOCATOR_XPATH = "//div[@id='userbar']//div[text()='Вход ']";
    private final String LOGIN_FIELD_LOCATOR_XPATH = "//input[@data-field='login' and @type='text']";
    private final String PASSWORD_FIELD_LOCATOR_XPATH = "//input[@data-field='login' and @type='password']";
    private final String LOGIN_BUTTON_LOCATOR_XPATH = "//button[contains (@class,'auth-box__auth-submit') and @type='submit']";
    private final String SEARCH_FIELD_LOCATOR_XPATH = "//input[@class='fast-search__input']";
    private final String RESULTS_FAST_SEARCHING_AREA_LOCATOR_XPATH = "//li[contains(@class,'search__result')]";
    private final String IFRAME_XPATH = "//iframe[@class='modal-iframe']";

    public MainForm(Browser browser) {
        super(browser);
        Assert.assertNotNull(browser.getElement(browser.XPATH_TYPE, MAIN_LOCATOR_XPATH),
                "Main form is not opened!");
    }

    public void searchProduct(String product) {
        browser.getElement(browser.XPATH_TYPE, SEARCH_FIELD_LOCATOR_XPATH).sendKeys(product);
        browser.switchtoIframe().frame(browser.getElement(browser.XPATH_TYPE, IFRAME_XPATH));
        browser.getElements(browser.XPATH_TYPE, RESULTS_FAST_SEARCHING_AREA_LOCATOR_XPATH);
        browser.returnToPage();
        browser.getElement(browser.XPATH_TYPE, SEARCH_FIELD_LOCATOR_XPATH).click();
    }

    public void logIn(String login, String password) {
        browser.getElement(browser.XPATH_TYPE, ENTER_BUTTON_LOCATOR_XPATH).click();
        browser.getElement(browser.XPATH_TYPE, LOGIN_FIELD_LOCATOR_XPATH).sendKeys(login);
        browser.getElement(browser.XPATH_TYPE, PASSWORD_FIELD_LOCATOR_XPATH).sendKeys(password);
        browser.getElement(browser.XPATH_TYPE, LOGIN_BUTTON_LOCATOR_XPATH).click();
        browser.waitToLoadPage();
    }
}
