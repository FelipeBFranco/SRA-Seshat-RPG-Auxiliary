package com.example.seshatrpgauxiliary.infrastructure.persistence.repository;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCharacterId(Long characterId);
}
