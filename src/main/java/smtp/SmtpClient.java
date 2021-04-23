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

    private String EHLOName = "TODO";

    public SmtpClient(String host, int port){
        this.host = host;
        this.port = port;
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

            while (!response.split(" ")[0].equals("220")) {
                response = reader.readLine();
                System.out.println(response);
            }
            System.out.println("EHLO " + EHLOName);
            writer.println("EHLO " + EHLOName);
            response = reader.readLine();
            System.out.println(response);

            Iterator<Person> personIterator = grp.recipients();
            while(personIterator.hasNext()) {
                Person p = personIterator.next();
                writer.println("MAIL FROM: " + grp.getSender().getEmail());
                reader.readLine();
                writer.println("MAIL TO: " + p.getEmail());
                reader.readLine();
                writer.println("DATA");
                reader.readLine();
                writer.println("From: " + grp.getSender().getEmail()); //false e-mail ?
                writer.println("To: " + p.getEmail());
                writer.println("Subject: " + msg.getSubject());
                writer.println(msg.getContent());
                writer.println(".");
                writer.println();
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
