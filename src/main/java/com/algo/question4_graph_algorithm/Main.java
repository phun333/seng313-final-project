package com.algo.question4_graph_algorithm;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create a sample city graph with 6 locations
        Graph cityGraph = new Graph(6);
        
        // Add initial roads
        System.out.println("Initial City Graph Setup:");
        cityGraph.addRoad(0, 1, 4);
        cityGraph.addRoad(0, 2, 2);
        cityGraph.addRoad(1, 2, 1);
        cityGraph.addRoad(1, 3, 5);
        cityGraph.addRoad(2, 3, 8);
        cityGraph.addRoad(2, 4, 10);
        cityGraph.addRoad(3, 4, 2);
        cityGraph.addRoad(3, 5, 6);
        cityGraph.addRoad(4, 5, 3);
        
        // Print initial shortest paths from location 0
        System.out.println("Initial Shortest Paths from Location 0:");
        printShortestPaths(cityGraph, 0);
        
        // Simulate road closure (remove road between 2 and 3)
        System.out.println("\nRoad Closure: Removing road between locations 2 and 3");
        cityGraph.removeRoad(2, 3);
        System.out.println("Updated Shortest Paths from Location 0:");
        printShortestPaths(cityGraph, 0);
        
        // Add a new road
        System.out.println("\nNew Road: Adding road between locations 0 and 4 with weight 7");
        cityGraph.addRoad(0, 4, 7);
        System.out.println("Updated Shortest Paths from Location 0:");
        printShortestPaths(cityGraph, 0);
        
        // Update road weight
        System.out.println("\nRoad Maintenance: Updating weight between locations 1 and 3 from 5 to 3");
        cityGraph.updateRoadWeight(1, 3, 3);
        System.out.println("Updated Shortest Paths from Location 0:");
        printShortestPaths(cityGraph, 0);
        
        System.out.println("\nComplexity Analysis:");
        System.out.println("Time Complexity: O((V + E) log V) for Dijkstra's algorithm");
        System.out.println("Space Complexity: O(V + E) for adjacency list");
        System.out.println("where V is the number of vertices (locations) and E is the number of edges (roads)");
        
        System.out.println("\nDynamic Updates:");
        System.out.println("- Adding/Removing roads: O(1)");
        System.out.println("- Updating road weights: O(1)");
        System.out.println("- Recalculating shortest paths: O((V + E) log V)");
    }
    
    private static void printShortestPaths(Graph graph, int startLocation) {
        Map<Integer, Integer> distances = graph.findShortestPaths(startLocation);
        for (int i = 0; i < graph.getVertices(); i++) {
            if (i != startLocation) {
                int distance = distances.get(i);
                if (distance == Integer.MAX_VALUE) {
                    System.out.printf("Location %d to %d: No path exists%n", startLocation, i);
                } else {
                    System.out.printf("Location %d to %d: Distance = %d%n", startLocation, i, distance);
                }
            }
        }
    }
} 