package model.mail;

public class Message {
    private String subject;
    private String content;

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Message(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
