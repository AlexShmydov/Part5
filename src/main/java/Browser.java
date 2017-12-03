import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Browser extends BaseEntity {
    private WebDriver driver;
    private final long WAIT_TIMER = 30;

    public Browser(String browserType) {
        switch (browserType){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
              System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\IEDriverServer.exe");
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability("ignoreZoomSetting", true);
                driver = new InternetExplorerDriver(caps);
                break;
            default:
                Assert.assertTrue(false,
                        "Unsupported driver's type");
        }
    }

    private WebDriver getInstance() {
        return driver;
    }

    public void goToUrl(String url) {
        getInstance().get(url);
        waitToLoadPage();
    }

    public WebDriver.TargetLocator switchtoIframe() {
        return getInstance().switchTo();
    }

    public void scrollToElement(WebElement targetElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", targetElement);
    }

    public void returnToPage() {
        getInstance().switchTo().defaultContent();
    }

    public void dispose() {
        getInstance().quit();
    }

    public void waitToLoadPage() {
        new WebDriverWait(getInstance(), WAIT_TIMER).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement getElement(String byType, String locator) {
        return (new WebDriverWait(getInstance(), WAIT_TIMER)).until(ExpectedConditions.presenceOfElementLocated(getBy(byType, locator)));
    }

    public WebElement getElementWithoutWaiting(String byType, String locator) {
        return getInstance().findElement(getBy(byType, locator));
    }

    public List<WebElement> getElements(String byType, String locator) {
        return (new WebDriverWait(getInstance(), WAIT_TIMER)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getBy(byType, locator)));
    }

    public List<WebElement> getElementsWithoutWaiting(String byType, String locator) {
        return getInstance().findElements(getBy(byType, locator));
    }


}
