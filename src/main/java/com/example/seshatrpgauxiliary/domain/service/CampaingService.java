package com.example.seshatrpgauxiliary.domain.service;

import com.example.seshatrpgauxiliary.application.dto.CampaingRequestDTO;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Campaing;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.User;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.CampaingRepository;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaingService {

    private final CampaingRepository campaingRepository;

    private final UserRepository userRepository;

    public CampaingService(CampaingRepository campaingRepository, UserRepository userRepository) {
        this.campaingRepository = campaingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CampaingRequestDTO createCampaing(CampaingRequestDTO campaingRequestDTO) {
        Campaing campaing = new Campaing();
        campaing.setName(campaingRequestDTO.name());
        campaing.setDescription(campaingRequestDTO.description());
        campaing.setGm(campaingRequestDTO.gm());
        Campaing savedCampaing = campaingRepository.save(campaing);
        return new CampaingRequestDTO(
                savedCampaing.getId(),
                savedCampaing.getName(),
                savedCampaing.getDescription(),
                savedCampaing.getGm());
    }

    @Transactional(readOnly = true)
    public CampaingRequestDTO getCampaingById(Long id) {
        Campaing campaing = campaingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaing not found."));
        return new CampaingRequestDTO(
                campaing.getId(),
                campaing.getName(),
                campaing.getDescription(),
                campaing.getGm());
    }

    @Transactional
    public CampaingRequestDTO updateCampaing(Long id, CampaingRequestDTO campaingRequestDTO) {
        Campaing campaing = campaingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaing not found."));
        campaing.setName(campaingRequestDTO.name());
        campaing.setDescription(campaingRequestDTO.description());
        campaing.setGm(campaingRequestDTO.gm());
        Campaing updatedCampaing = campaingRepository.save(campaing);
        return new CampaingRequestDTO(
                updatedCampaing.getId(),
                updatedCampaing.getName(),
                updatedCampaing.getDescription(),
                updatedCampaing.getGm());
    }

    @Transactional
    public void deleteCampaing(Long id) {
        Campaing campaing = campaingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaing not found."));
        campaingRepository.delete(campaing);
    }

    @Transactional
    public void addUserToCampaing(String userName, Long campaingId) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new RuntimeException("User not found."));
        Campaing campaing = campaingRepository.findById(campaingId)
                .orElseThrow(() -> new RuntimeException("Campaing not found."));

        user.getCampaing().add(campaing);
        userRepository.save(user);
    }
}