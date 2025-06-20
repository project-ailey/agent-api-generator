package com.ailey.aime.agentapigenerator.domain.repository;

import com.ailey.aime.agentapigenerator.domain.entity.Snippet;
import com.ailey.aime.agentapigenerator.domain.vo.SnippetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SnippetRepository extends JpaRepository<Snippet, String> {
    // ret only the names of all snippets
    @Query("SELECT s.name FROM Snippet s WHERE s.type = :type")
    List<String> findAllNamesByType(@Param("type") SnippetType type);

    Optional<Snippet> findByName(String name);
}
