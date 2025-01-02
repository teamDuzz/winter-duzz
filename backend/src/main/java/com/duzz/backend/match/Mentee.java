package com.duzz.backend.match;

import lombok.Getter;

import java.util.List;

@Getter
public class Mentee extends Profile {
    List<String> interests; // 관심내용

    public Mentee(String name, int[][] schedule, List<String> interests) {
        super(name, schedule);
        this.interests = interests;
    }

}
