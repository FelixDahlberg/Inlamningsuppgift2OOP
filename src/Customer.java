import java.time.LocalDate;

public class Customer {
    private String socialSecurityNumber;
    private String name;
    private LocalDate purchaseDateOfMembership;

    public Customer(String socialSecurityNumber, String name, LocalDate purchaseDateOfMembership) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.purchaseDateOfMembership = purchaseDateOfMembership;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPurchaseDateOfMembership() {
        return purchaseDateOfMembership;
    }

    public void setPurchaseDateOfMembership(LocalDate purchaseDateOfMembership) {
        this.purchaseDateOfMembership = purchaseDateOfMembership;
    }
}
