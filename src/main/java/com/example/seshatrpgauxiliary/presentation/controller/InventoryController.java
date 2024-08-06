package com.example.seshatrpgauxiliary.presentation.controller;

import com.example.seshatrpgauxiliary.application.dto.InventoryDTO;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final CharacterService characterService;

    public InventoryController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/character/{characterId}")
    public List<InventoryDTO> getInventoriesByCharacterId(@PathVariable Long characterId) {
        return characterService.getInventoriesByCharacterId(characterId);
    }

    @PostMapping("/character/{characterId}")
    public ResponseEntity<InventoryDTO> createInventory(@PathVariable Long characterId, @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO createdInventory = characterService.createInventory(characterId, inventoryDTO);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long inventoryId, @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO updatedInventory = characterService.updateInventory(inventoryId, inventoryDTO);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long inventoryId) {
        characterService.deleteInventory(inventoryId);
        return ResponseEntity.noContent().build();
    }
}