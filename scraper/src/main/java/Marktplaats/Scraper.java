package Marktplaats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Scraper {
    private static final List<Scooter> scooterDatabase = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (currentUser == null) {
            clearScreen();
            System.out.println("==============================");
            System.out.println("=   Marktplaats ScooterBot   =");
            System.out.println("==============================");
            System.out.println("1. Registreer een account");
            System.out.println("2. Sluit programma af");
            System.out.println("==============================");
            System.out.print("Maak een keuze: ");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        clearScreen();
                        registerUser(scanner);
                        break;
                    case 2:
                        clearScreen();
                        System.out.println("Programma wordt afgesloten...");
                        scanner.close();
                        return;
                    default:
                        clearScreen();
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                clearScreen();
                System.out.println("Ongeldige invoer. Voer een nummer in.");
            }
        }

        boolean running = true;
        while (running) {
            clearScreen();
            System.out.println("==============================");
            System.out.println("Welkom, " + currentUser.getUsername() + "!");
            System.out.println("==============================");
            System.out.println("1. Start scraper (zoek advertenties)");
            System.out.println("2. Toon gevonden advertenties");
            System.out.println("3. Advertenties filteren");
            System.out.println("4. Toon actieve instellingen");
            System.out.println("5. Simuleer Discord-melding");
            System.out.println("6. Sluit programma af");
            System.out.println("==============================");
            System.out.print("Maak een keuze: ");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        clearScreen();
                        startScraper();
                        break;
                    case 2:
                        clearScreen();
                        showScooters();
                        break;
                    case 3:
                        clearScreen();
                        filterScooters(scanner);
                        break;
                    case 4:
                        clearScreen();
                        updateUserSettings(scanner);
                        break;
                    case 5:
                        clearScreen();
                        simulateDiscordNotification();
                        break;
                    case 6:
                        clearScreen();
                        System.out.println("Programma wordt afgesloten...");
                        running = false;
                        break;
                    default:
                        clearScreen();
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                }

                if (running) {
                    System.out.println("\nDruk op 'Enter' om terug te keren naar het menu...");
                    while (!scanner.nextLine().isEmpty()) {
                        clearScreen();
                        System.out.println("Ongeldige invoer. Druk op 'Enter' om door te gaan.");
                    }
                }
            } catch (NumberFormatException e) {
                clearScreen();
                System.out.println("Ongeldige invoer. Voer een nummer in.");
            }
        }
        scanner.close();
    }

    private static void registerUser(Scanner scanner) {
        clearScreen();
        System.out.print("Voer een gebruikersnaam in: ");
        String username = scanner.nextLine();
        System.out.print("Voer een e-mailadres in: ");
        String email = scanner.nextLine();

        currentUser = new User(username, email);
        clearScreen();
        System.out.println("Account succesvol aangemaakt! Welkom, " + currentUser.getUsername() + "!");
    }

    private static void startScraper() {
        clearScreen();

        String[] brands = {"Vespa", "Zip", "Peugeot", "AGM"};
        String[] locations = {"Rotterdam", "Den Haag", "Amsterdam", "Zoetermeer"};
        Random random = new Random();

        int numberOfScooters = random.nextInt(6); // Generates a number between 0 and 5

        for (int i = 1; i <= numberOfScooters; i++) {
            String brand = brands[random.nextInt(brands.length)]; // Random brand
            String location = locations[random.nextInt(locations.length)]; // Random location
            int price = (1000 + random.nextInt(2000)) / 100 * 100; // Random price rounded to hundreds
            String date = "2023-10-" + (random.nextInt(30) + 1); // Random day in October
            String link = "https://marktplaats.com/scooter" + i; // Unique link

            Scooter scooter = new Scooter(
                    brand + " " + i, // Title with brand and unique number
                    price,
                    location,
                    date + " 12:00", // Fixed time
                    link
            );
            scooterDatabase.add(scooter);
        }

        System.out.println("Scraper gestart...");
        System.out.println("Aantal gescrapede scooters: " + scooterDatabase.size());
    }

    private static void showScooters() {
        clearScreen();
        if (scooterDatabase.isEmpty()) {
            System.out.println("Er zijn nog geen scooters gescraped.");
        } else {
            System.out.println("Gevonden scooters:");
            System.out.println("===================================================================");
            System.out.printf("%-5s | %-15s | %-10s | %-10s | %-30s%n", "Nr", "Titel", "Prijs", "Locatie", "Link");
            System.out.println("===================================================================");
            int counter = 1;
            for (Scooter scooter : scooterDatabase) {
                System.out.printf("%-5d | %-15s | €%-9d | %-10s | %-30s%n",
                        counter++,
                        scooter.getTitel(),
                        scooter.getPrijs(),
                        scooter.getLocatie(),
                        scooter.getLink());
            }
            System.out.println("===================================================================");
        }
    }

    private static void filterScooters(Scanner scanner) {
        clearScreen();
        System.out.println("Filter scooters:");
        System.out.print("Titel (bijv. Zip, Vespa, leeg voor geen filter): ");
        String titleFilter = scanner.nextLine().trim();

        System.out.print("Prijs boven (bijv. 1500, leeg voor geen filter): ");
        String priceAboveInput = scanner.nextLine().trim();
        Integer priceAbove = priceAboveInput.isEmpty() ? null : Integer.parseInt(priceAboveInput);

        System.out.print("Prijs onder (bijv. 2000, leeg voor geen filter): ");
        String priceBelowInput = scanner.nextLine().trim();
        Integer priceBelow = priceBelowInput.isEmpty() ? null : Integer.parseInt(priceBelowInput);

        System.out.print("Locatie (bijv. Amsterdam, leeg voor geen filter): ");
        String locationFilter = scanner.nextLine().trim();

        List<Scooter> filteredScooters = scooterDatabase.stream()
                .filter(scooter -> titleFilter.isEmpty() || scooter.getTitel().toLowerCase().contains(titleFilter.toLowerCase()))
                .filter(scooter -> priceAbove == null || scooter.getPrijs() > priceAbove)
                .filter(scooter -> priceBelow == null || scooter.getPrijs() < priceBelow)
                .filter(scooter -> locationFilter.isEmpty() || scooter.getLocatie().equalsIgnoreCase(locationFilter))
                .toList();

        if (filteredScooters.isEmpty()) {
            System.out.println("Geen scooters gevonden met de opgegeven filters.");
        } else {
            System.out.println("Gefilterde scooters:");
            System.out.println("===================================================================");
            System.out.printf("%-5s | %-15s | %-10s | %-10s | %-30s%n", "Nr", "Titel", "Prijs", "Locatie", "Link");
            System.out.println("===================================================================");
            int counter = 1;
            for (Scooter scooter : filteredScooters) {
                System.out.printf("%-5d | %-15s | €%-9d | %-10s | %-30s%n",
                        counter++,
                        scooter.getTitel(),
                        scooter.getPrijs(),
                        scooter.getLocatie(),
                        scooter.getLink());
            }
            System.out.println("===================================================================");
        }
    }

    private static void updateUserSettings(Scanner scanner) {
        clearScreen();
        System.out.println("Huidige instellingen:");
        System.out.println("Gebruikersnaam: " + currentUser.getUsername());
        System.out.println("E-mailadres: " + currentUser.getEmail());
        System.out.println("==============================");

        System.out.print("Nieuwe gebruikersnaam (leeg laten om niet te wijzigen): ");
        String newUsername = scanner.nextLine().trim();
        if (!newUsername.isEmpty()) {
            currentUser = new User(newUsername, currentUser.getEmail());
        }

        System.out.print("Nieuw e-mailadres (leeg laten om niet te wijzigen): ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty()) {
            currentUser = new User(currentUser.getUsername(), newEmail);
        }

        clearScreen();
        System.out.println("Instellingen succesvol bijgewerkt!");
        System.out.println("Nieuwe gebruikersnaam: " + currentUser.getUsername());
        System.out.println("Nieuw e-mailadres: " + currentUser.getEmail());
    }

    private static void simulateDiscordNotification() {
        clearScreen();
        System.out.println("Simulatie van een Discord-melding...");
        System.out.println("====================================");

        if (scooterDatabase.isEmpty()) {
            System.out.println("**Geen scooters beschikbaar om te melden.**");
        } else {
            System.out.println("**Nieuwe scooters gevonden!**");
            System.out.println("```");
            for (Scooter scooter : scooterDatabase) {
                System.out.println("Titel: " + scooter.getTitel());
                System.out.println("Prijs: €" + scooter.getPrijs());
                System.out.println("Locatie: " + scooter.getLocatie());
                System.out.println("Link: " + scooter.getLink());
                System.out.println("------------------------------------");
            }
            System.out.println("```");
        }

        System.out.println("====================================");
        System.out.println("Notificatie gesimuleerd.");
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}