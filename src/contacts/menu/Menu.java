package contacts.menu;

import contacts.contacts.Contact;
import contacts.contacts.Organization;
import contacts.contacts.Person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu extends ImportAndExport implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Scanner scanner = new Scanner(System.in);

    protected static void addContact(List<Contact> listOfContacts) {
        System.out.println("Enter the type (person, organization):");
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "person":
                addPerson(listOfContacts);
                break;
            case "organization":
                addOrganization(listOfContacts);
                break;
        }
    }

    private static void addPerson(List<Contact> listOfContacts) {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();

        System.out.println("Enter the birth date:");
        String birthDate = scanner.nextLine();
        if (!Person.correctBirthDate(birthDate)) {
            System.out.println("Bad birth date!");
        }

        System.out.println("Enter the gender (M, F):");
        String gender = scanner.nextLine().toUpperCase();
        if (!Person.correctGender(gender)) {
            System.out.println("Bad gender!");
        }

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Person person = new Person(name, surname, birthDate, gender, phoneNumber);
        listOfContacts.add(person);

        System.out.println("The record added.\n");
    }

    private static void addOrganization(List<Contact> listOfContacts) {
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();

        System.out.println("Enter the address:");
        String address = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Organization organization = new Organization(name, address, phoneNumber);
        listOfContacts.add(organization);

        System.out.println("The record added.\n");
    }

    private static void removeContact(List<Contact> listOfContacts, int index) {
        if (listOfContacts.size() > 0) {
            listOfContacts.remove(index - 1);
            System.out.println("The record removed!\n");
        } else {
            System.out.println("No records to remove!\n");
        }
    }

    private static void editContact(List<Contact> listOfContacts, int index) {
        if (listOfContacts.size() > 0) {
            if (listOfContacts.get(index - 1) instanceof Person) {
                Person person = (Person) listOfContacts.get(index - 1);
                System.out.println("Select a field (name, surname, birth, gender, number):");
                editPerson(person);
            } else if (listOfContacts.get(index - 1) instanceof Organization) {
                Organization organization = (Organization) listOfContacts.get(index - 1);
                System.out.println("Select a field (name, address, number):");
                editOrganization(organization);
            }
        } else {
            System.out.println("No records to edit!\n");
        }
    }

    private static void editPerson(Person person) {
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "name":
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                person.setName(name);
                person.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "surname":
                System.out.println("Enter surname:");
                String surname = scanner.nextLine();
                person.setSurname(surname);
                person.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "birth":
                System.out.println("Enter birth date:");
                String birthDate = scanner.nextLine();
                person.setBirthDate(birthDate);
                person.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "gender":
                System.out.println("Enter gender:");
                String gender = scanner.nextLine().toUpperCase();
                person.setGender(gender);
                person.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "number":
                System.out.println("Enter number:");
                String number = scanner.nextLine();
                person.setPhoneNumber(number);
                person.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
        }
    }

    private static void editOrganization(Organization organization) {
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "name":
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                organization.setName(name);
                organization.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "address":
                System.out.println("Enter address:");
                String address = scanner.nextLine();
                organization.setAddress(address);
                organization.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
            case "number":
                System.out.println("Enter number:");
                String number = scanner.nextLine();
                organization.setPhoneNumber(number);
                organization.setLastEditDate(LocalDateTime.now());
                System.out.println("The record updated!\n");
                break;
        }
    }

    private static void showListOfContacts(List<Contact> listOfContacts) {
        for (int i = 0; i < listOfContacts.size(); i++) {
            if (listOfContacts.get(i) instanceof Person) {
                Person person = (Person) listOfContacts.get(i);
                System.out.println((i + 1) + ". " + person.getName() + " " + person.getSurname());
            } else if (listOfContacts.get(i) instanceof Organization) {
                Organization organization = (Organization) listOfContacts.get(i);
                System.out.println((i + 1) + ". " + organization.getName());
            }
        }
    }

    private static void showDetailsOfContact(List<Contact> listOfContacts, int index) {
        if (listOfContacts.size() > 0) {
            if (listOfContacts.get(index - 1) instanceof Person) {
                Person person = (Person) listOfContacts.get(index - 1);
                System.out.println(person.toString());
            } else if (listOfContacts.get(index - 1) instanceof Organization) {
                Organization organization = (Organization) listOfContacts.get(index - 1);
                System.out.println(organization.toString());
            }
        } else {
            System.out.println("No records to show!\n");
        }
    }

    private static void contactActionsMenu(List<Contact> listOfContacts, int index) {
        while (true) {
            System.out.println("[record] Enter action (edit, delete, menu):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "edit":
                    editContact(listOfContacts, index);
                    break;
                case "delete":
                    removeContact(listOfContacts, index);
                    return;
                case "menu":
                    return;
            }
        }
    }

    protected static void listOfContacts(List<Contact> listOfContacts) {
        showListOfContacts(listOfContacts);

        if (listOfContacts.size() > 0) {
            System.out.println("[list] Enter action ([number], back):");
            String action = scanner.nextLine().toLowerCase();

            if ("back".equals(action)) {
                return;
            } else {
                try {
                    int index = Integer.parseInt(action);
                    showDetailsOfContact(listOfContacts, index);
                    contactActionsMenu(listOfContacts, index);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("No records to show!\n");
        }
    }

    private static List<Contact> search(List<Contact> listOfContacts) {
        List<Contact> listOfSearchContacts = new ArrayList<>();
        System.out.println("Enter search query:");
        String search = scanner.nextLine();
        Pattern p = Pattern.compile(search, Pattern.CASE_INSENSITIVE);

        for (Contact contact : listOfContacts) {
            if (contact instanceof Person) {
                Matcher m = p.matcher(contact.getName() + " " + ((Person) contact).getSurname() + " " + contact.getPhoneNumber());
                if (m.find()) {
                    listOfSearchContacts.add(contact);
                }
            } else if (contact instanceof Organization) {
                Matcher m = p.matcher(contact.getName() + " " + ((Organization) contact).getAddress() + " " + contact.getPhoneNumber());
                if (m.find()) {
                    listOfSearchContacts.add(contact);
                }
            }
        }

        System.out.println("Found " + listOfSearchContacts.size() + " results.");
        showListOfContacts(listOfSearchContacts);
        return listOfSearchContacts;
    }

    protected static void searchActionsMenu(List<Contact> listOfContacts) {
        List<Contact> listOfSearchContacts = search(listOfContacts);
        System.out.println("\n[search] Enter action ([number], back, again):");
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "back":
                return;
            case "again":
                searchActionsMenu(listOfContacts);
                break;
            default:
                try {
                    int index = Integer.parseInt(action);
                    showDetailsOfContact(listOfSearchContacts, index);
                    contactActionsMenu(listOfSearchContacts, index);
                    // connecting both lists
                    for (Contact contact : listOfContacts) {
                        for (Contact searchContact : listOfSearchContacts) {
                            if (contact.getID().equals(searchContact.getID())) {
                                if (searchContact instanceof Person) {
                                    contact = new Person(searchContact.getName(), ((Person) searchContact).getSurname(), ((Person) searchContact).getBirthDate(), ((Person) searchContact).getGender(), searchContact.getPhoneNumber());
                                } else if (searchContact instanceof Organization) {
                                    contact = new Organization(searchContact.getName(), ((Organization) searchContact).getAddress(), searchContact.getPhoneNumber());
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
