package Marktplaats;

public class Notificatie {
    private String message;
    private String recipient;

    public Notificatie(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return "Notificatie{message='" + message + "', recipient='" + recipient + "'}";
    }
}