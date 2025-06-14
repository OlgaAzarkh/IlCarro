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

    @Test(description = "Positive login with hardcoded credentials")
    public void loginPositiveTest() {
        logInPage.fillEmailForm("bilbo_baggins_12345@mail.com", "Password123!");
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Logged in success"), "loginPositiveTestLombok failed");
    }

    @Test(description = "Positive login using Lombok user model")
    public void loginPositiveTestLombok() {
        UserLombok userLombok = UserLombok.builder()
                .username("bilbo33_baggins_12345@mail.com")
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Logged in success"), "loginPositiveTestLombok failed");
    }

    @Test(description = "Negative login test with unregistered user")
    public void loginNegativeTestUnregUser() {
        UserLombok userLombok = UserLombok.builder()
                .username(RandomUtils.generateEmail(6))
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Login or Password incorrect"), "loginPositiveTestLombok failed");
    }

    @Test(description = "Negative login test with empty password")
    public void loginNegativeTestEmptyPassword() {
        UserLombok userLombok = UserLombok.builder()
                .username("bilbo33_baggins_12345@mail.com")
                .password("")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.invalidFieldValueErrorInline(logInPage.emptyPasswordErrorInline),
                "loginNegativeTestEmptyPassword failed: expected inline error was not displayed");
    }

    @Test(description = "Negative login test with empty email")
    public void loginNegativeTestEmptyEmail() {
        UserLombok userLombok = UserLombok.builder()
                .username("")
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.invalidFieldValueErrorInline(logInPage.emptyEmailErrorInline), "loginNegativeTestEmptyEmail failed");
    }

    @Test(description = "Negative login test with invalid email format")
    public void loginNegativeTestWithInvalidEmail() {
        UserLombok userLombok = UserLombok.builder()
                .username(RandomUtils.generateString(6))
                .password("Password123!")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.invalidFieldValueErrorInline(logInPage.invalidEmailFormatInlineMessage),"loginNegativeTestWithInvalidEmail failed");
    }

    @Test(description = "Negative login test with invalid password")
    public void loginNegativeTestWithInvalidPassword() {
        UserLombok userLombok = UserLombok.builder()
                .username("bilbo33_baggins_12345@mail.com")
                .password("???")
                .build();
        logInPage.fillEmailForm(userLombok.getUsername(), userLombok.getPassword());
        logInPage.clickButtonYalla();
        Assert.assertTrue(logInPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTestWithInvalidPassword");
    }
}
