import java.io.*;
import java.util.ArrayList;

import model.mail.Message;
import model.mail.Person;
import model.prank.Config;
import model.prank.PrankGenerator;

public class MainApp {
    private static String CONFIG = "./resources/config.properties";
    private static String MESSAGES = "./resources/messages.txt";
    private static String VICTIMS = "./resources/victims.txt";

    public static void main(String[] args) throws Exception {
        new PrankGenerator(
                new Config(CONFIG),
                readMessages(MESSAGES),
                readVictims(VICTIMS)
        ).sendPrank();
    }

    private static ArrayList<Message> readMessages(String filename) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            ArrayList<Message> messages = new ArrayList<>();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.toLowerCase().startsWith("subject:")) {
                    String subject = line.substring(8).trim();

                    StringBuilder content = new StringBuilder();
                    for (line = reader.readLine(); line != null && !line.trim().equals("---"); line = reader.readLine()) {
                        content.append(line);
                        content.append("\n");
                    }
                    messages.add(new Message(subject, content.toString().stripTrailing()));
                }
            }
            return messages;
        }
    }

    private static ArrayList<Person> readVictims(String filename) throws Exception {
        try (BufferedReader emailsReader = new BufferedReader(new FileReader(filename))){
            ArrayList<Person> victims = new ArrayList<>();

            for (String email = emailsReader.readLine(); email != null; email = emailsReader.readLine()) {
                victims.add(new Person(email.trim()));
            }

            return victims;
        }
    }
}

