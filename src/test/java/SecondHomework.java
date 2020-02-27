import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class SecondHomework {
    private final By ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_COMMENT_TITLE = By.xpath("(.//h1[contains(@class, 'article-title')])[2]");
    private final By COMMENT_BTN = By.xpath(".//a[contains(@class,'btn-comments')]");
    private final By TITLE_AND_COMMENT = By.tagName("article");
    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ANON_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    private final Logger LOGGER = LogManager.getLogger(SecondHomework.class);
    private WebDriver driver;

    @Test
    public void delfiArticleTest() {
        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        LOGGER.info("Opening browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        LOGGER.info("Opening Delfi home page");
        List<WebElement> articles = driver.findElements(ARTICLE_TITLE);
        LOGGER.info("Creating articles list");
        int articleNum=1;
        String mainPageTitleText = articles.get(articleNum).getText();
        LOGGER.info("Main page text: " + mainPageTitleText);
        articles.get(articleNum).click();
        String articleTitleText = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        LOGGER.info("Article page text: " + articleTitleText);
        Assertions.assertEquals(mainPageTitleText, articleTitleText, "Wrong title on article page!");
        driver.findElement(COMMENT_BTN).click();
        if (!driver.findElements(COMMENT_BTN).isEmpty()) {
            driver.findElement(COMMENT_BTN).click();
            String commentTitleText = driver.findElement(ARTICLE_COMMENT_TITLE).getText();
            LOGGER.info("Comment page text: " + commentTitleText);
            Assertions.assertEquals(mainPageTitleText, commentTitleText, "Wrong title on comment page!");
        } else{
            LOGGER.info("Comment page does not exist or locked.");
        }
    }

    @Test
    public void delfiCommentTest() {
        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        LOGGER.info("Opening browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        LOGGER.info("Opening Delfi home page");
        List<WebElement> articles = driver.findElements(TITLE_AND_COMMENT);
        LOGGER.info("Creating articles list");
        int articleNum = 6;
        LOGGER.info("Article name: " + articles.get(articleNum).findElement(ARTICLE_TITLE).getText());
        int mainPageCommentCount, articleCommentCount, anonCommentCount, regCommentCount;
        if (!articles.get(articleNum).findElements(MAIN_PAGE_COMMENT_COUNT).isEmpty()) {
            mainPageCommentCount = Integer.parseInt(articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().substring(1, articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().length() - 1));
        } else {
            mainPageCommentCount = 0;
        }
        LOGGER.info("Main page comment count was: " + mainPageCommentCount);
        articles.get(articleNum).findElement(ARTICLE_TITLE).click();
        if (!driver.findElements(ARTICLE_COMMENT_COUNT).isEmpty()) {
            articleCommentCount = Integer.parseInt(driver.findElement(ARTICLE_COMMENT_COUNT).getText().substring(1, driver.findElement(ARTICLE_COMMENT_COUNT).getText().length() - 1));
        } else {
            articleCommentCount = 0;
        }
        LOGGER.info("Article page comment count was: " + articleCommentCount);
        Assertions.assertEquals(mainPageCommentCount, articleCommentCount, "Wrong comment count on article page!");
        if (!driver.findElements(COMMENT_BTN).isEmpty()) {
            driver.findElement(COMMENT_BTN).click();
            if (!driver.findElements(ANON_COMMENT_COUNT).isEmpty()) {
                anonCommentCount = Integer.parseInt(driver.findElement(ANON_COMMENT_COUNT).getText().substring(1, driver.findElement(ANON_COMMENT_COUNT).getText().length() - 1));
                regCommentCount = Integer.parseInt(driver.findElement(REG_COMMENT_COUNT).getText().substring(1, driver.findElement(REG_COMMENT_COUNT).getText().length() - 1));
                LOGGER.info("Number of comment page comments Anon " + anonCommentCount + " and Reg " + regCommentCount + " all was: " + (anonCommentCount + regCommentCount));
                Assertions.assertEquals(mainPageCommentCount, anonCommentCount + regCommentCount - 1, "Wrong count on comment page!");
            } else {
                LOGGER.info("Comment page does not exist or locked.");
            }
        } else {
            LOGGER.info("Comment page does not exist.");
        }
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}