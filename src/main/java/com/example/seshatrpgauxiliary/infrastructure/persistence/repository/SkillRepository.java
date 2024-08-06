package com.example.seshatrpgauxiliary.infrastructure.persistence.repository;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByCharacterId(Long characterId);
}
