import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Browser extends BaseEntity {
    private WebDriver driver;
    private final long WAIT_TIMER = 30;
    private final String HUB_PATH = "http://localhost:4444/wd/hub/";

    public Browser(String browserType) {
        DesiredCapabilities capabilities = null;

        switch (browserType) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            default:
                Assert.assertTrue(false,
                        "Unsupported driver's type");
        }
        capabilities.setJavascriptEnabled(true);
        capabilities.acceptInsecureCerts();
        try {
            driver = new RemoteWebDriver(new URL(HUB_PATH), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
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

    public void clickOnLocation(int x, int y) {
        new Actions(driver).moveByOffset(x, y).click().perform();
    }

    public WebElement markElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        return element;
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
