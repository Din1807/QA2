package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    private final String HOME_PAGE_URL = "delfi.lv";
    private WebDriver driver;
    private WebDriverWait wait;

    public BaseFunc(){  //sozdali konstruktor
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);

    }

    public void goToUrl(String url){ //proverka ssilki
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        driver.get(url);
    }

    public void openHomePage(){
        goToUrl(HOME_PAGE_URL);
        //     driver.get(HOME_PAGE_URL);
    }
    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }
    public WebElement findElement(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void click(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
    public void closeBrowser(){
        driver.quit();
    }
}