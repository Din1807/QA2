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
    private final By ARTICLE_COMMENT_TITLE = By.xpath("(.//h1[contains(@class, 'article-title')]//a[starts-with(a,'')])[2]");
    private final By COMMENT_BTN = By.xpath(".//a[contains(@class,'btn-comments')]");
    private final By TITLE_AND_COMMENT = By.xpath("//span[contains(@class, 'd-block') and .//a[contains(@class, 'comment-count')]]");
    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ANON_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");

    @Test
    public void delfiArticleTest() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        List<WebElement> articles = driver.findElements(ARTICLE_TITLE);
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getText().length() != 0) {
                System.out.println(i + ": " + articles.get(i).getText());
            }
        }
        String mainPageTitleText = articles.get(1).getText();
        articles.get(1).click();
        String articleTitleText = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        Assertions.assertEquals(mainPageTitleText, articleTitleText, "Wrong title on article page!");
        driver.findElement(COMMENT_BTN).click();
        String commentTitleText = driver.findElement(ARTICLE_COMMENT_TITLE).getText();
        Assertions.assertEquals(mainPageTitleText, commentTitleText, "Wrong title on comment page!");
        driver.quit();

    }

    @Test
    public void delfiCommentTest() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        List<WebElement> articles = driver.findElements(TITLE_AND_COMMENT);
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getText().length() != 0 && articles.get(i).getText().length() > 20) {
                System.out.println(i + ": " + articles.get(i).getText());
            }
        }
        int articleNum = 3;
        int mainPageCommentCount = Integer.parseInt(articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().substring(1, articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().length() - 1));
        articles.get(articleNum).findElement(ARTICLE_TITLE).click();
        int articleCommentCount = Integer.parseInt(driver.findElement(ARTICLE_COMMENT_COUNT).getText().substring(1, driver.findElement(ARTICLE_COMMENT_COUNT).getText().length() - 1));
        Assertions.assertEquals(mainPageCommentCount, articleCommentCount, "Wrong comment count on article page!");
        driver.findElement(COMMENT_BTN).click();
        int anonCommentCount = Integer.parseInt(driver.findElement(ANON_COMMENT_COUNT).getText().substring(1, driver.findElement(ANON_COMMENT_COUNT).getText().length() - 1));
        int regCommentCount = Integer.parseInt(driver.findElement(REG_COMMENT_COUNT).getText().substring(1, driver.findElement(REG_COMMENT_COUNT).getText().length() - 1));
        Assertions.assertEquals(mainPageCommentCount, anonCommentCount + regCommentCount, "Wrong count on comment page!");
        driver.quit();
    }
}
