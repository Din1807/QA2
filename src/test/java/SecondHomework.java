import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class SecondHomework {
    private final By ARTICLE_TITLE = By.xpath("(.//h1[contains(@class, 'headline__title')])[2]");
    private final By ARTICLE_COMMENT = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_COMMENT_TITLE = By.xpath("(.//h1[contains(@class, 'article-title')]//a[starts-with(a,'')])[2]");

    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'd-print-none')]");
    private final By ANON_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    @Test
    public void delfiArticleTest(){
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        WebElement firstTitle = driver.findElement(ARTICLE_TITLE); // <-Find 1 title
        String firstTitleText = firstTitle.getText(); // <- Save title
        firstTitle.click(); // <- Click on this element
        WebElement articleTitle = driver.findElement(ARTICLE_PAGE_TITLE); // <- Find articles
        String articleTitleText = articleTitle.getText(); // <- Save article
        Assertions.assertEquals(firstTitleText,articleTitleText, "Wrong title on article page!"); // <- Check
        driver.findElement(ARTICLE_COMMENT_COUNT).click(); // <-Click to comment page
        WebElement CommentTitle = driver.findElement(ARTICLE_COMMENT_TITLE);
        String CommentTitleText = CommentTitle.getText();
        Assertions.assertEquals(firstTitleText,CommentTitleText, "Wrong title on comment page!"); // <- Check
        driver.quit();
    }
    @Test
    public void delfiCommentTest(){
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");
        WebElement mainPageComment = driver.findElement(MAIN_PAGE_COMMENT_COUNT); // <-Find 1 title
        String mainPageCommentText = mainPageComment.getText(); // <- Save title
        int mainPageCommentCount = Integer.parseInt(mainPageCommentText.substring(1,mainPageCommentText.length()-1));
        driver.findElement(ARTICLE_COMMENT).click();// <- Click on this element
        WebElement articleComment = driver.findElement(ARTICLE_COMMENT_COUNT); // <- Find articles
        String articleCommentText = articleComment.getText(); // <- Save article
        int articleCommentCount = Integer.parseInt(articleCommentText.substring(1,articleCommentText.length()-1));
        Assertions.assertEquals(mainPageCommentCount,articleCommentCount, "Wrong comment count on article page!"); // <- Check
        driver.findElement(ARTICLE_COMMENT_COUNT).click(); // <-Click to comment page
        WebElement anonComment = driver.findElement(ANON_COMMENT_COUNT);
        String anonCommentCountText = anonComment.getText();
        int anonCommentCount = Integer.parseInt(anonCommentCountText.substring(1,anonCommentCountText.length()-1));
        WebElement regComment = driver.findElement(REG_COMMENT_COUNT);
        String regCommentCountText = regComment.getText();
        int regCommentCount = Integer.parseInt(regCommentCountText.substring(1,regCommentCountText.length()-1));
        Assertions.assertEquals(mainPageCommentCount,anonCommentCount+regCommentCount, "Wrong count on comment page!"); // <- Check
        driver.quit();

    }
}
