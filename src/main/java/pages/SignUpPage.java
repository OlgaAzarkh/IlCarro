package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class SignUpPage extends BasePage{
        public SignUpPage(WebDriver driver) {
            setDriver(driver);
            PageFactory.initElements(
                    new AjaxElementLocatorFactory(driver, 10), this);
        }

    @FindBy(id = "name")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement yallaButton;
    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> messageErrorNameList;

    public void typeSignUpForm(UserLombok userLombok) {
        inputName.sendKeys(userLombok.getFirstName());
        inputLastName.sendKeys(userLombok.getLastName());
        inputEmail.sendKeys(userLombok.getUsername());
        inputPassword.sendKeys(userLombok.getPassword());
    }

    public void clickCheckBox() {
        int width = checkBox.getRect().getWidth();
        int height = checkBox.getRect().getHeight();
        System.out.println(width + "X" + height);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox, -width/20*9,0).click().perform();
    }

    public void clickBtnYalla() {
        yallaButton.click();
    }

    public boolean btnYallaIsEnabled() {
        return elementIsEnabled(yallaButton);
    }

    public boolean missingFieldValueErrorInline(String text) {
        for (WebElement e : messageErrorNameList) {
            e.getText();
            if (e.getText().contains(text))
                return true;
        }
        return false;
    }

}
