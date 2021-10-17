package contacts.menu;

import contacts.contacts.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportAndExport implements Serializable {
    protected static final String fileName = "phonebook.db";

    protected static void serialize(List<Contact> listOfContacts) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(listOfContacts);
        oos.close();
    }

    protected static Object deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();

        if (obj instanceof List<?>) {
            return obj;
        } else {
            return new ArrayList<>();
        }
    }
}
