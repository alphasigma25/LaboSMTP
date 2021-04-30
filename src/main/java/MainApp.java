import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import model.mail.Group;
import model.mail.Message;
import model.mail.Person;
import smtp.SmtpClient;

public class MainApp {
    private static String CONFIG = "config.properties";
    private static String MESSAGES = "messages.txt";
    private static String VICTIMS = "victims.txt";

    public static void main(String[] args) throws Exception {
        Config config = new Config(CONFIG);
        ArrayList<Message> messages = readMessages(MESSAGES);
        ArrayList<Person> victims = readVictims(VICTIMS);

        SmtpClient client = new SmtpClient(config.getSmtpServerHost(), config.getSmtpServerPort());
    }

    private static ArrayList<Message> readMessages(String filename) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            ArrayList<Message> messages = new ArrayList<>();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith("Subject:")) {
                    String subject = line.substring(8).trim();

                    String content = "";
                    for (line = reader.readLine(); line != null || line.trim() != "---"; line = reader.readLine()) {
                        content += line;
                    }
                    messages.add(new Message(subject, content.toString().stripTrailing()));
                }
            }

            return messages;
        }
    }

    private static ArrayList<Person> readVictims(String filename) throws Exception {
        try (BufferedReader emailsReader = new BufferedReader(new FileReader(VICTIMS))){
            ArrayList<Person> victims = new ArrayList<>();

            for (String email = emailsReader.readLine(); email != null; email = emailsReader.readLine()) {
                victims.add(new Person(email.trim()));
            }

            return victims;
        }
    }
}

class Config {
    private final String smtpServerHost;
    private final int smtpServerPort;
    private final int numberOfGroups;
    private final String witnessToCC;

    public Config(String filename) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties prop = new Properties();
            prop.load(input);

            this.smtpServerHost = prop.getProperty("smtpServerHost");
            this.smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            this.numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            this.witnessToCC = prop.getProperty("witnessToCC");
        }
    }

    public String getSmtpServerHost() {
        return smtpServerHost;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public String getWitnessToCC() {
        return witnessToCC;
    }
}
