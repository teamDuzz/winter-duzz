package com.duzz.backend.match;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class Mentor extends Profile {
    private final String major;
    List<String>subjects;
    public Mentor(String name,String number, String major, List<String> subjects) {
        super(name,number);
        this.major = major;
        this.subjects = subjects;
    }
}
