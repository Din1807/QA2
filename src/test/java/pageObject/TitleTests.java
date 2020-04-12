package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class TitleTests {
    private BaseFunc baseFunc = new BaseFunc();
    private final Logger LOGGER = LogManager.getLogger(TitleTests.class);

    @Test
    public void titleCheck(){
        baseFunc.openHomePage();
        HomePage homePage = new HomePage(baseFunc);
        String homePageTitle = homePage.getTitleById(2);
        homePage.goToArticleById(2);
        ArticlePage articlePage = new ArticlePage(baseFunc);
        String articlePageTitle=articlePage.getTitle();
        Assertions.assertEquals(homePageTitle,articlePageTitle,"...");
        baseFunc.closeBrowser();
    }
    @Test
    public void CommentCheck(){
        LOGGER.info("Opening Delfi home page");
        baseFunc.openHomePage();
        HomePage homePage = new HomePage(baseFunc);
        int mainPageCommentCount = homePage.getTitleCommentCountById(2);
        LOGGER.info("Main page comment count was: " + mainPageCommentCount);
        homePage.goToArticleCommentById(2);
        LOGGER.info("Go to Comment Page");
        CommentPage commentPage = new CommentPage(baseFunc);
        int regComCount = commentPage.getRegCom();
        int anonComCount = commentPage.getAnonCom();
        LOGGER.info("Number of comment page comments Anon " + regComCount + " and Reg " + anonComCount + " all was: " + (regComCount + anonComCount));
        Assertions.assertEquals(mainPageCommentCount, regComCount + anonComCount, "Wrong count on comment page!");
        baseFunc.closeBrowser();




/*

        int mainPageCommentCount, articleCommentCount, anonCommentCount, regCommentCount;
        if (!articles.get(articleNum).findElements(MAIN_PAGE_COMMENT_COUNT).isEmpty()) {
            mainPageCommentCount = Integer.parseInt(articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().substring(1, articles.get(articleNum).findElement(MAIN_PAGE_COMMENT_COUNT).getText().length() - 1));
        } else {
            mainPageCommentCount = 0;
        }

        articles.get(articleNum).findElement(ARTICLE_TITLE).click();
        if (!driver.findElements(ARTICLE_COMMENT_COUNT).isEmpty()) {
            articleCommentCount = Integer.parseInt(driver.findElement(ARTICLE_COMMENT_COUNT).getText().substring(1, driver.findElement(ARTICLE_COMMENT_COUNT).getText().length() - 1));
        } else {
            articleCommentCount = 0;
        }
        LOGGER.info("Article page comment count was: " + articleCommentCount);

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
        }*/
    }

    public void goToArticleById(){
    }
    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }

}
