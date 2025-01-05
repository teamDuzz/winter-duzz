package com.duzz.backend.dto;

import com.duzz.backend.entity.Subject;
import com.duzz.backend.other.SubjectTime;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
public class SubjectDto {
    private String id;
    private String name;
    private String professor;
    private String major;
    private Integer credit;
    private List<String> time;

    public static SubjectDto from(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .professor(subject.getProfessor())
                .major(subject.getMajor().getName())
                .credit(subject.getCredit())
                .time(subject.getTime().toStringList())
                .build();
    }
}
