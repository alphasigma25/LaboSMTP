package smtp;

import model.mail.Group;
import model.mail.Message;
import model.mail.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SmtpClientTest {

    private static SmtpClient client;

    @BeforeAll
    public static void init(){
        client = new SmtpClient("localhost", 2525);
    }

    @Test
    public void test1(){
        Group g = new Group(new Person("anne.ganguillet@heig-vd.ch"));
        g.addRecipient(new Person("anne.ganguillet@heig-vd.ch"));
        client.sendMessageToGroup(new Message("Hello","Hello hello"), g);
        Assertions.assertNotEquals(2,3);
    }

}