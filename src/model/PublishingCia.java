package model;

/**
 * @author cassiano
 */
public class PublishingCia {
    
    private int companyID;
    private String name;
    private Address address;

    public PublishingCia(int companyID, String name, Address address) {
        this.companyID = companyID;
        this.name      = name;
        this.address   = address;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
