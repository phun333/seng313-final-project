package com.algo.question2_greedy_algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConferenceScheduler {
    
    // Method to read sessions from CSV file
    public static List<ConferenceSession> readSessionsFromCSV(String filename) throws IOException {
        List<ConferenceSession> sessions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip the header line
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    int id = Integer.parseInt(values[0].trim());
                    int startTime = Integer.parseInt(values[1].trim());
                    int endTime = Integer.parseInt(values[2].trim());
                    
                    sessions.add(new ConferenceSession(id, startTime, endTime));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        }
        
        return sessions;
    }
    
    public static List<ConferenceSession> scheduleMaxSessions(List<ConferenceSession> sessions) {
        // Sort sessions by end time (using the compareTo method we defined)
        Collections.sort(sessions);
        
        List<ConferenceSession> scheduledSessions = new ArrayList<>();
        
        if (sessions.isEmpty()) {
            return scheduledSessions;
        }
        
        // Add first session
        scheduledSessions.add(sessions.get(0));
        int lastEndTime = sessions.get(0).getEndTime();
        
        // Iterate through remaining sessions
        for (int i = 1; i < sessions.size(); i++) {
            ConferenceSession currentSession = sessions.get(i);
            
            // If current session starts after the last scheduled session ends
            if (currentSession.getStartTime() >= lastEndTime) {
                scheduledSessions.add(currentSession);
                lastEndTime = currentSession.getEndTime();
            }
        }
        
        return scheduledSessions;
    }
} 