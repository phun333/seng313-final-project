package com.algo.question5_graph_algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NetworkFlow {
    private int vertices;
    private int[][] graph;
    private int[][] residualGraph;
    
    // Constructor that reads from file
    public NetworkFlow(String filename) throws IOException {
        readFromFile(filename);
    }
    
    // Read network from file
    private void readFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            // Skip comments and empty lines until we find the number of vertices
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                this.vertices = Integer.parseInt(line);
                break;
            }
            
            // Initialize graphs
            this.graph = new int[vertices][vertices];
            this.residualGraph = new int[vertices][vertices];
            
            // Read adjacency matrix
            int row = 0;
            while ((line = br.readLine()) != null && row < vertices) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Remove comments from the line
                if (line.contains("#")) {
                    line = line.substring(0, line.indexOf("#")).trim();
                }
                
                String[] values = line.split("\\s+");
                for (int col = 0; col < vertices; col++) {
                    graph[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        }
    }
    
    // Ford-Fulkerson algorithm implementation
    public int findMaxFlow(int source, int sink) {
        // Initialize residual graph
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }
        
        int maxFlow = 0;
        List<List<Integer>> allPaths = new ArrayList<>();
        
        // Augment the flow while there is a path from source to sink
        while (true) {
            int[] parent = new int[vertices];
            Arrays.fill(parent, -1);
            
            // Find an augmenting path using BFS
            if (!bfs(source, sink, parent)) {
                break;
            }
            
            // Find minimum residual capacity along the path
            int pathFlow = Integer.MAX_VALUE;
            List<Integer> currentPath = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
                currentPath.add(0, v);
            }
            currentPath.add(0, source);
            allPaths.add(currentPath);
            
            // Update residual capacities and reverse edges
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            
            maxFlow += pathFlow;
        }
        
        // Print all paths contributing to max flow
        System.out.println("\nPaths contributing to maximum flow:");
        for (int i = 0; i < allPaths.size(); i++) {
            List<Integer> path = allPaths.get(i);
            System.out.printf("Path %d: ", i + 1);
            for (int j = 0; j < path.size(); j++) {
                if (j > 0) System.out.print(" -> ");
                System.out.printf("Node %d", path.get(j));
            }
            System.out.println();
        }
        
        return maxFlow;
    }
    
    // BFS to find path from source to sink
    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(source);
        visited[source] = true;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.offer(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }
        
        return visited[sink];
    }
    
    // Print the network
    public void printNetwork() {
        System.out.println("Network Adjacency Matrix:");
        System.out.println("------------------------");
        
        // Print column headers
        System.out.print("     ");
        for (int i = 0; i < vertices; i++) {
            System.out.printf("Node%-3d", i);
        }
        System.out.println("\n");
        
        // Print matrix with row headers
        for (int i = 0; i < vertices; i++) {
            System.out.printf("Node%-1d ", i);
            for (int j = 0; j < vertices; j++) {
                System.out.printf("%6d", graph[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // Get number of vertices
    public int getVertices() {
        return vertices;
    }
} 