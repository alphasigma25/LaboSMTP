package model.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group {
    private final Person sender;
    private final List<Person> recipients = new ArrayList<>();

    public Group(Person sender) {
        this.sender = sender;
    }

    public Group(ArrayList<Person> people) {
        int senderIndex = (int)(people.size() * Math.random());
        sender = people.get(senderIndex);

        for (int i = 0; i < people.size(); ++i) {
            if (i != senderIndex) {
                recipients.add(people.get(i));
            }
        }
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
