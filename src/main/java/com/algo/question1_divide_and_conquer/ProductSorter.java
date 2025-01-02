package com.algo.question1_divide_and_conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSorter {
    
    // Method to read products from CSV file
    public static Product[] readProductsFromCSV(String filename) throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip the header line
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    int id = Integer.parseInt(values[0].trim());
                    int salesVolume = Integer.parseInt(values[1].trim());
                    double rating = Double.parseDouble(values[2].trim());
                    
                    products.add(new Product(id, salesVolume, rating));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        }
        
        return products.toArray(new Product[0]);
    }
    
    // MergeSort implementation
    public static void mergeSort(Product[] products) {
        if (products.length < 2) return;
        
        int mid = products.length / 2;
        Product[] left = Arrays.copyOfRange(products, 0, mid);
        Product[] right = Arrays.copyOfRange(products, mid, products.length);
        
        mergeSort(left);
        mergeSort(right);
        merge(products, left, right);
    }
    
    private static void merge(Product[] products, Product[] left, Product[] right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) > 0) {
                products[k++] = left[i++];
            } else {
                products[k++] = right[j++];
            }
        }
        
        while (i < left.length) {
            products[k++] = left[i++];
        }
        
        while (j < right.length) {
            products[k++] = right[j++];
        }
    }
    
    // QuickSort implementation
    public static void quickSort(Product[] products) {
        quickSort(products, 0, products.length - 1);
    }
    
    private static void quickSort(Product[] products, int low, int high) {
        if (low < high) {
            int pi = partition(products, low, high);
            quickSort(products, low, pi - 1);
            quickSort(products, pi + 1, high);
        }
    }
    
    private static int partition(Product[] products, int low, int high) {
        Product pivot = products[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (products[j].compareTo(pivot) > 0) {
                i++;
                swap(products, i, j);
            }
        }
        
        swap(products, i + 1, high);
        return i + 1;
    }
    
    private static void swap(Product[] products, int i, int j) {
        Product temp = products[i];
        products[i] = products[j];
        products[j] = temp;
    }
    
    // Method to measure execution time
    public static double measureSortingTime(Product[] products, boolean useMergeSort) {
        // Warm up the JVM
        for (int i = 0; i < 5; i++) {
            Product[] warmupCopy = Arrays.copyOf(products, products.length);
            if (useMergeSort) {
                mergeSort(warmupCopy);
            } else {
                quickSort(warmupCopy);
            }
        }
        
        // Actual measurement
        Product[] copy = Arrays.copyOf(products, products.length);
        long startTime = System.nanoTime();
        
        if (useMergeSort) {
            mergeSort(copy);
        } else {
            quickSort(copy);
        }
        
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000.0; // Convert to milliseconds with decimal points
    }
} 