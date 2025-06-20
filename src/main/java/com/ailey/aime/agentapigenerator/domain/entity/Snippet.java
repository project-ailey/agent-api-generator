package com.ailey.aime.agentapigenerator.domain.entity;

import com.ailey.aime.agentapigenerator.domain.vo.SnippetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "snippet",
        indexes = {
                @Index(name = "idx_snippet_type", columnList = "type")
        })
public class Snippet {
    @Id
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    private SnippetType type;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String snippet;
}
