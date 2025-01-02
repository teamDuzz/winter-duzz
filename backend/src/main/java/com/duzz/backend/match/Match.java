package com.duzz.backend.match;

public class Match {
    Mentor mentor;
    Mentee mentee;
    double score;

    public Match(Mentor mentor, Mentee mentee, double score) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Mentor: " + mentor.name + ", Mentee: " + mentee.name + ", Score: " + score;
    }
}
