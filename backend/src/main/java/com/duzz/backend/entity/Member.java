package com.duzz.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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
    @Column(length = 200) // 연락처
    private String phone;
    @Column(length = 200)
    private String interest; // 관심분야
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isMentor;
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean matchingOption; // 시간표 매칭 or 관심분야 매칭
    @ManyToOne
    private Major major;
    @Getter
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<MemberSubject> subjects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Member mentor;

    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<Member> mentees = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }

    @Override
    public String getUsername() {
        return id;
    }

}
