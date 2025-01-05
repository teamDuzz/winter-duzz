package com.duzz.backend.util;

import com.duzz.backend.dto.SubjectDto;
import com.duzz.backend.other.SubjectTime;
import com.duzz.backend.service.MajorService;
import com.duzz.backend.service.SubjectService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class CsvToSubjects {
    private final SubjectService subjectService;
    private final MajorService majorService;

    @PostConstruct
    public void loadSubjects() {
        final boolean done = true;
        if (done) {
            return;
        }

        final String path = "assets/subjects.csv";

        var lines = CsvUtil.readCsv(path);
        var subjects = new ArrayList<SubjectDto>();
        var majors = new HashSet<String>();

        int min = 100, max = 0;
        for (int i = 1; i < lines.size(); i++) {
            var line = lines.get(i);
            var id = line[5];
            var name = line[0];
            var credit = Integer.parseInt(line[4].split("/")[0].split("\\.")[0]);
            var time = new SubjectTime();
            if (line.length >= 8) {
                for (var token : line[7].split("\\| ")) {
                    // 화10:00~12:00(자1315)
                    token = token.trim();
                    if (token.isEmpty()) {
                        continue;
                    }
                    var dayOfWeek = SubjectTime.dayOfWeekMap.get(token.substring(0, 1));
                    var startTime = token.substring(1, 6);
                    var endTime = token.substring(7, 12);
                    var startHour = Integer.parseInt(startTime.substring(0, 2));
                    var startMinute = Integer.parseInt(startTime.substring(3, 5));
                    var endHour = Integer.parseInt(endTime.substring(0, 2));
                    var endMinute = Integer.parseInt(endTime.substring(3, 5));
                    time.addTime(DayOfWeek.valueOf(dayOfWeek), startHour, startMinute, endHour, endMinute);
                }
            }

            var majorToken = line[3].split(" ");
            var majorIdx = 0;
            for (int j = 0; j < majorToken.length; j++) {
                if (majorToken[j].endsWith("학과") || majorToken[j].endsWith("학부")) {
                    majorIdx = j;
                    break;
                }
            }
            var major = majorToken[majorIdx];
            String professor = null;
            if (line.length >= 8) {
                professor = line[6];
            }

            var dto = SubjectDto.builder()
                    .id(id)
                    .name(name)
                    .credit(credit)
                    .professor(professor)
                    .time(time.toStringList())
                    .major(major)
                    .build();
            subjects.add(dto);
            majors.add(major);
        }

//        for (var major : majors) {
//            try {
//                majorService.addMajor(major);
//            } catch (Exception e) {
//                System.out.println("Failed to add major: " + major);
//            }
//        }

        subjectService.addOrUpdateSubjects(subjects);
    }
}
