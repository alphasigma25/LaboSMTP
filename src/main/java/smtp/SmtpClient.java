package smtp;

import model.mail.Group;
import model.mail.Message;
import model.mail.Person;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

public class SmtpClient {

    private final String host;
    private final int port;
    private final String witnessToCC;

    private static final String EHLOName = "res_2021";

    public SmtpClient(String host, int port, String witnessToCC){
        this.host = host;
        this.port = port;
        this.witnessToCC = witnessToCC;
    }

    public void sendMessageToGroup(Message msg, Group grp){
        Socket clientSocket = null;
        OutputStream os = null;
        InputStream is = null;

        String response = "";

        try{
            clientSocket = new Socket(host, port);
            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            response = reader.readLine();
            System.out.println(response);

            System.out.println("EHLO " + EHLOName);
            writer.println("EHLO " + EHLOName);
            writer.flush();
            response = reader.readLine();
            System.out.println(response);
            while (!response.startsWith("250 ")) {
                response = reader.readLine();
                System.out.println(response);
            }

            Iterator<Person> personIterator = grp.recipients();
            while(personIterator.hasNext()) {
                Person p = personIterator.next();
                writer.println("MAIL FROM: " + grp.getSender().getEmail());
                writer.flush();
                reader.readLine();
                writer.println("RCPT TO: " + p.getEmail());
                writer.flush();
                reader.readLine();
                if(witnessToCC != null){
                    writer.println("RCPT TO: " + witnessToCC);
                    writer.flush();
                    reader.readLine();
                }
                writer.println("DATA");
                writer.flush();
                reader.readLine();
                writer.println("From: " + grp.getSender().getEmail()); //false e-mail ?
                writer.flush();
                writer.println("To: " + p.getEmail());
                writer.flush();
                if(witnessToCC != null){
                    writer.println("To: " + witnessToCC);
                    writer.flush();
                }
                writer.println("Subject: " + msg.getSubject());
                writer.flush();
                writer.println();
                writer.flush();
                writer.println(msg.getContent());
                writer.flush();
                writer.println(".");
                writer.flush();
                writer.println();
                writer.flush();
                reader.readLine();
            }

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }finally{
            try {
                is.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {
                os.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {
                clientSocket.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
