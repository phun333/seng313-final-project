package com.algo.question4_graph_algorithm;

import java.util.*;

public class Graph {
    private Map<Integer, Map<Integer, Integer>> adjacencyList;
    private int vertices;
    
    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new HashMap<>());
        }
    }
    
    // Add a new road
    public void addRoad(int source, int destination, int weight) {
        adjacencyList.get(source).put(destination, weight);
        adjacencyList.get(destination).put(source, weight); // Assuming undirected graph
    }
    
    // Remove a road
    public void removeRoad(int source, int destination) {
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }
    
    // Update road weight
    public void updateRoadWeight(int source, int destination, int newWeight) {
        adjacencyList.get(source).put(destination, newWeight);
        adjacencyList.get(destination).put(source, newWeight);
    }
    
    // Dijkstra's algorithm implementation
    public Map<Integer, Integer> findShortestPaths(int startLocation) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        
        // Initialize distances
        for (int i = 0; i < vertices; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(startLocation, 0);
        pq.offer(new Node(startLocation, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            
            if (visited.contains(currentVertex)) {
                continue;
            }
            
            visited.add(currentVertex);
            
            for (Map.Entry<Integer, Integer> neighbor : adjacencyList.get(currentVertex).entrySet()) {
                int nextVertex = neighbor.getKey();
                int weight = neighbor.getValue();
                
                if (!visited.contains(nextVertex)) {
                    int newDistance = distances.get(currentVertex) + weight;
                    if (newDistance < distances.get(nextVertex)) {
                        distances.put(nextVertex, newDistance);
                        pq.offer(new Node(nextVertex, newDistance));
                    }
                }
            }
        }
        
        return distances;
    }
    
    // Get number of vertices
    public int getVertices() {
        return vertices;
    }
    
    // Get adjacency list
    public Map<Integer, Map<Integer, Integer>> getAdjacencyList() {
        return adjacencyList;
    }
    
    // Inner class for PriorityQueue
    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
} 