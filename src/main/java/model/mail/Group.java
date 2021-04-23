package model.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group {
    private final Person sender;
    private final List<Person> recipients = new ArrayList<>();

    // TODO: Remove.
    public Group(Person sender) {
        this.sender = sender;
    }

    public Group(Person[] people) {
        int senderIndex = (int)(people.length * Math.random());
        sender = people[senderIndex];

        for (int i = 0; i < people.length; ++i) {
            if (i != senderIndex) {
                recipients.add(people[i]);
            }
        }
    }

    public Person getSender() {
        return sender;
    }

    // TODO: Remove.
    public void addRecipient(Person recipient) {
        recipients.add(recipient);
    }

    public Iterator<Person> recipients() {
        return recipients.iterator();
    }
}
