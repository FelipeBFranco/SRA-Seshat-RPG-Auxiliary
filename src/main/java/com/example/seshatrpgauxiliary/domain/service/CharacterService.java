package com.example.seshatrpgauxiliary.domain.service;

import com.example.seshatrpgauxiliary.application.dto.CharacterDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterInventoryDTO;
import com.example.seshatrpgauxiliary.application.dto.CharacterSkillDTO;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Character;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.AttributesRepository;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.CharacterRepository;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.UserRepository;
import com.example.seshatrpgauxiliary.presentation.request.CharacterCreationRequest;
import com.example.seshatrpgauxiliary.presentation.request.CharacterUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final UserRepository userRepository;

    private final AttributesRepository attributesRepository;


    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository, AttributesRepository attributesRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
        this.attributesRepository = attributesRepository;
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
                        character.getClassType(),
                        character.getCampaign()))
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

    public CharacterDTO createCharacter(CharacterCreationRequest request) {
        // Cria uma nova instância de Attributes com os dados do request
        Attributes attributes = new Attributes(null, request.getLevel(), 0, // Supondo que experience comece do zero
                request.getHealth(), request.getStamina(), 0, // Mana e Amalgama iniciam como 0 para simplificar
                0, 0, // StaminaMax e AmalgamaMax não especificados no request
                request.getStrength(), request.getAgility(), request.getIntelligence(),
                request.getMind(), request.getBlock(), request.getDodge(),
                request.getDetermination(), request.getHealth(), // HealthMax igual ao Health inicial
                0, // ManaMax não especificado no request
                request.getStamina()); // StaminaMax igual ao Stamina inicial
        // Salva os atributos no banco de dados
        attributes = attributesRepository.save(attributes);

        // Cria uma nova instância de Character com os dados do request e os Attributes salvos
        Character character = new Character(null, request.getName(), request.getImage(), attributes, new ArrayList<>(), // Inventory vazio inicialmente
                new ArrayList<>(), // Skills vazio inicialmente
                userRepository.findById(request.getUserId()).orElse(null), // Busca o User pelo ID
                request.getRace(), request.getClassType(), request.getCampaign());

        // Salva o personagem no banco de dados
        character = characterRepository.save(character);

        // Converte o Character salvo para DTO e retorna
        return new CharacterDTO(character.getId(), character.getName(), character.getImage(),
                character.getAttributes(), character.getUser().getId(), character.getUser().getName(),
                character.getRace(), character.getClassType(), character.getCampaign());
    }

    public CharacterDTO updateCharacter(Long characterId, CharacterUpdateRequest request) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));

        if (request.getName() != null) character.setName(request.getName());
        if (request.getLevel() != null) character.getAttributes().setLevel(request.getLevel());
        if (request.getHealth() != null) character.getAttributes().setHealth(request.getHealth());
        if (request.getStamina() != null) character.getAttributes().setStamina(request.getStamina());
        if (request.getStrength() != null) character.getAttributes().setStrength(request.getStrength());
        if (request.getAgility() != null) character.getAttributes().setAgility(request.getAgility());
        if (request.getIntelligence() != null) character.getAttributes().setIntelligence(request.getIntelligence());
        if (request.getMind() != null) character.getAttributes().setMind(request.getMind());
        if (request.getBlock() != null) character.getAttributes().setBlock(request.getBlock());
        if (request.getDodge() != null) character.getAttributes().setDodge(request.getDodge());
        if (request.getDetermination() != null) character.getAttributes().setDetermination(request.getDetermination());
        if (request.getRace() != null) character.setRace(request.getRace());
        if (request.getClassType() != null) character.setClassType(request.getClassType());
        if (request.getCampaign() != null) character.setCampaign(request.getCampaign());

        character = characterRepository.save(character);

        return new CharacterDTO(character.getId(), character.getName(), character.getImage(),
                character.getAttributes(), character.getUser().getId(), character.getUser().getName(),
                character.getRace(), character.getClassType(), character.getCampaign());
    }



}
