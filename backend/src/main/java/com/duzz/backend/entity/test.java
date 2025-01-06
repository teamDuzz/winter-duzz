package com.duzz.backend.entity;

import com.duzz.backend.match.Match;
import com.duzz.backend.match.Mentee;
import com.duzz.backend.match.Mentor;
import com.duzz.backend.match.MentorMenteeMatcher;

import java.util.Arrays;
import java.util.*;

public class test {
    public static void main(String[] args) {
        // 멘토 데이터 생성
        Mentor mentor1 = new Mentor("김멘토","1", "컴퓨터공학과", Arrays.asList("자바", "파이썬"));
        Mentor mentor2 = new Mentor("박멘토", "1","전자공학과", Arrays.asList("전자회로", "C++"));
        Mentor mentor3 = new Mentor("이멘토","1", "경영학과", Arrays.asList("경영", "파이썬"));
        Mentor mentor4 = new Mentor("최멘토","1", "수학과", Arrays.asList("수학", "통계학"));
        Mentor mentor5 = new Mentor("장멘토","1", "물리학과", Arrays.asList("양자역학", "고전역학"));
// 멘티 데이터 생성
        Mentee mentee1 = new Mentee("김멘티1","1", Arrays.asList("자바", "파이썬"), Arrays.asList("컴퓨터프로그래밍1", "미적분"), true);
        Mentee mentee2 = new Mentee("김멘티2","1", Arrays.asList("수학", "통계학"), Arrays.asList("수학1", "통계학"), false);
        Mentee mentee3 = new Mentee("김멘티3","1", Arrays.asList("경영", "파이썬"), Arrays.asList("경영학1", "미적분"), true);
        Mentee mentee4 = new Mentee("김멘티4","1", Arrays.asList("자바", "C++"), Arrays.asList("프로그래밍1", "운영체제"), false);
        Mentee mentee5 = new Mentee("김멘티5","1", Arrays.asList("파이썬", "기계공학"), Arrays.asList("물리학1", "수학1"), true);
        Mentee mentee6 = new Mentee("김멘티6","1", Arrays.asList("자바", "자기계발"), Arrays.asList("파이썬1", "컴퓨터조직"), false);
        Mentee mentee7 = new Mentee("김멘티7","1", Arrays.asList("전자회로", "C++"), Arrays.asList("회로이론", "디지털회로"), true);
        Mentee mentee8 = new Mentee("김멘티8","1", Arrays.asList("자기계발", "경영학"), Arrays.asList("경영학2", "미적분"), true);
        Mentee mentee9 = new Mentee("김멘티9","1", Arrays.asList("파이썬", "자바"), Arrays.asList("네트워크", "운영체제"), false);
        Mentee mentee10 = new Mentee("김멘티10", "1",Arrays.asList("수학", "물리학"), Arrays.asList("양자역학", "기하학"), true);
        Mentee mentee11 = new Mentee("김멘티11","1", Arrays.asList("회로", "전자공학"), Arrays.asList("전자기학", "회로이론"), false);
        Mentee mentee12 = new Mentee("김멘티12","1", Arrays.asList("파이썬", "수학"), Arrays.asList("이산수학", "확률론"), true);
        Mentee mentee13 = new Mentee("김멘티13","1", Arrays.asList("경영학", "자기계발"), Arrays.asList("경영학3", "통계학"), true);
        Mentee mentee14 = new Mentee("김멘티14","1", Arrays.asList("물리학", "통계학"), Arrays.asList("역학", "열역학"), false);
        Mentee mentee15 = new Mentee("김멘티15","1", Arrays.asList("물리학", "기계공학"), Arrays.asList("기계역학", "열역학"), true);
        Mentee mentee16 = new Mentee("김멘티16","1", Arrays.asList("C++", "프로그래밍"), Arrays.asList("알고리즘", "운영체제"), true);
        Mentee mentee17 = new Mentee("김멘티17","1", Arrays.asList("전자공학", "통계학"), Arrays.asList("회로이론", "전기자기학"), false);
        Mentee mentee18 = new Mentee("김멘티18","1", Arrays.asList("자바", "물리학"), Arrays.asList("파이썬", "기계학"), true);
        Mentee mentee19 = new Mentee("김멘티19", "1",Arrays.asList("자기계발", "파이썬"), Arrays.asList("수학1", "기초물리학"), true);
        Mentee mentee20 = new Mentee("김멘티20", "1",Arrays.asList("수학", "기계공학"), Arrays.asList("통계학", "기계역학"), false);
        List<Mentor> mentors = Arrays.asList(mentor1, mentor2, mentor3, mentor4, mentor5);
        List<Mentee> mentees = Arrays.asList(mentee1,mentee2,mentee3,mentee4,mentee5,mentee6,mentee7,mentee8,mentee9,mentee10,mentee11,mentee12,mentee13,mentee14,mentee15,mentee16,mentee17,mentee18,mentee19,mentee20);
        MentorMenteeMatcher mentorMenteeMatcher = new MentorMenteeMatcher();
        List<Match> matches = mentorMenteeMatcher.matchProfiles(mentors, mentees);
        for (Match match : matches) {
            System.out.println("멘토: " + match.mentor().getName());
            System.out.println("멘티: ");
            for (Mentee mentee : match.mentees()) {
                System.out.println(mentee.getName());
            }
            System.out.println();
        }
    }
}

