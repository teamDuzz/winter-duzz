package com.duzz.backend.service;

import com.duzz.backend.component.JwtTokenProvider;
import com.duzz.backend.entity.Member;
import com.duzz.backend.form.SignInForm;
import com.duzz.backend.form.SignUpForm;
import com.duzz.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
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
                .build();

        memberRepository.save(member);
    }

    public String signIn(SignInForm form) {
        var authToken = new UsernamePasswordAuthenticationToken(form.getId(), form.getPassword());
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);
        return jwtTokenProvider.generateToken(auth);
    }
}
