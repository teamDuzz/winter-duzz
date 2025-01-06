package com.duzz.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachFile extends BaseTimeEntity {
    @Id
    @Column(length = 255, nullable = false)
    private String uuid;
    @Column(length = 255, nullable = false)
    private String fileName;
    @Column(length = 255, nullable = false)
    private String originalFileName;
}
