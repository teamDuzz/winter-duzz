package com.duzz.backend.match;

import lombok.Getter;

import java.util.*;
@Getter
public class Mentor extends Profile {
    private String major;
    List<String>subjects;
    public Mentor() {
        super("", new int[5][48]);
    }
    public Mentor(String name, int[][] schedule, String major, List<String> subjects) {
        super(name, schedule);
        this.major = major;
        this.subjects = subjects;
    }
}
