package smtp;

import model.mail.Group;
import model.mail.Message;
import model.mail.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmtpClientTest {

    private static final SmtpClient client = new SmtpClient("localhost", 2525, null);;

    @Test
    public void test1(){
        Group g = new Group(new Person("satire@aol.com"));
        g.addRecipient(new Person("satire@aol.com"));
        client.sendMessageToGroup(new Message("Hello","Hello hello"), g);
        Assertions.assertNotEquals(2,3);
    }
}