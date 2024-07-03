package com.example.seshatrpgauxiliary.domain.service;

import com.example.seshatrpgauxiliary.application.dto.CharacterDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterInventoryDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterSkillDTO;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Character;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.CharacterRepository;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final UserRepository userRepository;


    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }
    public List<CharacterDTO> getCharactersByUserId(Long userId) {
        List<Character> characters = characterRepository.findByUserId(userId);
        return characters.stream()
                .map(character -> new CharacterDTO(
                        character.getId(),
                        character.getName(),
                        character.getImage(),
                        character.getAttributes(),
                        character.getUser().getId(),
                        character.getUser().getName(),
                        character.getRace(),
                        character.getClassType()))
                .collect(Collectors.toList());
    }

    public List<CharacterInventoryDTO> getCharactersInventoryByUserId(Long userId) {
        List<Character> characters = characterRepository.findByUserId(userId);
        return characters.stream()
                .map(character -> new CharacterInventoryDTO(
                        character.getId(),
                        character.getName(),
                        character.getInventory()
                        ))
                .collect(Collectors.toList());
    }

    public List<CharacterSkillDTO> getCharactersSkillsByUserId(Long userId) {
        List<Character> characters = characterRepository.findByUserId(userId);
        return characters.stream()
                .map(character -> new CharacterSkillDTO(
                        character.getId(),
                        character.getName(),
                        character.getSkill()
                ))
                .collect(Collectors.toList());
    }



}
