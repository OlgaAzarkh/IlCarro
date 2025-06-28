package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Fuel;

import java.io.File;
import java.util.Map;

public class LetCarWorkPage extends BasePage{
        public LetCarWorkPage(WebDriver driver) {
            setDriver(driver);
            PageFactory.initElements(
                    new AjaxElementLocatorFactory(driver, 10), this);
        }

    @FindBy(id = "pickUpPlace")
    WebElement city;
    @FindBy(id = "make")
    WebElement manufacture;
    @FindBy(id = "model")
    WebElement model;
    @FindBy(xpath = "//button[@class='dismissButton']")
    WebElement googleMapsBtnOk;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//input[@type='file']")
    WebElement inputFile;
    @FindBy(xpath = "//div[text()=' Wrong address ']")
    WebElement wrongAddressMessage;
    @FindBy(xpath = "//div[text()=' Make is required ']")
    WebElement manufactureIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Model is required ']")
    WebElement modelIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Fuel is required ']")
    WebElement fuelIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Number of seats is required ']")
    WebElement numberOfSeatsIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Car class is required ']")
    WebElement clasIsRequiredMessage;
    @FindBy(xpath = "//div[text()='Car registration number is required']")
    WebElement carRegistrationIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Price is required ']")
    WebElement priceIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Year required ']")
    WebElement yearIsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Wrong year ']")
    WebElement wrongYearMessage;
    @FindBy(xpath = "//div[text()=' Car must have min 2 seat ']")
    WebElement carMustHaveMinSeatsMessage;
    @FindBy(xpath = "//div[text()=' Price must be positive ']")
    WebElement priceMustBePositiveMessage;


    public void typeAddCarForm(Car car) {
        city.sendKeys(car.getCity());
        //googleMapsBtnOk.click();
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        selectFuel.sendKeys(car.getFuel());
        typeFuel(car.getFuel());
        if (car.getSeats() == -1) {
            inputSeats.sendKeys(""); // вводим ПУСТО
        } else {
            inputSeats.sendKeys(car.getSeats().toString()); // обычный ввод
        }
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        if (car.isFillPrice()) {
            if (car.getPricePerDay() != null) {
                inputPrice.sendKeys(car.getPricePerDay() + "");
            } else {
                inputPrice.sendKeys("");
            }
        }
        inputPrice.sendKeys(car.getPricePerDay() + "");
        addPhoto(car.getImage());
        submitButton.click();
    }

    private void addPhoto(String fileName) {
        inputFile.sendKeys(
                new File("src/test/resources/photos/"
                        + File.separator + fileName).getAbsolutePath());

    }

    private void typeFuel(String fuel) {
        if (fuel != null && !fuel.isEmpty()) {
            Select select = new Select(selectFuel);
            select.selectByValue(fuel);
        }
    }

    public boolean isSubmitButtonEnabled() {
        return elementIsEnabled(submitButton);
    }

    public boolean isWrongAddressMessageVisible() {
        return isElementPresent(wrongAddressMessage);
    }

    public boolean isManufactureRequiredMessageVisible() {
        return isElementPresent(manufactureIsRequiredMessage);
    }

    public boolean isModelRequiredMessageVisible() {
        return isElementPresent(modelIsRequiredMessage);
    }

    public boolean isFuelRequiredMessageVisible() {
        return isElementPresent(fuelIsRequiredMessage);
    }

    public boolean isNumberOfSeatsRequiredMessageVisible() {
        return isElementPresent(numberOfSeatsIsRequiredMessage);
    }

    public boolean isClassRequiredMessageVisible() {
        return isElementPresent(clasIsRequiredMessage);
    }

    public boolean isCarRegistrationRequiredMessageVisible() {
        return isElementPresent(carRegistrationIsRequiredMessage);
    }

    public boolean isPriceRequiredMessageVisible() {
        return isElementPresent(priceIsRequiredMessage);
    }

    public boolean isYearRequiredMessageVisible() {
        return isElementPresent(yearIsRequiredMessage);
    }

    public boolean isWrongYearMessageVisible() {
        return isElementPresent(wrongYearMessage);
    }

    public boolean isMinSeatsMessageVisible() {
        return isElementPresent(carMustHaveMinSeatsMessage);
    }

    public boolean isPriceMustBePositiveMessageVisible() {
        return isElementPresent(priceMustBePositiveMessage);
    }

}
