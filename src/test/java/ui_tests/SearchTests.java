package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;

public class SearchTests extends ApplicationManager {

    @Test
    public void searchPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchForm("Haifa", LocalDate.of(2025, 7,25),
                LocalDate.of(2025,8,25));
    }
}
