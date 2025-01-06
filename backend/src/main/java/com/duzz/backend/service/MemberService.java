package com.duzz.backend.service;

import com.duzz.backend.component.JwtTokenProvider;
import com.duzz.backend.dto.MemberDto;
import com.duzz.backend.entity.Member;
import com.duzz.backend.form.MemberUpdateForm;
import com.duzz.backend.form.SignInForm;
import com.duzz.backend.form.SignUpForm;
import com.duzz.backend.repository.MajorRepository;
import com.duzz.backend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MajorRepository majorRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpForm form) {
        if (memberRepository.existsById(form.getId())) {
            throw new RuntimeException("이미 존재하는 학번입니다.");
        }

        var member = Member.builder()
                .id(form.getId())
                .name(form.getName())
                .password(passwordEncoder.encode(form.getPassword()))
                .isMentor(form.getIsMentor())
                .build();

        memberRepository.save(member);
    }

    public String signIn(SignInForm form) {
        var authToken = new UsernamePasswordAuthenticationToken(form.getId(), form.getPassword());
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);
        return jwtTokenProvider.generateToken(auth);
    }

    @Transactional
    public MemberDto getMember(String id) {
        var member = memberRepository.findById(id).orElse(null);
        if (member != null) {
            System.out.println(member.getSubjects());
            return MemberDto.from(member);
        }
        return null;
    }
    //모든 멤버 반환
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    public void updateMember(String id, MemberUpdateForm form) {
        var member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new RuntimeException("존재하지 않는 학번입니다.");
        }

        if (form.getName() != null) {
            member.setName(form.getName());
        }
        if (form.getPassword() != null) {
            member.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        if (form.getPhone() != null) {
            member.setPhone(form.getPhone());
        }
        if (form.getEmail() != null) {
            member.setEmail(form.getEmail());
        }
        if (form.getMajor() != null) {
            var major = majorRepository.findByName(form.getMajor()).orElse(null);
            if (major == null) {
                throw new RuntimeException("존재하지 않는 학과입니다.");
            }
            if (member.getMajor() != null) {
                member.getMajor().getMembers().remove(member);
            }
            member.setMajor(major);
            major.getMembers().add(member);
        }
        if (form.getIsMentor() != null) {
            member.setIsMentor(form.getIsMentor());
        }

        memberRepository.save(member);
    }
    public PagedModel<MemberDto> getMembersByMajor(String majorName, int page) {
        final int pageSize = 100;

        var major = majorRepository.findByName(majorName).orElse(null);
        if (major == null) {
            throw new RuntimeException("존재하지 않는 학과입니다.");
        }

        var sort = Sort.by(Sort.Order.asc("id"));
        var pageable = PageRequest.of(page, pageSize, sort);
        var members = memberRepository.findAllByMajor_Name(majorName, pageable);

        var memberDtos = members.map(MemberDto::from);

        return new PagedModel<>(memberDtos);
    }
}
