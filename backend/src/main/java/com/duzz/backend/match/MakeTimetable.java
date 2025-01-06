package com.duzz.backend.match;

import com.duzz.backend.dto.MemberDto;
import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.other.SubjectTime;
import com.duzz.backend.service.SubjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakeTimetable {
    public static int[][] makeTimetable(Member member){
        int[][] timetable = new int[7][48];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 48; j++){
                timetable[i][j] = 1;
            }
        }
        Set<MemberSubject> subjects = member.getSubjects();
        Map<String, Integer> dayMap = Map.of(
                "월", 0, "화", 1, "수", 2, "목", 3, "금", 4, "토", 5, "일", 6
        );
        for (MemberSubject subject : subjects) {
            SubjectTime time = subject.getSubject().getTime();
            List<String> strings = time.toStringList();
            for (String string : strings) {
                String[] split = string.split(" ");
                int day = dayMap.get(split[0]); // 요일을 숫자로 매핑

                // 시간 파싱
                String[] startTimeSplit = split[1].split(":");
                String[] endTimeSplit = split[3].split(":"); // "HH:mm ~ HH:mm"에서 종료 시간은 split[3]

                int startHour = Integer.parseInt(startTimeSplit[0]);
                int startMinute = Integer.parseInt(startTimeSplit[1]);
                int endHour = Integer.parseInt(endTimeSplit[0]);
                int endMinute = Integer.parseInt(endTimeSplit[1]);

                // 30분 단위로 시간표 채우기
                for (int hour = startHour; hour <= endHour; hour++) {
                    // 시작 시간과 종료 시간 사이의 30분 단위로 채우기
                    int startSlot = (hour == startHour) ? (startMinute / 30) : 0;
                    int endSlot = (hour == endHour) ? (endMinute / 30) : 2;

                    for (int slot = startSlot; slot < endSlot; slot++) {
                        timetable[day][hour * 2 + slot] = 0; // 30분 단위로 시간 채우기
                    }
                }
            }
        }
        return timetable;
    }
}
