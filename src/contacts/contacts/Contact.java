package contacts.contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.UUID;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String phoneNumber;
    private final LocalDateTime createDate;
    private LocalDateTime lastEditDate;
    private final String ID;

    public Contact(String name, String phoneNumber) {
        ID = UUID.randomUUID().toString();
        this.name = name;
        setPhoneNumber(phoneNumber);
        this.createDate = LocalDateTime.now();
        this.lastEditDate = createDate;
    }

    public String getID() { return ID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (correctPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "[no number]";
        }
    }

    private boolean correctPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\+?"
                + "((\\([0-9A-Za-z]+\\)|[0-9A-Za-z]+)"
                + "|([0-9A-Za-z]+[ -]\\([0-9A-Za-z]{2,}\\))|"
                + "[0-9A-Za-z]+[ -][0-9A-Za-z]{2,})"
                + "([ -][0-9A-Za-z]{2,}[ -]?)*");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public LocalDateTime getCreateDate() { return createDate; }

    public LocalDateTime getLastEditDate() { return lastEditDate; }

    public void setLastEditDate(LocalDateTime lastEditDate) { this.lastEditDate = lastEditDate; }
}
