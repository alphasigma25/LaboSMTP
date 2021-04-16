package model.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group {
    private Person sender;
    private List<Person> recipients = new ArrayList<>();

    public Group(Person sender) {
        this.sender = sender;
    }

    public Person getSender() {
        return sender;
    }

    public void addRecipient(Person recipient) {
        recipients.add(recipient);
    }

    public Iterator<Person> recipients() {
        return recipients.iterator();
    }
}
