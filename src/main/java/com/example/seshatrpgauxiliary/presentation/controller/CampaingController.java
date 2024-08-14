package com.example.seshatrpgauxiliary.presentation.controller;

import com.example.seshatrpgauxiliary.application.dto.CampaingRequestDTO;
import com.example.seshatrpgauxiliary.domain.service.CampaingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaings")
public class CampaingController {

    @Autowired
    private CampaingService campaingService;

    @PostMapping
    public ResponseEntity<CampaingRequestDTO> createCampaing(@RequestBody CampaingRequestDTO campaingRequestDTO) {
        return ResponseEntity.ok(campaingService.createCampaing(campaingRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaingRequestDTO> getCampaingById(@PathVariable Long id) {
        return ResponseEntity.ok(campaingService.getCampaingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaingRequestDTO> updateCampaing(@PathVariable Long id, @RequestBody CampaingRequestDTO campaingRequestDTO) {
        return ResponseEntity.ok(campaingService.updateCampaing(id, campaingRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaing(@PathVariable Long id) {
        campaingService.deleteCampaing(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{campaingId}/addUser")
    public ResponseEntity<Void> addUserToCampaing(@PathVariable Long campaingId, @RequestParam String userName) {
        campaingService.addUserToCampaing(userName, campaingId);
        return ResponseEntity.ok().build();
    }
}