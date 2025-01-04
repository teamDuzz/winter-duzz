package com.duzz.backend.match;

import lombok.Getter;

import java.util.List;

@Getter
public class Mentee extends Profile {
    List<String> interests; // 관심내용
    boolean option; // false: 시간표 우선 , true: 관심 분야 우선
    public Mentee(String name, List<String> interests,boolean option) {
        super(name);
        this.interests = interests;
        this.option=option;
    }

}
