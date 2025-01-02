# Seng313 Final Project

This project implements five different algorithmic solutions as part of the Algorithm course final project.

## Team Members
- Mehmet Ali Selvet 210208033
- Ayşegül Beyza Karaalp 210208029
- Yiğit Erdoğan 210208025

## Project Structure

The project contains five main questions, each implemented in its own package:

1. **Question 1 (Divide and Conquer)**: Product Sorting
   - Implementation of MergeSort and QuickSort algorithms
   - Sorting products based on sales volume and ratings
   - Performance comparison with different input sizes

2. **Question 2 (Greedy Algorithm)**: Conference Scheduling
   - Implementation of interval scheduling algorithm
   - Maximizing the number of non-overlapping conference sessions
   - Reading conference data from CSV file

3. **Question 3 (Dynamic Programming)**: Longest Common Subsequence
   - Finding LCS between two text documents
   - Implementation of dynamic programming solution
   - Support for multiple possible sequences

4. **Question 4 (Graph Algorithm)**: Dijkstra's Algorithm
   - Implementation of dynamic routing system
   - Finding shortest paths in a city network
   - Support for adding/removing roads and updating weights

5. **Question 5 (Network Flow)**: Ford-Fulkerson Algorithm
   - Implementation of maximum flow algorithm
   - Reading network from adjacency matrix
   - Finding all possible paths contributing to max flow

## Technologies Used
- Java 11
- Maven for dependency management

## How to Run

1. Make sure you have Java 11 and Maven installed
2. Clone the repository
3. Navigate to the project directory
4. Run the following commands:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.algo.q1.Main"  # For Question 1
mvn exec:java -Dexec.mainClass="com.algo.q2.Main"  # For Question 2
mvn exec:java -Dexec.mainClass="com.algo.q3.Main"  # For Question 3
mvn exec:java -Dexec.mainClass="com.algo.q4.Main"  # For Question 4
mvn exec:java -Dexec.mainClass="com.algo.q5.Main"  # For Question 5
```

## Input Data
- Question 1: CSV files with product data (1000, 5000, and 10000 records)
- Question 2: CSV file with conference session data
- Question 3: Text files for document comparison
- Question 4: Graph representation of city network
- Question 5: Adjacency matrix for network flow

## Time Complexity Analysis
- Question 1: O(n log n) for both MergeSort and QuickSort
- Question 2: O(n log n) for interval scheduling
- Question 3: O(mn) for LCS algorithm
- Question 4: O((V + E) log V) for Dijkstra's algorithm
- Question 5: O(VE²) for Ford-Fulkerson algorithm 
