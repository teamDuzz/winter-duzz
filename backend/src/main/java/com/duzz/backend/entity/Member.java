package com.duzz.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {
    @Id
    @Column(length = 10, nullable = false) // 학번
    private String id;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String password;
    // 추후 추가할 필드
    // 소속, 수강 과목, 학년, 연락처
    @Column(length = 50, nullable = true) // 연락처
    private String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }

    @Override
    public String getUsername() {
        return id;
    }

}
