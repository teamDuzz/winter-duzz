package com.duzz.backend.service;

import com.duzz.backend.dto.SubjectDto;
import com.duzz.backend.entity.Subject;
import com.duzz.backend.other.SubjectTime;
import com.duzz.backend.repository.MajorRepository;
import com.duzz.backend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final MajorRepository majorRepository;

    public void addOrUpdateSubject(SubjectDto subjectDto) {
        var subject = subjectRepository.findById(subjectDto.getId()).orElse(null);
        if (subject == null) {
            subject = makeSubject(subjectDto);
        } else {
            updateSubject(subject, subjectDto);
        }

        subjectRepository.save(subject);
    }

    public void addOrUpdateSubjects(Iterable<SubjectDto> subjectDtos) {
        var subjects = new ArrayList<Subject>();
        for (var subjectDto : subjectDtos) {
            var subject = subjectRepository.findById(subjectDto.getId()).orElse(null);
            if (subject == null) {
                subject = makeSubject(subjectDto);
            } else {
                updateSubject(subject, subjectDto);
            }

            subjects.add(subject);
        }

        subjectRepository.saveAll(subjects);
    }

    public void deleteSubject(String subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    public PagedModel<SubjectDto> getSubjects(String keyword, int page) {
        var sort = Sort.by("name").ascending();
        var pageable = PageRequest.of(page, 50, sort);

        var subjects = subjectRepository.findByKeyword(keyword, pageable);
        var subjectDtos = subjects.map(SubjectDto::from);
        return new PagedModel<>(subjectDtos);
    }

    public Subject getSubject(String subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    private Subject makeSubject(SubjectDto subjectDto) {
        var major = majorRepository.findById(subjectDto.getMajor()).orElse(null);
        if (major == null) {
            throw new IllegalArgumentException("존재하지 않는 학과입니다.");
        }

        return Subject.builder()
                .id(subjectDto.getId())
                .name(subjectDto.getName())
                .professor(subjectDto.getProfessor())
                .time(SubjectTime.of(subjectDto.getTime()))
                .major(major)
                .build();
    }

    private void updateSubject(Subject subject, SubjectDto subjectDto) {
        var major = majorRepository.findById(subjectDto.getMajor()).orElse(null);
        if (major == null) {
            throw new IllegalArgumentException("존재하지 않는 학과입니다.");
        }

        subject.setName(subjectDto.getName());
        subject.setTime(SubjectTime.of(subjectDto.getTime()));
        subject.setMajor(major);
        subject.setProfessor(subjectDto.getProfessor());
        subject.setCredit(subjectDto.getCredit());
    }
}
