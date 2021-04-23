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
}
