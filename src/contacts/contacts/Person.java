package contacts.contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String surname, String birthDate, String gender, String phoneNumber) {
        super(name, phoneNumber);
        this.surname = surname;
        this.birthDate = correctBirthDate(birthDate) ? birthDate : "[no data]";
        this.gender = correctGender(gender) ? gender : "[no data]";
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getBirthDate() { return birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public static boolean correctBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean correctGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Birth date: " + getBirthDate() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getCreateDate().toString().substring(0,16) + "\n" +
                "Time last edit: " + getLastEditDate().toString().substring(0,16) + "\n";
    }
}
