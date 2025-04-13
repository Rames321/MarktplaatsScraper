package Marktplaats;

public class Zoeken {
    private String keyword;
    private double minPrice;
    private double maxPrice;

    public Zoeken(String keyword, double minPrice, double maxPrice) {
        this.keyword = keyword;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getKeyword() {
        return keyword;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "Zoeken{keyword='" + keyword + "', minPrice=" + minPrice + ", maxPrice=" + maxPrice + "}";
    }
}