package Marktplaats;

public class SimpeleTest {
    public static void main(String[] args) {
        testScooterAanmaken();
        testFilterOpPrijs();
        testNotificatieGeneratie();
    }

    public static void testScooterAanmaken() {
        Scooter s = new Scooter("Zip SP", 1350, "Amsterdam", "2023-10-12 12:00", "link");
        if (
                s.getTitel().equals("Zip SP") &&
                        s.getPrijs() == 1350 &&
                        s.getLocatie().equals("Amsterdam") &&
                        s.getTijdstip().equals("2023-10-12 12:00") &&
                        s.getLink().equals("link")
        ) {
            System.out.println("[✓] Scooter aanmaken test geslaagd");
        } else {
            System.out.println("[✗] Scooter aanmaken test mislukt");
        }
    }

    public static void testFilterOpPrijs() {
        Scooter goedkoop = new Scooter("AGM VX", 1100, "Rotterdam", "2023-10-11", "link");
        Scooter duur = new Scooter("Vespa LX", 1800, "Den Haag", "2023-10-11", "link");

        int grens = 1500;
        if (goedkoop.getPrijs() < grens && !(duur.getPrijs() < grens)) {
            System.out.println("[✓] Filter op prijs test geslaagd");
        } else {
            System.out.println("[✗] Filter op prijs test mislukt");
        }
    }

    public static void testNotificatieGeneratie() {
        Notificatie n = new Notificatie("Nieuwe Zip gevonden", "admin");
        if (n.getMessage().contains("Zip") && n.getRecipient().equals("admin")) {
            System.out.println("[✓] Notificatie test geslaagd");
        } else {
            System.out.println("[✗] Notificatie test mislukt");
        }
    }
}
