package model;

public class Car extends BaseEntity implements PricedItem, Printable {
    private String brand;
    private String model;
    private int year;
    private int dailyPrice;
    private int branchId;

    public Car(int id, String brand, String model, int year, int dailyPrice, int branchId) {
        super(id, brand + " " + model);
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.branchId = branchId;
        validate();
    }
    @Override
    public String toPrint(){
        return "Car id= " + getId() + ", brand= " + getBrand() + ", model=" + getModel() + ",year=" + getYear() + ", dayliPrice=" + getDailyPrice();
    }

    @Override
    public String getEntityType(){
        return "Car";
    }

    @Override
    public void validate() {
        if (dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be positive");
        }
        if (year < 1990) {
            throw new IllegalArgumentException("Invalid car year");
        }
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setDailyPrice(int dailyPrice) {
        if (dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be positive");
        }
        this.dailyPrice = dailyPrice;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}



