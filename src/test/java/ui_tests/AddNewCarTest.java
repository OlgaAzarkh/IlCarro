package ui_tests;

import data_provider.CarDP;
import dto.Car;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LogInPage;
import utils.Fuel;
import utils.HeaderMenuItem;
import utils.RandomUtils;

public class AddNewCarTest extends ApplicationManager {
    LogInPage logInPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver());
        logInPage = BasePage.clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        logInPage.fillLoginForm("bilbo33_baggins_12345@mail.com", "Password123!");
        letCarWorkPage = BasePage.clickButtonsOnHeader(HeaderMenuItem.LET_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
    }

    @Test(dataProvider = "addNewCarDP", dataProviderClass = CarDP.class)
    public void addNewCarPositiveTestDataProvider(Car car) {
        letCarWorkPage.typeAddCarForm(car);
    }

    @Test(dataProvider = "addNewCarDPFile", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest(Car car) {
        logger.info("test data --->" + car);
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptyCity() {
        Car car = Car.builder()
                .city("")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isWrongAddressMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptyManufacture() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isManufactureRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptyModel() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isWrongAddressMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptyYear() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isYearRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWrongYear() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("-1")
                .fuel(Fuel.DIESEL.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isWrongYearMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptyFuel() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.UNKNOWN.getValue())
                .seats(4)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isFuelRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestEmptySeats() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(-1)
                .fillSeats(false)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isNumberOfSeatsRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWithSeatsLessThanMinimum() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(1)
                .fillSeats(false)
                .carClass("economy")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isMinSeatsMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWithEmptyCarClass() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(2)
                .fillSeats(false)
                .carClass("")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isClassRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWithEmptyCarRegistrationNumber() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(2)
                .fillSeats(false)
                .carClass("A")
                .serialNumber("")
                .pricePerDay(25.5)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isCarRegistrationRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWithEmptyPrice() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(2)
                .fillSeats(false)
                .carClass("A")
                .serialNumber(RandomUtils.generateString(6))
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isPriceRequiredMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }

    @Test
    public void addNewCarNegativeTestWithNegativePrice() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("4534")
                .model("Astra")
                .year("2025")
                .fuel(Fuel.DIESEL.getValue())
                .seats(2)
                .fillSeats(false)
                .carClass("A")
                .serialNumber(RandomUtils.generateString(6))
                .pricePerDay(-1.3)
                .about("about")
                .image("IMG_0467.PNG")
                .build();
        letCarWorkPage.typeAddCarForm(car);
        Assert.assertTrue(letCarWorkPage.isPriceMustBePositiveMessageVisible());
        Assert.assertFalse(letCarWorkPage.isSubmitButtonEnabled());
    }
}
