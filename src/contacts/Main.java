package contacts;

import contacts.contacts.Contact;
import contacts.menu.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Menu implements Serializable {
    private static final Scanner scanner = new Scanner(System.in);
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        List<Contact> listOfContacts;

        try {
            System.out.println("Opening " + fileName);
            listOfContacts = (List<Contact>) deserialize();
        } catch (Exception e) {
            System.out.println("Database not found. Creating new database \"phonebook.db\"...");
            listOfContacts = new ArrayList<>();
        }

        while(true) {
            System.out.println("\nEnter action (add, list, search, count, exit):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    addContact(listOfContacts);
                    break;
                case "list":
                    listOfContacts(listOfContacts);
                    break;
                case "search":
                    searchActionsMenu(listOfContacts);
                    break;
                case "count":
                    System.out.println("The Phone Book has " + listOfContacts.size() + " records.\n");
                    break;
                case "exit":
                    try {
                        System.out.println("Saving contacts to " + fileName);
                        serialize(listOfContacts);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    scanner.close();
                    return;
            }
        }
    }
}
