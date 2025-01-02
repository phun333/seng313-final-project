package com.algo.question2_greedy_algorithm;

public class ConferenceSession implements Comparable<ConferenceSession> {
    private int id;
    private int startTime;
    private int endTime;

    public ConferenceSession(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(ConferenceSession other) {
        // Sort by end time for greedy algorithm
        return Integer.compare(this.endTime, other.endTime);
    }

    @Override
    public String toString() {
        return String.format("Session %d: [%d-%d]", id, startTime, endTime);
    }
} 