package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By TITLE_AND_COMMENT = By.tagName("article");
    private final By MAIN_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitleById(int id) {
        List<WebElement> titles = baseFunc.findElements(TITLE);
        return titles.get(id - 1).getText();
    }

    public void goToArticleById(int id){
        List<WebElement>titles = baseFunc.findElements(TITLE);
        baseFunc.click(titles.get(id-1));
    }
    public int getTitleCommentCountById(int id) {
        List<WebElement> articles = baseFunc.findElements(TITLE_AND_COMMENT);
        int mainPageCommentCount = Integer.parseInt(articles.get(id).findElement(MAIN_PAGE_COMMENT_COUNT).getText().substring(1, articles.get(id).findElement(MAIN_PAGE_COMMENT_COUNT).getText().length() - 1));
        return mainPageCommentCount;
    }
    public void goToArticleCommentById(int id){
        List<WebElement>titles = baseFunc.findElements(TITLE_AND_COMMENT);
        baseFunc.click(titles.get(id).findElement(MAIN_PAGE_COMMENT_COUNT));
        //titles.get(id).findElement(MAIN_PAGE_COMMENT_COUNT).click();
    }

}

