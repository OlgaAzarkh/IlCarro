package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Car {
    private String city;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private Integer seats;
    private String carClass;
    private String serialNumber;
    private Double pricePerDay;
    private String about;
    private String image;
    private boolean fillSeats;
    private boolean isFillPrice;
}
