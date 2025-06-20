package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SignUpPage;
import utils.HeaderMenuItem;
import utils.RandomUtils;

public class SignUpTests extends ApplicationManager {
    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        signUpPage = BasePage.clickButtonsOnHeader(HeaderMenuItem.SIGN_UP);
    }

    @Test
    public void signUpPositiveTest() {
        UserLombok user = UserLombok.builder()
                .firstName("Sherlock2")
                .lastName("Holmes")
                .username(RandomUtils.generateEmail(6))
                .password("Test777@")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));
    }

    @Test
    public void signUpNegativeTestWithoutCheckbox() {
        UserLombok user = UserLombok.builder()
                .firstName("Sherlock2")
                .lastName("Holmes")
                .username(RandomUtils.generateEmail(6))
                .password("Test777@")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickBtnYalla();
        Assert.assertFalse(signUpPage.btnYallaIsEnabled());
    }

    @Test
    public void signUpNegativeTestWithoutName() {
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("Holmes")
                .username(RandomUtils.generateEmail(6))
                .password("Test777@")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.missingFieldValueErrorInline("Name is required"), "signUpNegativeTestWithoutName failed");
    }

    @Test
    public void signUpNegativeTestWithoutLastName() {
        UserLombok user = UserLombok.builder()
                .firstName("Sherlock")
                .lastName("")
                .username(RandomUtils.generateEmail(6))
                .password("Test777@")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.missingFieldValueErrorInline("Last name is required"), "signUpNegativeTestWithoutLastName failed");
    }

    @Test
    public void signUpNegativeTestWithoutEmail() {
        UserLombok user = UserLombok.builder()
                .firstName("Sherlock")
                .lastName("Holmes")
                .username("")
                .password("Test777@")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.missingFieldValueErrorInline("Email is required"), "signUpNegativeTestWithoutLastName failed");
    }

    @Test
    public void signUpNegativeTestWithoutPassword() {
        UserLombok user = UserLombok.builder()
                .firstName("Sherlock")
                .lastName("Holmes")
                .username(RandomUtils.generateEmail(6))
                .password("")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.missingFieldValueErrorInline("Password is required"), "signUpNegativeTestWithoutLastName failed");
    }



}
