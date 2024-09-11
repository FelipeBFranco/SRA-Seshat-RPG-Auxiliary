package com.example.seshatrpgauxiliary.presentation.controller;
import com.example.seshatrpgauxiliary.application.dto.CharacterDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterInventoryDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterSkillDTO;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
import com.example.seshatrpgauxiliary.presentation.request.CharacterCreationRequest;
import com.example.seshatrpgauxiliary.presentation.request.CharacterUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{userId}")
    public List<CharacterDTO> getCharactersByUserId(@PathVariable Long userId) {
        return characterService.getCharactersByUserId(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterCreationRequest request) {
        CharacterDTO createdCharacter = characterService.createCharacter(request);
        return new ResponseEntity<>(createdCharacter , HttpStatus.OK);
    }


    @PutMapping("/update/{characterId}")
    public ResponseEntity<CharacterUpdateRequest> updateCharacter(@PathVariable Long characterId, @RequestBody CharacterUpdateRequest request) {
        CharacterUpdateRequest updatedCharacter = characterService.updateCharacter(characterId, request);
        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/delete/{characterId}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long characterId) {
        characterService.deleteCharacter(characterId);
        return ResponseEntity.noContent().build();
    }
}