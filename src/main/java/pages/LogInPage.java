package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogInPage extends BasePage {

    public LogInPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    public void fillEmailForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement yallaButton;

    public void clickButtonYalla() {
        yallaButton.click();
    }

    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpMessage;


    public boolean validatePopUpMessage(String text) {
        return isTextInElementPresent(popUpMessage, text);
    }

    @FindBy(xpath = "//div[text()=' Password is required ']")
    WebElement emptyPasswordErrorInline;

    public boolean emptyPasswordErrorInline() {
        return isElementPresent(emptyPasswordErrorInline);
    }
}


