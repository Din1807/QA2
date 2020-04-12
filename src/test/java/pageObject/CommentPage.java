package pageObject;

import org.openqa.selenium.By;

public class CommentPage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");

    private final By ANON_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[1]");
    private final By REG_COMMENT_COUNT = By.xpath("(.//span[contains(@class, 'type-cnt')])[2]");
    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public int getAnonCom() {
        int anonCommentCount = Integer.parseInt(baseFunc.findElement(ANON_COMMENT_COUNT).getText().substring(1, baseFunc.findElement(ANON_COMMENT_COUNT).getText().length() - 1));
        return anonCommentCount;
    }
    public int getRegCom() {
        int regCommentCount = Integer.parseInt(baseFunc.findElement(REG_COMMENT_COUNT).getText().substring(1, baseFunc.findElement(REG_COMMENT_COUNT).getText().length() - 1));
        return regCommentCount;
    }
}
