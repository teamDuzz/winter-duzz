package com.duzz.backend.match;

import lombok.Getter;

import java.util.*;
@Getter
public class Mentor extends Profile {
    private String major;
    List<String>subjects;
    public Mentor(String name, String major, List<String> subjects) {
        super(name);
        this.major = major;
        this.subjects = subjects;
    }
}
