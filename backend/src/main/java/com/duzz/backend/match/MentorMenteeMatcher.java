package com.duzz.backend.match;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MentorMenteeMatcher {

    // 멘토-멘티 매칭
    public static Mentor matchProfiles(List<Mentor> mentors, Mentee mentee) {
        String url = "http://localhost:5000/api/receive"; // Flask 서버 URL
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        // MentorshipData 객체 생성
        MentorshipData mentorshipData = new MentorshipData(mentors, mentee);

        // 요청 헤더 설정 (UTF-8 인코딩)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        try {
            // MentorshipData 객체를 JSON으로 변환
            String json = objectMapper.writeValueAsString(mentorshipData);
            System.out.println("Serialized JSON: " + json);

            // HTTP 요청 생성
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            // Flask 서버로 POST 요청 보내기
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            // 서버 응답 확인
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Response from Flask server: " + response.getBody());

                // JSON 응답 파싱
                JsonNode responseJson = objectMapper.readTree(response.getBody());
                System.out.println("Response JSON data: " + responseJson.get("data").toString());

                if ("success".equals(responseJson.get("status").asText())) {
                    // "data" 필드에서 Mentor 객체 생성
                    Mentor bestMatch = objectMapper.treeToValue(responseJson.get("data"), Mentor.class);
                    // Match 객체 생성 및 반환
                    System.out.println("Best match: " + bestMatch.name);
                    return bestMatch;
                } else {
                    System.err.println("Error from Flask server: " + responseJson.get("message").asText());
                }
            }
        } catch (Exception e) {
            System.err.println("Error during communication with Flask server: " + e.getMessage());
        }
        return null;
    }

    // 내부 클래스: MentorshipData
    @Getter
    static class MentorshipData {
        private final List<Mentor> mentors;
        private final Mentee mentee;

        public MentorshipData(List<Mentor> mentors, Mentee mentee) {
            this.mentors = mentors;
            this.mentee = mentee;
        }
    }
}
