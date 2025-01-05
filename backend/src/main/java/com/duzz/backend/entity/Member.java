package com.duzz.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    @Column(length = 50, nullable = true)
    private String email;
    @Column(length = 200, nullable = false)
    private String password;
    // 추후 추가할 필드
    // 수강 과목, 학년, 관심분야
    @Column(length = 200) // 연락처
    private String phone;
    @Column(length = 200)

    private Boolean isMentor;

    @ManyToOne
    private Major major;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<MemberSubject> subjects = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }

    @Override
    public String getUsername() {
        return id;
    }
}
