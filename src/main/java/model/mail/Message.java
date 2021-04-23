package model.mail;

public class Message {
    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    private String subject;
    private String content;

    public Message(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
