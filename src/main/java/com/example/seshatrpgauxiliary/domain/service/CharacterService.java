package com.example.seshatrpgauxiliary.domain.service;

import com.example.seshatrpgauxiliary.application.dto.*;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Character;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Inventory;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Skill;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.*;
import com.example.seshatrpgauxiliary.presentation.request.CharacterCreationRequest;
import com.example.seshatrpgauxiliary.presentation.request.CharacterUpdateRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final UserRepository userRepository;

    private final AttributesRepository attributesRepository;

    private final InventoryRepository inventoryRepository;

    private final SkillRepository skillRepository;


    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository, AttributesRepository attributesRepository, InventoryRepository inventoryRepository, SkillRepository skillRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
        this.attributesRepository = attributesRepository;
        this.inventoryRepository = inventoryRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    public CharacterUpdateRequest updateCharacter(Long characterId, CharacterUpdateRequest request) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found."));

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
        if (request.getExperience() != null) character.getAttributes().setExperience(request.getExperience());
        if (request.getStaminaMax() != null) character.getAttributes().setStaminaMax(request.getStaminaMax());
        if (request.getHealthMax() != null) character.getAttributes().setHealthMax(request.getHealthMax());
        if (request.getAmalgamaMax() != null) character.getAttributes().setAmalgamaMax(request.getAmalgamaMax());
        if (request.getManaMax() != null) character.getAttributes().setManaMax(request.getManaMax());
        if (request.getAmalgama() != null) character.getAttributes().setAmalgama(request.getAmalgama());
        if (request.getMana() != null) character.getAttributes().setMana(request.getMana());

        character = characterRepository.save(character);

        return new CharacterUpdateRequest(character.getUser().getId(), character.getName(), character.getRace(),
                character.getClassType(), character.getAttributes().getLevel(), character.getAttributes().getHealth(),
                character.getAttributes().getStamina(), character.getAttributes().getAmalgama(),
                character.getAttributes().getMana(), character.getAttributes().getStrength(),
                character.getAttributes().getAgility(), character.getAttributes().getIntelligence(),
                character.getAttributes().getMind(), character.getAttributes().getBlock(),
                character.getAttributes().getDodge(), character.getAttributes().getDetermination(),
                character.getCampaign(), character.getAttributes().getExperience(),
                character.getAttributes().getStaminaMax(), character.getAttributes().getHealthMax(),
                character.getAttributes().getAmalgamaMax(), character.getAttributes().getManaMax());
    }

    @Transactional
    public void deleteCharacter(Long characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found."));
        characterRepository.delete(character);
    }

    @Transactional
    public List<InventoryDTO> getInventoriesByCharacterId(Long characterId) {
        return inventoryRepository.findByCharacterId(characterId).stream()
                .map(inventory -> new InventoryDTO(
                        inventory.getId(),
                        inventory.getName(),
                        inventory.getQuantity(),
                        inventory.getDescription(),
                        inventory.getEnergy(),
                        inventory.getCharacter().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public InventoryDTO createInventory(Long characterId, InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setName(inventoryDTO.getName());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setDescription(inventoryDTO.getDescription());
        inventory.setEnergy(inventoryDTO.getEnergy());
        inventory.setCharacter(characterRepository.findById(characterId).orElseThrow(() -> new RuntimeException("Character not found.")));
        Inventory savedInventory = inventoryRepository.save(inventory);
        return new InventoryDTO(
                savedInventory.getId(),
                savedInventory.getName(),
                savedInventory.getQuantity(),
                savedInventory.getDescription(),
                savedInventory.getEnergy(),
                savedInventory.getCharacter().getId());
    }

    @Transactional
    public InventoryDTO updateInventory(Long inventoryId, InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found."));
        inventory.setName(inventoryDTO.getName());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setDescription(inventoryDTO.getDescription());
        inventory.setEnergy(inventoryDTO.getEnergy());
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return new InventoryDTO(
                updatedInventory.getId(),
                updatedInventory.getName(),
                updatedInventory.getQuantity(),
                updatedInventory.getDescription(),
                updatedInventory.getEnergy(),
                updatedInventory.getCharacter().getId());
    }

    @Transactional
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found."));
        inventoryRepository.delete(inventory);
    }

    @Transactional
    public List<SkillDTO> getSkillsByCharacterId(Long characterId) {
        return skillRepository.findByCharacterId(characterId).stream()
                .map(skill -> new SkillDTO(
                        skill.getId(),
                        skill.getName(),
                        skill.getDescription(),
                        skill.getEnergy(),
                        skill.getCharacter().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public SkillDTO createSkill(Long characterId, SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setName(skillDTO.getName());
        skill.setDescription(skillDTO.getDescription());
        skill.setEnergy(skillDTO.getEnergy());
        skill.setCharacter(characterRepository.findById(characterId).orElseThrow(() -> new RuntimeException("Character not found.")));
        Skill savedSkill = skillRepository.save(skill);
        return new SkillDTO(
                savedSkill.getId(),
                savedSkill.getName(),
                savedSkill.getDescription(),
                savedSkill.getEnergy(),
                savedSkill.getCharacter().getId());
    }

    @Transactional
    public SkillDTO updateSkill(Long skillId, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found."));
        skill.setName(skillDTO.getName());
        skill.setDescription(skillDTO.getDescription());
        skill.setEnergy(skillDTO.getEnergy());
        Skill updatedSkill = skillRepository.save(skill);
        return new SkillDTO(
                updatedSkill.getId(),
                updatedSkill.getName(),
                updatedSkill.getDescription(),
                updatedSkill.getEnergy(),
                updatedSkill.getCharacter().getId());
    }

    @Transactional
    public void deleteSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found."));
        skillRepository.delete(skill);
    }

}

