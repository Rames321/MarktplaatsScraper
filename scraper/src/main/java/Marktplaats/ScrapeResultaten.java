package Marktplaats;

import java.util.List;

public class ScrapeResultaten {
    private List<Scooter> scooters;
    private int totalResults;

    public ScrapeResultaten(List<Scooter> scooters) {
        this.scooters = scooters;
        this.totalResults = scooters.size();
    }

    public List<Scooter> getScooters() {
        return scooters;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return "ScrapeResult{totalResults=" + totalResults + ", scooters=" + scooters + "}";
    }
}