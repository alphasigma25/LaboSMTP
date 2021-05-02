package model.prank;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final String smtpServerHost;
    private final int smtpServerPort;
    private final int numberOfGroups;
    private final String witnessToCC;

    public Config(String filename) throws IOException {
        try (InputStream input = new FileInputStream(filename)) {
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
