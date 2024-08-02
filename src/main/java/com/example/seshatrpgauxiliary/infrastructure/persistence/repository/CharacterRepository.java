package com.example.seshatrpgauxiliary.infrastructure.persistence.repository;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Character;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUserId(Long userId);
    Optional<Character> findById (Long id);
}
