package com.duzz.backend.service;

import com.duzz.backend.dto.DummyAddInfoDto;
import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.entity.Subject;
import com.duzz.backend.repository.MajorRepository;
import com.duzz.backend.repository.MemberRepository;
import com.duzz.backend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DummyMemberService {
    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;
    private final MajorRepository majorRepository;
    private final PasswordEncoder passwordEncoder;

    public DummyAddInfoDto addDummies(int mentorCount, int menteeCount) {
        var dummies = new ArrayList<Member>();
        var mentors = new ArrayList<String>();
        var mentees = new ArrayList<String>();
        var majors = majorRepository.findAll();
        for (int i = 0; i < mentorCount; ++i) {
            var randomMajor = majors.get(randomInt(0, majors.size() - 1));
            var subjects = new ArrayList<Subject>();
            var subjectCount = randomInt(3, 7);
            var subjectOfMajor = subjectRepository.findAllByMajor_Name(randomMajor.getName());
            for (int j = 0; j < subjectCount; ++j) {
                var randomSubject = subjectOfMajor.get(randomInt(0, subjectOfMajor.size() - 1));
                subjects.add(randomSubject);
            }

            var dummy = Member.builder()
                    .name("Dummy" + i)
                    .email("dummy" + i + "@test.com")
                    .password(passwordEncoder.encode("password"))
                    .id("d0-" + i)
                    .major(randomMajor)
                    .isMentor(true)
                    .interest("")
                    .matchingOption(false)
                    .phone("010-1234-5678")
                    .build();
            dummy.setSubjects(subjects.stream().map(subject -> MemberSubject.of(dummy, subject)).collect(Collectors.toSet()));
            dummies.add(dummy);
            mentors.add(dummy.getId());
        }

        for (int i = 0; i < menteeCount; ++i) {
            var randomMajor = majors.get(randomInt(0, majors.size() - 1));
            var subjects = new ArrayList<Subject>();
            var subjectCount = randomInt(3, 7);
            var subjectOfMajor = subjectRepository.findAllByMajor_Name(randomMajor.getName());
            for (int j = 0; j < subjectCount; ++j) {
                var randomSubject = subjectOfMajor.get(randomInt(0, subjectOfMajor.size() - 1));
                subjects.add(randomSubject);
            }

            var dummy = Member.builder()
                    .name("Dummy" + i)
                    .email("dummy" + i + "@test.com")
                    .password(passwordEncoder.encode("password"))
                    .id("d1-" + i)
                    .major(randomMajor)
                    .isMentor(false)
//                    .interest("interest")
                    .interest(generateInterest())
                    .matchingOption(false)
                    .phone("010-1234-5678")
                    .build();
            dummy.setSubjects(subjects.stream().map(subject -> MemberSubject.of(dummy, subject)).collect(Collectors.toSet()));
            dummies.add(dummy);
            mentees.add(dummy.getId());
        }

        memberRepository.saveAll(dummies);
        System.out.println("Dummy members are added: " + dummies.size());

        return DummyAddInfoDto.builder()
                .mentors(mentors)
                .mentees(mentees)
                .build();
    }

    public int clearDummies() {
        var allMembers = memberRepository.findAll();
        var toDelete = allMembers.stream().filter(member -> member.getId().startsWith("d")).toList();
        memberRepository.deleteAll(toDelete);
        System.out.println("Dummy members are deleted: " + toDelete.size());
        return toDelete.size();
    }

    private String generateInterest() {
        final List<String> interests = List.of(
                "AI", "보안", "프로그래밍", "데이터베이스", "네트워크", "운영체제", "컴퓨터구조", "자료구조", "알고리즘", "웹", "앱", "게임", "임베디드", "로봇", "클라우드", "빅데이터", "머신러닝", "딥러닝", "컴퓨터비전", "자연어처리", "블록체인",
                "화학", "물리", "생물", "지구과학", "수학", "통계", "물리학", "화학공학", "생명공학", "환경공학", "지구과학", "수학", "통계", "물리학", "화학공학", "생명공학", "환경공학", "지구과학", "수학", "통계", "물리학", "화학공학", "생명공학", "환경공학",
                "글쓰기", "윤리", "역사", "정치", "경제", "사회학", "심리학", "인류학", "문화인류학", "언어학", "철학", "종교학", "예술", "음악", "미술", "건축", "디자인", "영화", "연극", "무용", "문학", "소설", "시", "희곡", "수필", "평론", "기행문", "일기", "서사시", "자서전", "전기", "소설", "시", "희곡", "수필", "평론", "기행문", "일기", "서사시", "자서전", "전기", "소설", "시", "희곡", "수필", "평론", "기행문", "일기", "서사시", "자서전", "전기",
                "미술", "현대미술", "그림", "조각", "설치", "회화", "판화", "사진", "영상", "디자인", "제품디자인", "패션디자인", "그래픽디자인", "웹디자인", "인테리어디자인", "건축디자인", "공간디자인", "시각디자인", "편집디자인", "광고디자인", "일러스트레이션", "애니메이션", "게임디자인", "영화디자인", "사운드디자인", "뮤지컬디자인", "연극디자인", "예술", "미술사", "미술이론", "미술비평",
                "영상편집"
        );

        var sb = new StringBuilder();
        var cnt = 1;
        for (int i = 0; i < cnt; ++i) {
            sb.append(interests.get(randomInt(0, interests.size() - 1)));
            if (i < cnt - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
