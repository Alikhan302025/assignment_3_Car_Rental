package model;
import java.time.LocalDate;
public class Rental extends BaseEntity {

    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public Rental(int id,
                  Customer customer,
                  Car car,
                  LocalDate startDate,
                  LocalDate endDate,
                  String status) {

        super(id, "Rental");
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        //validate();

    }
    @Override
    public String getEntityType() {
        return "Rental";
    }

    @Override
    public void validate(){
        if (customer == null){
            throw new IllegalArgumentException("Customer is required");
        }
        if (car == null){
            throw new IllegalArgumentException("Car is required");
        }
        if (startDate == null){
            throw new IllegalArgumentException("Start date is required");

        }
    }

    public Customer getCustomer() {
        return customer;
    }
    public Car getCar(){
        return car;
    }
    public String getStatus(){
        return status;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        this.status = status;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.endDate = endDate;
    }
}
