package model.prank;

import model.mail.Group;
import model.mail.Message;
import model.mail.Person;
import smtp.SmtpClient;

import java.util.ArrayList;

public class PrankGenerator {
    private final Config config;
    private final ArrayList<Message> messages;
    private final ArrayList<Person> victims;

    public PrankGenerator(Config config, ArrayList<Message> messages, ArrayList<Person> victims){
        if (config.getNumberOfGroups() * 3 > victims.size()) {
            throw new IllegalArgumentException("Il y a moins de 3 personnes par groupe");
        }

        this.config = config;
        this.messages = messages;
        this.victims = victims;
    }

    public void sendPrank(){
        ArrayList<Person>[] groups = new ArrayList[config.getNumberOfGroups()];
        for(int i = 0; i<groups.length; ++i){
            groups[i] = new ArrayList<>();
        }
        for(int i = 0; i < victims.size(); ++i){
            groups[i % config.getNumberOfGroups()].add(victims.get(i));
        }

        Group[] realGroups = new Group[config.getNumberOfGroups()];

        for(int i = 0; i < groups.length; ++i){
            realGroups[i] = new Group(groups[i]);
        }

        SmtpClient client = new SmtpClient(config.getSmtpServerHost(), config.getSmtpServerPort(),
                config.getWitnessToCC());
        for(Group group : realGroups) {
            Message messageToSend = messages.get((int)(Math.random() * messages.size()));
            client.sendMessageToGroup(messageToSend, group);
        }
    }
}
