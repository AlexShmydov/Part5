import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    private Browser browser;

    private void createInstance(String browser){
        if (this.browser == null)
            this.browser = new Browser(browser);
    }

    public Browser getBrowser() {
        return browser;
    }

    @BeforeClass
    @Parameters({"url","browser"})
    public void init(String url,String browser) {
        createInstance(browser);
        getBrowser().goToUrl(url);
    }

    @AfterClass
    public void tearDown() {
        getBrowser().dispose();
    }
}
