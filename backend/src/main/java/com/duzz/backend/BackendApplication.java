package com.duzz.backend;

import com.duzz.backend.entity.Subject;
import com.duzz.backend.other.SubjectTime;
import com.duzz.backend.util.CsvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.DayOfWeek;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
