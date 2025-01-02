package com.algo.question3_dynamic_programming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LCSFinder {
    private static int[][] dp;
    private static String[] words1;
    private static String[] words2;
    
    // Method to read text from file and get words
    public static String[] readTextFromFile(String filename) throws IOException {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove punctuation and split into words
                String[] words = line.replaceAll("[^a-zA-Z\\s]", "").trim().split("\\s+");
                wordList.addAll(Arrays.asList(words));
            }
        }
        return wordList.toArray(new String[0]);
    }
    
    public static List<String> findAllLCS(String[] text1, String[] text2) {
        if (text1 == null || text2 == null) {
            return new ArrayList<>();
        }
        
        words1 = text1;
        words2 = text2;
        
        // Initialize the dp array
        dp = new int[text1.length + 1][text2.length + 1];
        
        // Fill the dp array
        for (int i = 1; i <= text1.length; i++) {
            for (int j = 1; j <= text2.length; j++) {
                if (text1[i - 1].equals(text2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Find all possible LCS
        Set<String> result = new HashSet<>();
        backtrack(text1.length, text2.length, new ArrayList<>(), result);
        
        // Sort results by number of words
        List<String> sortedResults = new ArrayList<>(result);
        sortedResults.sort((a, b) -> {
            int aWords = a.split("\\s+").length;
            int bWords = b.split("\\s+").length;
            return Integer.compare(bWords, aWords);
        });
        
        return sortedResults;
    }
    
    private static void backtrack(int i, int j, List<String> current, Set<String> result) {
        if (i == 0 || j == 0) {
            if (!current.isEmpty()) {
                Collections.reverse(current);
                result.add(String.join(" ", current));
            }
            return;
        }
        
        if (i > 0 && j > 0 && words1[i - 1].equals(words2[j - 1])) {
            current.add(words1[i - 1]);
            backtrack(i - 1, j - 1, current, result);
            current.remove(current.size() - 1);
        } else {
            if (i > 0 && (j == 0 || dp[i - 1][j] >= dp[i][j - 1])) {
                backtrack(i - 1, j, current, result);
            }
            if (j > 0 && (i == 0 || dp[i][j - 1] >= dp[i - 1][j])) {
                backtrack(i, j - 1, current, result);
            }
        }
    }
    
    public static int getLCSLength(String[] text1, String[] text2) {
        return dp[text1.length][text2.length];
    }
    
    // Method to print LCS matrix
    public static void printLCSMatrix(String[] text1, String[] text2) {
        System.out.println("\nLCS Matrix:");
        
        // Find the maximum word length for formatting
        int maxLength = 0;
        for (String word : text1) {
            maxLength = Math.max(maxLength, word.length());
        }
        for (String word : text2) {
            maxLength = Math.max(maxLength, word.length());
        }
        
        // Format string for words and numbers
        String wordFormat = "%" + (maxLength + 2) + "s";
        String numFormat = "%" + (maxLength + 2) + "d";
        
        // Print header row with words from text2
        System.out.print(String.format(wordFormat, ""));
        for (String word : text2) {
            System.out.print(String.format(wordFormat, word));
        }
        System.out.println();
        
        // Print matrix rows with words from text1
        for (int i = 1; i <= text1.length; i++) {
            System.out.print(String.format(wordFormat, text1[i-1]));
            for (int j = 1; j <= text2.length; j++) {
                System.out.print(String.format(numFormat, dp[i][j]));
            }
            System.out.println();
        }
    }
} 