package com.example.seshatrpgauxiliary.infrastructure.persistence.repository;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Long> {
}
