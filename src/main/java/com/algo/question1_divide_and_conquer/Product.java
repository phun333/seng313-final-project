package com.algo.question1_divide_and_conquer;

public class Product implements Comparable<Product> {
    private int id;
    private int salesVolume;
    private double rating;

    public Product(int id, int salesVolume, double rating) {
        this.id = id;
        this.salesVolume = salesVolume;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public int compareTo(Product other) {
        // First compare by sales volume (descending)
        int salesComparison = Integer.compare(this.salesVolume, other.salesVolume);
        if (salesComparison != 0) {
            return salesComparison;
        }
        // If sales volumes are equal, compare by rating (descending)
        return Double.compare(this.rating, other.rating);
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, sales=%d, rating=%.1f}", 
                id, salesVolume, rating);
    }
} 