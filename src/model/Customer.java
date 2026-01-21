package model;

import java.time.LocalDate;

public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;


    public Customer(int id, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth) {
        super(id, firstName + " " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        validate();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getEntityType() {
        return "Customer";
    }

    @Override
    public void validate() {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone must not be empty");
        }
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth must not be null");
        }
    }
}






