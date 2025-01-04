package com.duzz.backend.match;

import java.util.*;

public class MatchTest {
    public static void main(String[] args) {
        // 멘토 20명 생성 과목 6~7개로 생성 한글 이름으로
        Mentor mentor1 = new Mentor("이준호", "심리학", Arrays.asList("인지심리학", "발달심리학", "사회심리학", "심리검사", "심리학적 평가"));
        Mentor mentor2 = new Mentor("정해성", "화학", Arrays.asList("유기화학", "무기화학", "물리화학", "화학반응속도", "화학분석"));
        Mentor mentor3 = new Mentor("박수민", "문학", Arrays.asList("현대문학", "고전문학", "문학비평", "영미문학", "한국문학"));
        Mentor mentor4 = new Mentor("김도현", "지리학", Arrays.asList("지역지리", "인문지리", "경제지리", "기후학", "지도학"));
        Mentor mentor5 = new Mentor("송지훈", "생명과학", Arrays.asList("세포생물학", "유전학", "생리학", "진화론", "생화학"));
        Mentor mentor6 = new Mentor("윤정호", "사회학", Arrays.asList("사회이론", "문화사회학", "사회조직", "사회문제", "인구학"));
        Mentor mentor7 = new Mentor("김상현", "미술", Arrays.asList("회화", "조소", "미술사", "디지털 아트", "비주얼 문화"));
        Mentor mentor8 = new Mentor("최정우", "철학", Arrays.asList("형이상학", "윤리학", "논리학", "동양철학", "서양철학"));
        Mentor mentor9 = new Mentor("이광수", "경영학", Arrays.asList("회계학", "마케팅", "인적자원관리", "조직행동학", "경영전략"));
        Mentor mentor10 = new Mentor("박민수", "의학", Arrays.asList("해부학", "생리학", "병리학", "약리학", "내과학"));
        Mentor mentor11 = new Mentor("김정훈", "건축학", Arrays.asList("건축설계", "구조공학", "건축역사", "도시계획", "건축자재"));
        Mentor mentor12 = new Mentor("허성철", "컴퓨터공학", Arrays.asList("알고리즘", "인공지능", "데이터베이스", "네트워크", "소프트웨어 공학"));
        Mentor mentor13 = new Mentor("조영진", "교육학", Arrays.asList("교육심리학", "교육철학", "교수법", "교육과정 개발", "교육행정"));
        Mentor mentor14 = new Mentor("한상우", "국제학", Arrays.asList("국제정치", "국제경제", "국제법", "국제기구", "외교정책"));
        Mentor mentor15 = new Mentor("서태영", "음악", Arrays.asList("작곡", "음악이론", "음악사", "합창", "악기연주"));
        Mentor mentor16 = new Mentor("이민수", "수학", Arrays.asList("미적분학", "선형대수학", "확률론", "수리논리학", "해석학"));
        Mentor mentor17 = new Mentor("박우철", "심리학", Arrays.asList("심리치료", "인지과학", "발달심리", "심리학 연구방법", "행동과학"));
        Mentor mentor18 = new Mentor("정우진", "패션디자인", Arrays.asList("패션 역사", "의류 디자인", "패션 마케팅", "소재학", "패션 트렌드 분석"));
        Mentor mentor19 = new Mentor("한정훈", "사회복지학", Arrays.asList("사회복지실천", "사회복지정책", "사회복지행정", "복지법", "사회복지윤리"));
        Mentor mentor20 = new Mentor("오현수", "정보기술", Arrays.asList("네트워크 보안", "정보 시스템", "웹 개발", "빅데이터", "모바일 프로그래밍"));

        // 멘티 100명 생성 과목(6~7개), 관심사 3~4개로 생성 한글 이름으로 이름 다양하게 안겹치게
        Mentee mentee1 = new Mentee("김솔우", Arrays.asList("자바", "파이썬", "C++", "운영체제", "알고리즘"), Arrays.asList("프로그래밍", "게임 개발", "컴퓨터 비전"), false);
        Mentee mentee2 = new Mentee("유석민", Arrays.asList("미적분", "수학1", "수학2", "확률론", "선형대수"), Arrays.asList("수학", "데이터 분석", "기계학습"), false);
        Mentee mentee3 = new Mentee("이훈승", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "조직론"), Arrays.asList("경영", "스타트업", "팀 관리"), false);
        Mentee mentee4 = new Mentee("김수민", Arrays.asList("전자회로", "회로이론", "디지털회로", "전자기학", "신호 처리"), Arrays.asList("전기 공학", "회로 설계", "로봇 공학"), false);
        Mentee mentee5 = new Mentee("박훈지", Arrays.asList("물리학1", "물리학2", "기초물리학", "열역학", "고급 물리학"), Arrays.asList("물리학", "천체 물리학", "에너지 연구"), false);
        Mentee mentee6 = new Mentee("강우정", Arrays.asList("기계학", "기계역학", "열역학", "유체역학", "자동제어"), Arrays.asList("기계 공학", "자동차 설계", "로봇 공학"), false);
        Mentee mentee7 = new Mentee("이철규", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "마케팅"), Arrays.asList("경영 전략", "디지털 마케팅", "창업"), false);
        Mentee mentee8 = new Mentee("김진우", Arrays.asList("전자회로", "회로이론", "디지털회로", "전자기학", "컴퓨터 아키텍처"), Arrays.asList("회로 설계", "전력 시스템", "디지털 전자"), false);
        Mentee mentee9 = new Mentee("박수민", Arrays.asList("물리학1", "물리학2", "기초물리학", "열역학", "고체물리학"), Arrays.asList("물리학", "에너지 전환", "나노기술"), false);
        Mentee mentee10 = new Mentee("강훈지", Arrays.asList("기계학", "기계역학", "열역학", "유체역학", "기계 설계"), Arrays.asList("기계 공학", "제품 디자인", "3D 프린팅"), false);
        Mentee mentee11 = new Mentee("이우정", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "회계학"), Arrays.asList("경영", "재무 분석", "기업 성장 전략"), false);
        Mentee mentee12 = new Mentee("김정우", Arrays.asList("전자회로", "회로이론", "디지털회로", "전자기학", "통신이론"), Arrays.asList("통신 기술", "IoT", "전자기기"), false);
        Mentee mentee13 = new Mentee("박규철", Arrays.asList("물리학1", "물리학2", "기초물리학", "열역학", "양자역학"), Arrays.asList("물리학", "양자 컴퓨터", "우주 연구"), false);
        Mentee mentee14 = new Mentee("강우진", Arrays.asList("기계학", "기계역학", "열역학", "유체역학", "냉각 공학"), Arrays.asList("기계 공학", "HVAC 시스템", "열 관리"), false);
        Mentee mentee15 = new Mentee("이민수", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "리더십"), Arrays.asList("리더십", "인적 자원 관리", "글로벌 비즈니스"), false);
        Mentee mentee16 = new Mentee("김지훈", Arrays.asList("전자회로", "회로이론", "디지털회로", "전자기학", "회로 설계"), Arrays.asList("회로 설계", "전자기기", "하드웨어 개발"), false);
        Mentee mentee17 = new Mentee("박솔우", Arrays.asList("물리학1", "물리학2", "기초물리학", "열역학", "고체물리학"), Arrays.asList("물리학", "지구 물리학", "환경 연구"), false);
        Mentee mentee18 = new Mentee("강석민", Arrays.asList("기계학", "기계역학", "열역학", "유체역학", "열전달"), Arrays.asList("기계 공학", "자동차 설계", "발전 시스템"), false);
        Mentee mentee19 = new Mentee("이훈승", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "인공지능"), Arrays.asList("비즈니스 분석", "AI 기술", "디지털 혁신"), false);
        Mentee mentee20 = new Mentee("김수민", Arrays.asList("전자회로", "회로이론", "디지털회로", "전자기학", "자동제어"), Arrays.asList("로봇 공학", "자동화", "제어 시스템"), false);
        Mentee mentee21 = new Mentee("박훈지", Arrays.asList("물리학1", "물리학2", "기초물리학", "열역학", "양자역학"), Arrays.asList("물리학", "스페이스X", "미래 기술"), false);
        Mentee mentee22 = new Mentee("강우정", Arrays.asList("기계학", "기계역학", "열역학", "유체역학", "재료 공학"), Arrays.asList("기계 설계", "건설 기술", "자동차 산업"), false);
        Mentee mentee23 = new Mentee("이철규", Arrays.asList("경영학1", "경영학2", "경영학3", "통계학", "전략적 마케팅"), Arrays.asList("마케팅", "브랜드 관리", "소셜 미디어"), false);
        Mentee mentee24 = new Mentee("김서영", Arrays.asList("자바", "웹 개발", "알고리즘", "운영체제", "데이터베이스"), Arrays.asList("웹 개발", "프론트엔드", "앱 개발"), false);
        Mentee mentee25 = new Mentee("조민준", Arrays.asList("미적분", "선형대수", "확률론", "통계학", "이산수학"), Arrays.asList("수학", "데이터 과학", "기계학습"), false);
        Mentee mentee26 = new Mentee("유은서", Arrays.asList("화학1", "화학2", "물리화학", "유기화학", "고분자화학"), Arrays.asList("화학", "환경 공학", "화학 공정"), false);
        Mentee mentee27 = new Mentee("이서윤", Arrays.asList("생물학1", "생물학2", "분자생물학", "세포생물학", "유전자공학"), Arrays.asList("생명과학", "유전자 편집", "약리학"), false);
        Mentee mentee28 = new Mentee("박지민", Arrays.asList("심리학1", "심리학2", "인지심리학", "발달심리학", "사회심리학"), Arrays.asList("심리학", "심리 상담", "정신 건강"), false);
        Mentee mentee29 = new Mentee("강민경", Arrays.asList("기계설계", "구조역학", "진동학", "열역학", "자동화 시스템"), Arrays.asList("기계 공학", "스마트 팩토리", "산업 공학"), false);
        Mentee mentee30 = new Mentee("최성환", Arrays.asList("알고리즘", "데이터베이스", "운영체제", "웹 개발", "보안"), Arrays.asList("컴퓨터 공학", "사이버 보안", "클라우드 컴퓨팅"), false);
        Mentee mentee31 = new Mentee("정수진", Arrays.asList("경제학1", "미시경제학", "거시경제학", "국제경제학", "금융시장"), Arrays.asList("경제학", "금융", "사회복지"), false);
        Mentee mentee32 = new Mentee("허지훈", Arrays.asList("물리학1", "수학적 물리학", "기계학습", "운동학", "광학"), Arrays.asList("물리학", "AI", "기계학습"), false);
        Mentee mentee33 = new Mentee("서지혜", Arrays.asList("국제법", "인권법", "민법", "상법", "형법"), Arrays.asList("법학", "국제 관계", "정책 연구"), false);
        Mentee mentee34 = new Mentee("오민석", Arrays.asList("분자생물학", "생화학", "유전자학", "분자유전학", "약리학"), Arrays.asList("생명과학", "약학", "바이오테크"), false);
        Mentee mentee35 = new Mentee("임영석", Arrays.asList("천문학1", "천문학2", "물리학", "천체물리학", "우주론"), Arrays.asList("천문학", "우주 탐사", "관측 기술"), false);
        Mentee mentee36 = new Mentee("남상현", Arrays.asList("컴퓨터 네트워크", "디지털 통신", "정보 이론", "소프트웨어 엔지니어링", "보안"), Arrays.asList("컴퓨터 공학", "통신 기술", "소프트웨어 개발"), false);
        Mentee mentee37 = new Mentee("전해진", Arrays.asList("작곡", "음악이론", "음악사", "작곡법", "오케스트라"), Arrays.asList("음악", "클래식 음악", "영화 음악"), false);
        Mentee mentee38 = new Mentee("김시우", Arrays.asList("비즈니스 전략", "인공지능", "경영학", "마케팅", "경영정보시스템"), Arrays.asList("경영학", "AI", "빅데이터"), false);
        Mentee mentee39 = new Mentee("조수정", Arrays.asList("연극", "연기", "무대 연출", "영화 이론", "영상 편집"), Arrays.asList("연극", "영화", "미디어"), false);
        Mentee mentee40 = new Mentee("한지호", Arrays.asList("화학1", "화학2", "화학공학", "화학 실험", "환경화학"), Arrays.asList("화학", "환경 공학", "화학 연구"), false);
        Mentee mentee41 = new Mentee("김소라", Arrays.asList("정보기술", "디지털 마케팅", "UX/UI 디자인", "웹 개발", "클라우드 컴퓨팅"), Arrays.asList("IT", "디지털 마케팅", "스타트업"), false);
        Mentee mentee42 = new Mentee("윤승현", Arrays.asList("재무회계", "회계학", "기업 재무", "회계분석", "투자학"), Arrays.asList("회계학", "금융", "기업 재무"), false);
        Mentee mentee43 = new Mentee("서진영", Arrays.asList("기계학", "열역학", "제어공학", "기계설계", "로봇 공학"), Arrays.asList("기계 공학", "로봇 공학", "3D 프린팅"), false);
        Mentee mentee44 = new Mentee("박은빈", Arrays.asList("컴퓨터 공학", "웹 개발", "기계학습", "인공지능", "딥러닝"), Arrays.asList("AI", "소프트웨어 개발", "자율주행"), false);
        Mentee mentee45 = new Mentee("이수현", Arrays.asList("화학1", "화학2", "물리화학", "유기화학", "나노과학"), Arrays.asList("화학", "나노기술", "재료 과학"), false);
        Mentee mentee46 = new Mentee("김준수", Arrays.asList("건축학", "도시 계획", "건축 설계", "구조 공학", "재료 공학"), Arrays.asList("건축", "도시 개발", "친환경 건축"), false);
        Mentee mentee47 = new Mentee("한나영", Arrays.asList("패션 디자인", "패션 역사", "컬러 이론", "디자인 이론", "의상학"), Arrays.asList("디자인", "패션", "패션 마케팅"), false);
        Mentee mentee48 = new Mentee("신성빈", Arrays.asList("물리학1", "물리학2", "양자역학", "열역학", "전자기학"), Arrays.asList("물리학", "에너지 연구", "양자 컴퓨터"), false);
        Mentee mentee49 = new Mentee("배정현", Arrays.asList("미디어학", "커뮤니케이션 이론", "방송학", "영화학", "디지털 미디어"), Arrays.asList("미디어", "커뮤니케이션", "디지털 콘텐츠"), false);
        Mentee mentee50 = new Mentee("전미경", Arrays.asList("교육학1", "교육심리학", "교육과정", "특수교육", "사회복지학"), Arrays.asList("교육학", "특수 교육", "사회복지"), false);
        Mentee mentee51 = new Mentee("최은경", Arrays.asList("수학1", "수학2", "미적분학", "확률론", "선형대수"), Arrays.asList("수학", "통계학", "데이터 분석"), false);
        Mentee mentee52 = new Mentee("정하늘", Arrays.asList("기계학", "응용역학", "열역학", "진동학", "기계 설계"), Arrays.asList("기계 공학", "로봇 공학", "자동화"), false);
        Mentee mentee53 = new Mentee("배수정", Arrays.asList("심리학", "발달심리학", "행동 심리학", "사회 심리학", "인지 심리학"), Arrays.asList("심리학", "심리 상담", "인간 행동 연구"), false);
        Mentee mentee54 = new Mentee("김유진", Arrays.asList("화학1", "화학2", "분석 화학", "유기 화학", "환경 화학"), Arrays.asList("화학", "환경 공학", "환경 과학"), false);
        Mentee mentee55 = new Mentee("한수현", Arrays.asList("경제학", "금융학", "미시경제학", "거시경제학", "금융시장"), Arrays.asList("경제학", "경영학", "금융"), false);
        Mentee mentee56 = new Mentee("서지영", Arrays.asList("화학공학", "화학1", "화학2", "유기화학", "물리화학"), Arrays.asList("화학", "화학 공정", "바이오 화학"), false);
        Mentee mentee57 = new Mentee("장민우", Arrays.asList("사회학", "심리학", "법학", "정치학", "경제학"), Arrays.asList("사회학", "사회 연구", "사회복지"), false);
        Mentee mentee58 = new Mentee("김서영", Arrays.asList("기계 설계", "열역학", "구조역학", "유체역학", "자동차 공학"), Arrays.asList("기계 공학", "자동차 공학", "에너지 연구"), false);
        Mentee mentee59 = new Mentee("박지영", Arrays.asList("회계학", "경영학", "기업 재무", "경영정보시스템", "회계분석"), Arrays.asList("회계학", "경영학", "기업 재무"), false);
        Mentee mentee60 = new Mentee("오수연", Arrays.asList("심리학1", "심리학2", "심리치료", "심리상담", "심리학적 실험"), Arrays.asList("심리학", "심리 상담", "정신 건강"), false);
        Mentee mentee61 = new Mentee("김민수", Arrays.asList("컴퓨터 공학", "기계학습", "소프트웨어 개발", "인공지능", "빅데이터"), Arrays.asList("컴퓨터 공학", "소프트웨어 개발", "AI"), false);
        Mentee mentee62 = new Mentee("이상훈", Arrays.asList("경제학", "경영학", "인공지능", "빅데이터", "데이터 분석"), Arrays.asList("경제학", "데이터 과학", "경영학"), false);
        Mentee mentee63 = new Mentee("차지훈", Arrays.asList("화학1", "물리화학", "화학공학", "분석 화학", "고분자 화학"), Arrays.asList("화학", "환경 화학", "화학 공정"), false);
        Mentee mentee64 = new Mentee("서은영", Arrays.asList("수학1", "수학2", "미적분학", "대수학", "선형대수"), Arrays.asList("수학", "컴퓨터 과학", "통계학"), false);
        Mentee mentee65 = new Mentee("최지은", Arrays.asList("기계 설계", "기계학", "열역학", "유체역학", "자동화"), Arrays.asList("기계 공학", "로봇 공학", "자동화"), false);
        Mentee mentee66 = new Mentee("이영수", Arrays.asList("법학", "국제법", "민법", "형법", "상법"), Arrays.asList("법학", "정치학", "법률 연구"), false);
        Mentee mentee67 = new Mentee("홍민지", Arrays.asList("정보통신", "컴퓨터 공학", "네트워크", "운영체제", "디지털 통신"), Arrays.asList("IT", "통신", "소프트웨어 개발"), false);
        Mentee mentee68 = new Mentee("정다영", Arrays.asList("화학", "화학공학", "고분자 화학", "재료 공학", "화학 실험"), Arrays.asList("화학", "화학 연구", "바이오화학"), false);
        Mentee mentee69 = new Mentee("김진욱", Arrays.asList("응용수학", "미적분학", "대수학", "수리통계학", "선형대수"), Arrays.asList("수학", "기계 공학", "전산학"), false);
        Mentee mentee70 = new Mentee("박성진", Arrays.asList("디지털 마케팅", "브랜드 전략", "소셜 미디어 마케팅", "UX/UI 디자인", "광고학"), Arrays.asList("마케팅", "디지털 마케팅", "브랜드"), false);
        Mentee mentee71 = new Mentee("김소연", Arrays.asList("디지털 미디어", "영상 제작", "편집 기술", "미디어 디자인", "영상 기획"), Arrays.asList("미디어", "영상 제작", "디지털 콘텐츠"), false);
        Mentee mentee72 = new Mentee("이민주", Arrays.asList("미술", "조형학", "회화", "조각", "디자인 이론"), Arrays.asList("미술", "디자인", "시각 예술"), false);
        Mentee mentee73 = new Mentee("최은지", Arrays.asList("화학", "화학공학", "물리화학", "유기화학", "고분자화학"), Arrays.asList("화학", "화학 공정", "환경 연구"), false);
        Mentee mentee74 = new Mentee("한상미", Arrays.asList("정보 기술", "네트워크", "소프트웨어 개발", "보안", "클라우드 컴퓨팅"), Arrays.asList("IT", "소프트웨어 개발", "사이버 보안"), false);
        Mentee mentee75 = new Mentee("김다영", Arrays.asList("경영학", "경제학", "국제경제학", "기업 재무", "마케팅"), Arrays.asList("경영학", "마케팅", "금융"), false);
        Mentee mentee76 = new Mentee("조준혁", Arrays.asList("유전자학", "세포생물학", "분자생물학", "진화생물학", "의료생명학"), Arrays.asList("생명과학", "의학", "생물학"), false);
        Mentee mentee77 = new Mentee("박지영", Arrays.asList("기계학", "자동화", "로봇 공학", "제어 공학", "기계 설계"), Arrays.asList("기계 공학", "로봇 공학", "자동화"), false);
        Mentee mentee78 = new Mentee("윤지훈", Arrays.asList("심리학", "행동과학", "심리 상담", "인지과학", "정신 건강"), Arrays.asList("심리학", "정신 건강", "심리상담"), false);
        Mentee mentee79 = new Mentee("이서연", Arrays.asList("철학", "윤리학", "동양 철학", "서양 철학", "미학"), Arrays.asList("철학", "윤리학", "미학"), false);
        Mentee mentee80 = new Mentee("김채원", Arrays.asList("디지털 디자인", "웹 디자인", "UX/UI", "3D 모델링", "디지털 미디어"), Arrays.asList("디자인", "디지털 디자인", "미디어"), false);
        Mentee mentee81 = new Mentee("박찬호", Arrays.asList("심리학", "사회 심리학", "심리학 실험", "심리학 연구", "정신 분석"), Arrays.asList("심리학", "정신 건강", "행동 연구"), false);
        Mentee mentee82 = new Mentee("한지연", Arrays.asList("음악이론", "작곡", "합창", "오케스트라", "음악 교육"), Arrays.asList("음악", "작곡", "음악 교육"), false);
        Mentee mentee83 = new Mentee("양수지", Arrays.asList("법학", "형법", "민법", "상법", "행정법"), Arrays.asList("법학", "법률", "법적 연구"), false);
        Mentee mentee84 = new Mentee("장서현", Arrays.asList("사회학", "정치학", "경제학", "인류학", "심리학"), Arrays.asList("사회학", "정치학", "인류학"), false);
        Mentee mentee85 = new Mentee("최지윤", Arrays.asList("기계 설계", "전자 공학", "제어 시스템", "기계학", "응용 공학"), Arrays.asList("기계 공학", "전자 공학", "제어 시스템"), false);
        Mentee mentee86 = new Mentee("허정은", Arrays.asList("교육학", "심리학", "교육과정", "어린이 심리학", "청소년 상담"), Arrays.asList("교육학", "심리학", "교육 연구"), false);
        Mentee mentee87 = new Mentee("이진아", Arrays.asList("정보 보안", "사이버 보안", "디지털 포렌식", "컴퓨터 네트워크", "보안 프로토콜"), Arrays.asList("IT", "사이버 보안", "정보 보호"), false);
        Mentee mentee88 = new Mentee("박민희", Arrays.asList("미디어학", "커뮤니케이션", "디지털 미디어", "영상 콘텐츠", "미디어 디자인"), Arrays.asList("미디어", "영상 제작", "디지털 콘텐츠"), false);
        Mentee mentee89 = new Mentee("강지수", Arrays.asList("경영학", "조직 이론", "마케팅", "경영 전략", "경영 정보 시스템"), Arrays.asList("경영학", "비즈니스 분석", "경영 전략"), false);
        Mentee mentee90 = new Mentee("오윤희", Arrays.asList("경제학", "금융학", "국제 경제학", "거시 경제학", "통계학"), Arrays.asList("경제학", "금융", "경영학"), false);
        Mentee mentee91 = new Mentee("차상훈", Arrays.asList("역사학", "고대 역사", "세계사", "중세 역사", "근대 역사"), Arrays.asList("역사학", "고대 역사", "문화 연구"), false);
        Mentee mentee92 = new Mentee("정다희", Arrays.asList("화학", "유기 화학", "고분자 화학", "재료 과학", "환경 화학"), Arrays.asList("화학", "환경 연구", "화학 공정"), false);
        Mentee mentee93 = new Mentee("전해민", Arrays.asList("기계학", "유체역학", "자동차 공학", "기계 설계", "재료 공학"), Arrays.asList("기계 공학", "자동차 공학", "로봇 공학"), false);
        Mentee mentee94 = new Mentee("양수빈", Arrays.asList("디지털 디자인", "웹 디자인", "UX/UI", "프로젝트 관리", "광고학"), Arrays.asList("디자인", "UX/UI", "디지털 콘텐츠"), false);
        Mentee mentee95 = new Mentee("한소라", Arrays.asList("지리학", "환경학", "기후 변화", "지구 과학", "환경 정책"), Arrays.asList("환경학", "지리학", "기후 변화 연구"), false);
        Mentee mentee96 = new Mentee("김태현", Arrays.asList("컴퓨터 공학", "프로그래밍", "알고리즘", "운영체제", "소프트웨어 개발"), Arrays.asList("컴퓨터 공학", "소프트웨어 개발", "알고리즘"), false);
        Mentee mentee97 = new Mentee("최한나", Arrays.asList("음악 이론", "합창", "오케스트라", "작곡", "음악 분석"), Arrays.asList("음악", "작곡", "음악 교육"), false);
        Mentee mentee98 = new Mentee("조수아", Arrays.asList("기계학", "자동화 시스템", "로봇 공학", "기계 설계", "구조역학"), Arrays.asList("기계 공학", "로봇 공학", "자동화"), false);
        Mentee mentee99 = new Mentee("김정아", Arrays.asList("회계학", "재무 관리", "기업 재무", "회계 분석", "세무"), Arrays.asList("회계학", "금융", "재무 분석"), false);
        Mentee mentee100 = new Mentee("박시영", Arrays.asList("인공지능", "기계 학습", "딥러닝", "자연어 처리", "알고리즘"), Arrays.asList("AI", "기계 학습", "딥러닝"), false);



        List<Mentor> mentors = Arrays.asList(mentor3, mentor4, mentor5, mentor6, mentor7, mentor8, mentor9, mentor10, mentor11, mentor12, mentor13, mentor14, mentor15, mentor16, mentor17, mentor18, mentor19, mentor20,mentor1,mentor2);
        List<Mentee> mentees = Arrays.asList(mentee1,mentee2,mentee3,mentee4,mentee5,mentee6,mentee7,mentee8,mentee9,mentee10,mentee11,mentee12,mentee13,mentee14,mentee15,mentee16,mentee17,mentee18,mentee19,mentee20,mentee21,mentee22,mentee23,mentee24,mentee25,mentee26,mentee27,mentee28,mentee29,mentee30,mentee31,mentee32,mentee33,mentee34,mentee35,mentee36,mentee37,mentee38,mentee39,mentee40,mentee41,mentee42,mentee43,mentee44,mentee45,mentee46,mentee47,mentee48,mentee49,mentee50,mentee51,mentee52,mentee53,mentee54,mentee55,mentee56,mentee57,mentee58,mentee59,mentee60,mentee61,mentee62,mentee63,mentee64,mentee65,mentee66,mentee67,mentee68,mentee69,mentee70,mentee71,mentee72,mentee73,mentee74,mentee75,mentee76,mentee77,mentee78,mentee79,mentee80,mentee81,mentee82,mentee83,mentee84,mentee85,mentee86,mentee87,mentee88,mentee89,mentee90,mentee91,mentee92,mentee93,mentee94,mentee95,mentee96,mentee97,mentee98,mentee99,mentee100);
        List<Match> matches = MentorMenteeMatcher.matchProfiles(mentors, mentees);
        for (Match match : matches) {
            System.out.println("멘토: " + match.getMentor().getName());
            System.out.println("멘티: ");
            for (Mentee mentee : match.getMentees()) {
                System.out.println(mentee.getName());
            }
            System.out.println();
        }
    }
}
