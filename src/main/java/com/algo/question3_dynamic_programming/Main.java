package com.algo.question3_dynamic_programming;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Read texts from files
            String file1 = "src/main/java/com/algo/question3_dynamic_programming/text_files/document1.txt";
            String file2 = "src/main/java/com/algo/question3_dynamic_programming/text_files/document2.txt";
            
            String[] words1 = LCSFinder.readTextFromFile(file1);
            String[] words2 = LCSFinder.readTextFromFile(file2);
            
            System.out.println("Input Sequences:");
            System.out.println("---------------");
            System.out.println("Sequence 1: " + String.join(" ", words1));
            System.out.println("Sequence 2: " + String.join(" ", words2));
            
            // Find all possible LCS
            List<String> lcs = LCSFinder.findAllLCS(words1, words2);
            
            System.out.println("\nLongest Common Word Sequence Results:");
            System.out.println("------------------------------------");
            if (!lcs.isEmpty()) {
                System.out.println("Number of words in longest common sequence: " + 
                    lcs.get(0).split("\\s+").length);
                
                System.out.println("\nAll possible longest common sequences:");
                int count = 1;
                int maxLength = lcs.get(0).split("\\s+").length;
                
                for (String sequence : lcs) {
                    if (sequence.split("\\s+").length == maxLength) {
                        System.out.printf("%d. [%d words] %s%n", 
                            count++, 
                            sequence.split("\\s+").length,
                            sequence);
                    }
                }
            } else {
                System.out.println("No common sequences found.");
            }
            
            // Print the LCS matrix for visualization
            LCSFinder.printLCSMatrix(words1, words2);
            
            System.out.println("\nComplexity Analysis:");
            System.out.println("Time Complexity: O(mn) for finding LCS length");
            System.out.println("Space Complexity: O(mn) for dp table");
            System.out.println("where m and n are the number of words in the input texts");
            
        } catch (IOException e) {
            System.err.println("Error reading text files: " + e.getMessage());
        }
    }
} 