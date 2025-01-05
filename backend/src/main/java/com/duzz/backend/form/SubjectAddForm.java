package com.duzz.backend.form;

import com.duzz.backend.dto.SubjectDto;
import com.duzz.backend.other.SubjectTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SubjectAddForm {
    @Schema(description = "과목 번호", example = "1000-9083")
    private String id;
    @Schema(description = "과목명", example = "자료구조")
    private String name;
    @Schema(description = "학점", example = "3")
    private Integer credit;
    @Schema(description = "학과", example = "컴퓨터융합학부")
    private String major;

    @Schema(description = "수업 시간의 리스트", example = "[\"월 09:00 ~ 10:00\", \"수 09:00 ~ 10:00\"]")
    private List<String> time;

    public static SubjectDto toDto(SubjectAddForm form) {
        return SubjectDto.builder()
                .id(form.getId())
                .name(form.getName())
                .major(form.getMajor())
                .credit(form.getCredit())
                .time(form.getTime())
                .build();
    }
}
