package com.duzz.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DummyAddInfoDto {
    List<String> mentors;
    List<String> mentees;
}
