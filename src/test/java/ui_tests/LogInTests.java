package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LogInPage logInPage = new LogInPage(getDriver());
        logInPage.fillEmailForm("bilbo_baggins_12345@mail.com", "Password123Dr");
        logInPage.clickButtonYalla();
    }
}
