package com.duzz.backend.match;

import java.util.*;

public class MatchTest {
    public static void main(String[] args) {
        Mentor mentor1 = new Mentor("kim", new int[7][48], "컴퓨터공학과", Arrays.asList("자바", "파이썬"));
        Mentor mentor2 = new Mentor("박멘토", new int[7][48], "전자공학과", Arrays.asList("C", "C++"));
        Mentor mentor3 = new Mentor("이멘토", new int[7][48], "경영학과", Arrays.asList("자바", "파이썬"));
        Mentor mentor4 = new Mentor("최멘토", new int[7][48], "수학과", Arrays.asList("수학", "통계학"));
        Mentee mentee1 = new Mentee("김멘티", new int[7][48], Arrays.asList("자바", "파이썬"));
        mentor1.schedule[0][9] = 1;
        mentor1.schedule[0][10] = 1;
        mentor1.schedule[0][11] = 1;
        mentor1.schedule[0][12] = 1;
        mentor1.schedule[0][13] = 1;
        mentor2.schedule[0][14] = 1;
        mentor2.schedule[0][15] = 1;
        mentee1.schedule[0][9] = 1;
        mentee1.schedule[0][10] = 1;
        mentee1.schedule[0][11] = 1;
        List<Mentor> mentors = Arrays.asList(mentor1, mentor2, mentor3, mentor4);
        Mentor bestMentor =  MentorMenteeMatcher.matchProfiles(mentors, mentee1);
        System.out.println(bestMentor);
    }
}
