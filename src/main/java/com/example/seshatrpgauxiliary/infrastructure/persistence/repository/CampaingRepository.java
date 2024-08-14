package com.example.seshatrpgauxiliary.infrastructure.persistence.repository;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Campaing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaingRepository extends JpaRepository<Campaing, Long> {

}
