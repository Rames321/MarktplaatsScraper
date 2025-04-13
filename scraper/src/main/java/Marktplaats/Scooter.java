package Marktplaats;

public class Scooter {
    private String titel;
    private int prijs; // Changed to integer
    private String locatie;
    private String tijdstip;
    private String link;

    public Scooter(String titel, int prijs, String locatie, String tijdstip, String link) {
        this.titel = titel;
        this.prijs = prijs;
        this.locatie = locatie;
        this.tijdstip = tijdstip;
        this.link = link;
    }

    public String getTitel() {
        return titel;
    }

    public int getPrijs() {
        return prijs;
    }

    public String getLocatie() {
        return locatie;
    }

    public String getTijdstip() {
        return tijdstip;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Scooter{titel='" + titel + "', prijs=" + prijs + ", locatie='" + locatie + "', tijdstip='" + tijdstip + "', link='" + link + "'}";
    }
}