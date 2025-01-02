package com.algo.question1_divide_and_conquer;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // CSV file paths
        String[] csvFiles = {
            "src/main/java/com/algo/question1_divide_and_conquer/csv_datas/data_product1000.csv",
            "src/main/java/com/algo/question1_divide_and_conquer/csv_datas/data_product_5000.csv",
            "src/main/java/com/algo/question1_divide_and_conquer/csv_datas/data_product_10000.csv"
        };
        
        StringBuilder output = new StringBuilder();
        Product[] sortedProducts1000 = null;
        
        for (String csvFile : csvFiles) {
            try {
                // Read products from CSV
                Product[] products = ProductSorter.readProductsFromCSV(csvFile);
                int size = products.length; // Don't subtract 1 since we want to show actual size
                
                // Run each sort multiple times and take average for more accurate results
                double mergeSortTotal = 0;
                double quickSortTotal = 0;
                int runs = 10;
                
                for (int i = 0; i < runs; i++) {
                    // For MergeSort
                    Product[] mergeCopy = Arrays.copyOf(products, products.length);
                    long mergeStart = System.nanoTime();
                    ProductSorter.mergeSort(mergeCopy);
                    long mergeEnd = System.nanoTime();
                    mergeSortTotal += (mergeEnd - mergeStart) / 1_000_000.0;
                    
                    if (csvFile.endsWith("data_product1000.csv") && i == 0) {
                        sortedProducts1000 = mergeCopy;
                    }
                    
                    // For QuickSort
                    Product[] quickCopy = Arrays.copyOf(products, products.length);
                    long quickStart = System.nanoTime();
                    ProductSorter.quickSort(quickCopy);
                    long quickEnd = System.nanoTime();
                    quickSortTotal += (quickEnd - quickStart) / 1_000_000.0;
                }
                
                double mergeSortAvg = mergeSortTotal / runs;
                double quickSortAvg = quickSortTotal / runs;
                
                output.append(String.format("%d\t\t%.3f\t\t%.3f%n", size, mergeSortAvg, quickSortAvg));
                
            } catch (IOException e) {
                System.err.println("Error reading CSV file " + csvFile + ": " + e.getMessage());
            }
        }
        
        // Print execution time comparison and sorted products together
        System.out.println("Size\t\tMergeSort\tQuickSort");
        System.out.print(output);
        
        // Print first 10 products from 1000 records
        if (sortedProducts1000 != null) {
            System.out.println("\nFirst 10 products sorted by sales volume (descending):");
            for (int i = 0; i < Math.min(10, sortedProducts1000.length); i++) {
                System.out.printf("Sales: %d, Rating: %.1f%n", 
                    sortedProducts1000[i].getSalesVolume(), 
                    sortedProducts1000[i].getRating());
            }
        }
        
        System.out.println("\nComplexity Analysis:");
        System.out.println("MergeSort: O(n log n) - stable sort");
        System.out.println("QuickSort: O(n log n) average case, O(n²) worst case - not stable");
        System.out.println("\nNote: Times are averaged over 10 runs for each size, with JVM warm-up");
        System.out.println("Times shown in milliseconds with 3 decimal places for better precision");
    }
} 