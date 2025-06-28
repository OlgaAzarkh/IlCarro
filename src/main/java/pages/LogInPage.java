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

    public void fillLoginForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement yallaButton;

    public void clickButtonYalla() {
        yallaButton.click();
    }

    @FindBy(xpath = "//div[text()=' Password is required ']")
    public WebElement emptyPasswordErrorInline;

    @FindBy(xpath = "//div[text()=' Email is required ']")
    public WebElement emptyEmailErrorInline;

    public boolean invalidFieldValueErrorInline(WebElement element) {
        return isElementPresent(element);
    }

    @FindBy(xpath = "//div[text()=\"It'snot look like email\"]")
    public WebElement invalidEmailFormatInlineMessage;
}


