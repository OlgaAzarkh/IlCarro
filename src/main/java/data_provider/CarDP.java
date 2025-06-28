package data_provider;

import dto.Car;
import org.testng.annotations.DataProvider;
import utils.Fuel;
import utils.RandomUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarDP {

    @DataProvider
    public Car[] addNewCarDP() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("DP Car")
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

        Car car2 = Car.builder()
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

        Car car3 = Car.builder()
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

        return new Car[]{car, car2, car3};
    }

    @DataProvider
    public Iterator<Car> addNewCarDPFile() {
        List<Car> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/data_provider/data_car.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Car.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.valueOf(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.valueOf(splitArray[8]))
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Car> addNewCarDPFileNegativeTests() {
        List<Car> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/data_provider/data_car_negative.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Car.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.valueOf(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.valueOf(splitArray[8]))
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }



}
