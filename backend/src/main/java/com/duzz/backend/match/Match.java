package com.duzz.backend.match;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record Match(Mentor mentor, List<Mentee> mentees) {
}
