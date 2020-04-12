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
    }

    public void goToArticleById(){
    }
    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }

}
