package com.duzz.backend.match;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Match {
    private final Mentor mentor;
    private final List<Mentee> mentees;

    public Match(Mentor mentor, List<Mentee> mentees) {
        this.mentor = mentor;
        this.mentees = mentees;

    }
}
