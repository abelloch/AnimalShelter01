package com.animalshelter.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animalshelter.Models.Donation;
import com.animalshelter.Services.DonationService;

@RestController
@RequestMapping("/api/donations")
@Validated // Asegura que las validaciones en el cuerpo de la solicitud se apliquen
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.getDonationById(id);
        return donation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody @Validated Donation donation) {
        Donation savedDonation = donationService.saveDonation(donation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody @Validated Donation donation) {
        Optional<Donation> existingDonation = donationService.getDonationById(id);
        if (existingDonation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asume que la entidad Donation tiene un método `setId(Long id)`
        donation.setId(id); // Asegúrate de que tu clase Donation tenga un método `setId`
        Donation updatedDonation = donationService.saveDonation(donation);
        return ResponseEntity.ok(updatedDonation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        Optional<Donation> existingDonation = donationService.getDonationById(id);
        if (existingDonation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}
