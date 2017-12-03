public class BaseForm {
    protected Browser browser;

    public BaseForm(Browser browser) {
        this.browser = browser;
        browser.waitToLoadPage();
    }
}
