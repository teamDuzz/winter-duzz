package com.duzz.backend.match;

import java.util.*;

public class MatchTest {
    public static void main(String[] args) {
        // 멘토 데이터 생성
        Mentor mentor1 = new Mentor("김멘토", "컴퓨터공학과", Arrays.asList("자바", "파이썬"));
        Mentor mentor2 = new Mentor("박멘토", "전자공학과", Arrays.asList("전자회로", "C++"));
        Mentor mentor3 = new Mentor("이멘토", "경영학과", Arrays.asList("경영", "파이썬"));
        Mentor mentor4 = new Mentor("최멘토", "수학과", Arrays.asList("수학", "통계학"));
        Mentor mentor5 = new Mentor("장멘토", "물리학과", Arrays.asList("양자역학", "고전역학"));

// 멘티 데이터 생성
        Mentee mentee1 = new Mentee("김멘티1", Arrays.asList("자바", "파이썬"), Arrays.asList("컴퓨터프로그래밍1", "미적분"), true);
        Mentee mentee2 = new Mentee("김멘티2", Arrays.asList("수학", "통계학"), Arrays.asList("수학1", "통계학"), false);
        Mentee mentee3 = new Mentee("김멘티3", Arrays.asList("경영", "파이썬"), Arrays.asList("경영학1", "미적분"), true);
        Mentee mentee4 = new Mentee("김멘티4", Arrays.asList("자바", "C++"), Arrays.asList("프로그래밍1", "운영체제"), false);
        Mentee mentee5 = new Mentee("김멘티5", Arrays.asList("파이썬", "기계공학"), Arrays.asList("물리학1", "수학1"), true);
        Mentee mentee6 = new Mentee("김멘티6", Arrays.asList("자바", "자기계발"), Arrays.asList("파이썬1", "컴퓨터조직"), false);
        Mentee mentee7 = new Mentee("김멘티7", Arrays.asList("전자회로", "C++"), Arrays.asList("회로이론", "디지털회로"), true);
        Mentee mentee8 = new Mentee("김멘티8", Arrays.asList("자기계발", "경영학"), Arrays.asList("경영학2", "미적분"), true);
        Mentee mentee9 = new Mentee("김멘티9", Arrays.asList("파이썬", "자바"), Arrays.asList("네트워크", "운영체제"), false);
        Mentee mentee10 = new Mentee("김멘티10", Arrays.asList("수학", "물리학"), Arrays.asList("양자역학", "기하학"), true);
        Mentee mentee11 = new Mentee("김멘티11", Arrays.asList("회로", "전자공학"), Arrays.asList("전자기학", "회로이론"), false);
        Mentee mentee12 = new Mentee("김멘티12", Arrays.asList("파이썬", "수학"), Arrays.asList("이산수학", "확률론"), true);
        Mentee mentee13 = new Mentee("김멘티13", Arrays.asList("경영학", "자기계발"), Arrays.asList("경영학3", "통계학"), true);
        Mentee mentee14 = new Mentee("김멘티14", Arrays.asList("물리학", "통계학"), Arrays.asList("역학", "열역학"), false);
        Mentee mentee15 = new Mentee("김멘티15", Arrays.asList("물리학", "기계공학"), Arrays.asList("기계역학", "열역학"), true);
        Mentee mentee16 = new Mentee("김멘티16", Arrays.asList("C++", "프로그래밍"), Arrays.asList("알고리즘", "운영체제"), true);
        Mentee mentee17 = new Mentee("김멘티17", Arrays.asList("전자공학", "통계학"), Arrays.asList("회로이론", "전기자기학"), false);
        Mentee mentee18 = new Mentee("김멘티18", Arrays.asList("자바", "물리학"), Arrays.asList("파이썬", "기계학"), true);
        Mentee mentee19 = new Mentee("김멘티19", Arrays.asList("자기계발", "파이썬"), Arrays.asList("수학1", "기초물리학"), true);
        Mentee mentee20 = new Mentee("김멘티20", Arrays.asList("수학", "기계공학"), Arrays.asList("통계학", "기계역학"), false);

// 각 멘토들의 스케줄 설정 (예시)
        mentor1.schedule[0][9] = 1;
        mentor1.schedule[0][10] = 1;
        mentor1.schedule[0][11] = 1;
        mentor1.schedule[0][12] = 1;
        mentor1.schedule[0][13] = 1;

        mentor2.schedule[0][14] = 1;
        mentor2.schedule[0][15] = 1;

        mentor3.schedule[1][9] = 1;
        mentor3.schedule[1][10] = 1;

        mentor4.schedule[2][11] = 1;
        mentor4.schedule[2][12] = 1;
        mentor4.schedule[2][13] = 1;

        mentor5.schedule[3][14] = 1;
        mentor5.schedule[3][15] = 1;


        List<Mentor> mentors = Arrays.asList(mentor1, mentor2, mentor3, mentor4, mentor5);
        List<Mentee> mentees = Arrays.asList(mentee1,mentee2,mentee3,mentee4,mentee5,mentee6,mentee7,mentee8,mentee9,mentee10,mentee11,mentee12,mentee13,mentee14,mentee15,mentee16,mentee17,mentee18,mentee19,mentee20);
        List<Match> matches = MentorMenteeMatcher.matchProfiles(mentors, mentees);
        for (Match match : matches) {
            System.out.println("멘토: " + match.getMentor().getName());
            System.out.println("멘티: ");
            for (Mentee mentee : match.getMentees()) {
                System.out.println(mentee.getName());
            }
            System.out.println();
        }
    }
}
