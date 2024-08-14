package com.example.seshat;

import com.example.seshatrpgauxiliary.application.dto.CharacterDTO;
import com.example.seshatrpgauxiliary.domain.service.CharacterService;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Character;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.User;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AttributesRepository attributesRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private CharacterService characterService;

    @Test
    void getCharactersByUserId_ReturnsCharacterDTOList_WhenCharactersExist() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("Test User");

        List<Character> characters = List.of(
                new Character(1L, "Character1", "image1", new Attributes(), new ArrayList<>(), new ArrayList<>(), user, "Race1", "Class1", "Campaign1"),
                new Character(2L, "Character2", "image2", new Attributes(), new ArrayList<>(), new ArrayList<>(), user, "Race2", "Class2", "Campaign2")
        );

        when(characterRepository.findByUserId(userId)).thenReturn(characters);

        List<CharacterDTO> result = characterService.getCharactersByUserId(userId);

        assertEquals(2, result.size());
        assertEquals("Character1", result.get(0).getName());
        assertEquals("Character2", result.get(1).getName());
    }

    @Test
    void getCharactersByUserId_ReturnsEmptyList_WhenNoCharactersExist() {
        Long userId = 1L;

        when(characterRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        List<CharacterDTO> result = characterService.getCharactersByUserId(userId);

        assertTrue(result.isEmpty());
    }
}