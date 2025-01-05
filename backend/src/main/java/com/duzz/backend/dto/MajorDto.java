package com.duzz.backend.dto;

import com.duzz.backend.entity.Major;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MajorDto {
    private String name;

    public static MajorDto from(Major major) {
        return MajorDto.builder()
                .name(major.getName())
                .build();
    }
}
