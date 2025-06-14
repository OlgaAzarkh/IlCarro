package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import utils.RandomUtils;

public class LogInTests extends ApplicationManager {
    HomePage homePage;
    LogInPage logInPage;

    @BeforeMethod
    public void goToLoginPage() {
       homePage = new HomePage(getDriver());
       homePage.clickBtnLoginHeader();
        logInPage = new LogInPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LogInPage logInPage = new LogInPage(getDriver());
        logInPage.fillEmailForm("bilbo_baggins_12345@mail.com", "Password123Dr");
        logInPage.clickButtonYalla();
    }

    @Test
    public void loginPositiveTestLombok() {
        UserLombok userLombok = UserLombok.builder()
                .username("bilbo33_baggins_12345@mail.com")
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Logged in success"), "loginPositiveTestLombok failed");
    }

    @Test
    public void loginNegativeTestUnregUser() {
        UserLombok userLombok = UserLombok.builder()
                .username(RandomUtils.generateEmail(6))
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Login or Password incorrect"), "loginPositiveTestLombok failed");
    }

    @Test
    public void loginNegativeTestEmptyPassword() {
        UserLombok userLombok = UserLombok.builder()
                .username("bilbo33_baggins_12345@mail.com")
                .password("11")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.emptyPasswordErrorInline(), "loginNegativeTestEmptyPassword failed");
    }
}
