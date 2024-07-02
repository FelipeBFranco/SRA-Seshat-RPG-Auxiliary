package com.example.seshatrpgauxiliary.presentation.controller;
import com.example.seshatrpgauxiliary.application.dto.CharacterDto;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
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
    public List<CharacterDto> getCharactersByUserId(@PathVariable Long userId) {
        return characterService.getCharactersByUserId(userId);
    }
}