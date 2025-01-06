package com.duzz.backend.match;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MentorMenteeMatcher {

    // 멘토-멘티 매칭
    public static List<Match> matchProfiles(List<Mentor> mentors, List<Mentee> mentees) {
        String url = "http://211.109.126.86:5000/api/receive"; // Flask 서버 URL
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        // 요청 헤더 설정 (UTF-8 인코딩)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT_CHARSET, "UTF-8");

        // MentorshipData 객체 생성
        MentorshipData mentorshipData = new MentorshipData(mentors, mentees);

        try {
            // MentorshipData 객체를 JSON으로 변환
            String json = objectMapper.writeValueAsString(mentorshipData);
            System.out.println("Serialized JSON: " + json);

            // HTTP 요청 생성
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            // Flask 서버로 POST 요청 보내기
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Response from Flask server: " + response.getBody());

                // JSON 응답 파싱
                JsonNode responseJson = objectMapper.readTree(response.getBody());

                if ("success".equals(responseJson.get("status").asText())) {
                    // 매칭 결과 파싱
                    List<Match> matchResults = new ArrayList<>();
                    JsonNode resultNode = responseJson.get("result");

                    for (JsonNode matchNode : resultNode) {
                        // Mentor 정보 파싱
                        JsonNode mentorNode = matchNode.get("mentor");
                        String mentorName = mentorNode.get("name").asText();
                        String id = mentorNode.get("id").asText();
                        String major = mentorNode.get("major").asText();
                        JsonNode mentorSubjectsNode = mentorNode.get("subjects");
                        List<String> mentorSubjects = new ArrayList<>();
                        for (JsonNode subject : mentorSubjectsNode) {
                            mentorSubjects.add(subject.asText());
                        }

                        // 멘토 객체 생성
                        Mentor mentor = new Mentor(mentorName, id, major, mentorSubjects);

                        JsonNode menteeNodes = matchNode.get("mentees");

                        // 멘티 리스트 생성
                        List<Mentee> menteeList = new ArrayList<>();
                        for (JsonNode menteeNode : menteeNodes) {
                            String menteeName = menteeNode.get("name").asText();
                            String menteeId = menteeNode.get("id").asText();
                            JsonNode menteeInterestsNode = menteeNode.get("interests");
                            List<String> menteeInterests = new ArrayList<>();
                            for (JsonNode interest : menteeInterestsNode) {
                                menteeInterests.add(interest.asText());
                            }

                            JsonNode menteeSubjectsNode = menteeNode.get("subjects");
                            List<String> menteeSubjects = new ArrayList<>();
                            for (JsonNode subject : menteeSubjectsNode) {
                                menteeSubjects.add(subject.asText());
                            }

                            boolean menteeOption = menteeNode.get("option").asBoolean();

                            // 멘티 객체 생성
                            Mentee mentee = new Mentee(menteeName,menteeId,menteeInterests, menteeSubjects, menteeOption);
                            menteeList.add(mentee);
                        }

                        // Match 객체 추가
                        matchResults.add(new Match(mentor, menteeList));
                    }

                    return matchResults;
                } else {
                    System.err.println("Error from Flask server: " + responseJson.get("message").asText());
                    return new ArrayList<>();
                }
            }


        } catch (Exception e) {
            System.err.println("Error during communication with Flask server: " + e.getMessage());
        }

        return new ArrayList<>(); // 매칭 실패 시 빈 리스트 반환
    }

    // 내부 클래스: MentorshipData
    record MentorshipData(List<Mentor> mentors, List<Mentee> mentees) {
    }


}
