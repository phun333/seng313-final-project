package com.algo.question2_greedy_algorithm;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Read conference sessions from CSV file
            String csvFile = "src/main/java/com/algo/question2_greedy_algorithm/csv_datas/conference_sessions.csv";
            List<ConferenceSession> sessions = ConferenceScheduler.readSessionsFromCSV(csvFile);
            
            System.out.println("Available Conference Sessions:");
            for (ConferenceSession session : sessions) {
                System.out.println(session);
            }
            
            // Schedule maximum number of sessions
            List<ConferenceSession> scheduledSessions = ConferenceScheduler.scheduleMaxSessions(sessions);
            
            System.out.println("\nScheduled Sessions (Maximum non-overlapping):");
            for (ConferenceSession session : scheduledSessions) {
                System.out.println(session);
            }
            
            System.out.println("\nTotal number of scheduled sessions: " + scheduledSessions.size());
            
            System.out.println("\nGreedy Algorithm Analysis:");
            System.out.println("1. Initial number of sessions: " + sessions.size());
            System.out.println("2. Maximum non-overlapping sessions: " + scheduledSessions.size());
            System.out.println("3. Time Complexity: O(n log n) due to sorting");
            System.out.println("4. Space Complexity: O(n) for storing the result");
            System.out.println("\nGreedy Choice Property:");
            System.out.println("- Algorithm sorts sessions by end time");
            System.out.println("- Always selects the session that ends earliest");
            System.out.println("- This ensures maximum number of non-overlapping sessions");
            
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
} 