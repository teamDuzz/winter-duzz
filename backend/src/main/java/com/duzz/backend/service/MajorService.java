package com.duzz.backend.service;

import com.duzz.backend.dto.MajorDto;
import com.duzz.backend.dto.SubjectDto;
import com.duzz.backend.entity.Major;
import com.duzz.backend.repository.MajorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public void addMajor(String majorName) {
        if (majorRepository.existsById(majorName)) {
            throw new IllegalArgumentException("이미 존재하는 학과입니다.");
        }

        majorRepository.save(Major.builder()
                .name(majorName)
                .build());
    }

    public List<MajorDto> getMajorList() {
        return majorRepository.findAll().stream()
                .map(MajorDto::from)
                .toList();
    }

    @Transactional
    public List<SubjectDto> getSubjectByMajor(String majorName) {
        var major = majorRepository.findById(majorName).orElse(null);
        if (major == null) {
            throw new IllegalArgumentException("존재하지 않는 학과입니다.");
        }

        return major.getSubjects().stream()
                .map(SubjectDto::from)
                .toList();
    }
}
