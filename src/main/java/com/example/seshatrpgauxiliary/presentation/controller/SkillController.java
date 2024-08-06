package com.example.seshatrpgauxiliary.presentation.controller;

import com.example.seshatrpgauxiliary.application.dto.SkillDTO;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final CharacterService characterService;

    public SkillController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/character/{characterId}")
    public List<SkillDTO> getSkillsByCharacterId(@PathVariable Long characterId) {
        return characterService.getSkillsByCharacterId(characterId);
    }

    @PostMapping("/character/{characterId}")
    public ResponseEntity<SkillDTO> createSkill(@PathVariable Long characterId, @RequestBody SkillDTO skillDTO) {
        SkillDTO createdSkill = characterService.createSkill(characterId, skillDTO);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long skillId, @RequestBody SkillDTO skillDTO) {
        SkillDTO updatedSkill = characterService.updateSkill(skillId, skillDTO);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) {
        characterService.deleteSkill(skillId);
        return ResponseEntity.noContent().build();
    }
}
