package com.example.seshatrpgauxiliary.presentation.controller;
import com.example.seshatrpgauxiliary.application.dto.CharacterDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterInventoryDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterSkillDTO;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
import com.example.seshatrpgauxiliary.presentation.request.CharacterCreationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/inventory/{userId}")
    public List<CharacterInventoryDTO> getCharactersInventoryByUserId(@PathVariable Long userId) {
        return characterService.getCharactersInventoryByUserId(userId);
    }

    @GetMapping("/skills/{userId}")
    public List<CharacterSkillDTO> getCharactersSkillsByUserId (@PathVariable Long userId) {
        return characterService.getCharactersSkillsByUserId(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterCreationRequest request) {
        CharacterDTO createdCharacter = characterService.createCharacter(request);
        return new ResponseEntity<>(createdCharacter , HttpStatus.OK);
    }
}