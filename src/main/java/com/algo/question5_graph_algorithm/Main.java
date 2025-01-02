package com.algo.question5_graph_algorithm;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Read network from file
            String networkFile = "src/main/java/com/algo/question5_graph_algorithm/input_data/network.txt";
            NetworkFlow network = new NetworkFlow(networkFile);
            
            // Print the network
            network.printNetwork();
            
            // Get source and sink nodes from user
            Scanner scanner = new Scanner(System.in);
            
            System.out.printf("Enter source node (0-%d): ", network.getVertices() - 1);
            int source = scanner.nextInt();
            
            System.out.printf("Enter sink node (0-%d): ", network.getVertices() - 1);
            int sink = scanner.nextInt();
            
            // Validate input
            if (source < 0 || source >= network.getVertices() || 
                sink < 0 || sink >= network.getVertices()) {
                System.out.println("Invalid source or sink node!");
                return;
            }
            
            // Find and print maximum flow
            System.out.println("\nFinding maximum flow from node " + source + " to node " + sink + "...");
            int maxFlow = network.findMaxFlow(source, sink);
            System.out.println("\nMaximum Flow: " + maxFlow);
            
            System.out.println("\nComplexity Analysis:");
            System.out.println("Time Complexity: O(VE²)");
            System.out.println("Space Complexity: O(V²)");
            System.out.println("where V is the number of vertices and E is the number of edges");
            
        } catch (IOException e) {
            System.err.println("Error reading network file: " + e.getMessage());
        }
    }
} 