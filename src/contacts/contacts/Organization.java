package contacts.contacts;

import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String address;

    public Organization(String name, String address, String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getCreateDate().toString().substring(0,16) + "\n" +
                "Time last edit: " + getLastEditDate().toString().substring(0,16) + "\n";
    }
}
