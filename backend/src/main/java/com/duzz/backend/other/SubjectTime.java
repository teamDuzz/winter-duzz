package com.duzz.backend.other;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Embeddable
@Data
@NoArgsConstructor
public class SubjectTime {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ScheduleElement> schedule = new ArrayList<>();

    private SubjectTime(List<String> timeStr) {
        // timeStr: ["월 09:00 ~ 12:00", "수 09:00 ~ 12:00"]
        for (var time : timeStr) {
            var split = time.split(" ");
            if (split.length != 4) {
                throw new IllegalArgumentException("Invalid time format");
            }
            var dayOfWeek = DayOfWeek.valueOf(dayOfWeekMap.get(split[0]));
            var startTime = split[1].split(":");
            var endTime = split[3].split(":");
            addTime(dayOfWeek, Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]), Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        }
    }

    public void addTime(DayOfWeek dayOfWeek, int startHour, int startMinute, int endHour, int endMinute) {
        if (startHour == 24) {
            startHour = 23; startMinute = 59;
        }
        if (endHour == 24) {
            endHour = 23; endMinute = 59;
        }

        if (startHour > endHour || (startHour == endHour && startMinute >= endMinute)) {
            throw new IllegalArgumentException("Invalid time range: " + startHour + ":" + startMinute + " ~ " + endHour + ":" + endMinute);
        }

        var startTime = LocalDateTime.of(1970, 1, 1, startHour, startMinute);
        var endTime = LocalDateTime.of(1970, 1, 1, endHour, endMinute);
        schedule.add(new ScheduleElement(dayOfWeek, startTime, endTime));
    }

    public List<String> toStringList() {
        var result = new ArrayList<String>();
        for (var element : schedule) {
            result.add(element.toString());
        }
        return result;
    }

    public static SubjectTime of(List<String> timeStr) {
        return new SubjectTime(timeStr);
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ScheduleElement {
        private DayOfWeek dayOfWeek;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public String toString() {
            return dayOfWeekKorMap.get(dayOfWeek.toString()) + " " + startTime.format(formatter) + " ~ " + endTime.format(formatter);
        }

        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    public static final Map<String, String> dayOfWeekMap = Map.of(
            "월", "MONDAY",
            "화", "TUESDAY",
            "수", "WEDNESDAY",
            "목", "THURSDAY",
            "금", "FRIDAY",
            "토", "SATURDAY",
            "일", "SUNDAY"
    );
    public static final Map<String, String> dayOfWeekKorMap = Map.of(
            "MONDAY", "월",
            "TUESDAY", "화",
            "WEDNESDAY", "수",
            "THURSDAY", "목",
            "FRIDAY", "금",
            "SATURDAY", "토",
            "SUNDAY", "일"
    );
}
