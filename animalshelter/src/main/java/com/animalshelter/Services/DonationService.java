package com.animalshelter.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.animalshelter.Models.Donation;
import com.animalshelter.Repository.DonationRepository;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    /**
     * Retrieve all donations from the database.
     * 
     * @return List of all donations.
     */
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    /**
     * Retrieve a donation by its ID.
     * 
     * @param id The ID of the donation.
     * @return An Optional containing the donation if found, otherwise empty.
     */
    public ResponseEntity<Donation> getDonationById(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if (donation.isPresent()) {
            return ResponseEntity.ok(donation.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Save a donation to the database.
     * 
     * @param donation The donation object to save.
     * @return The saved donation.
     */
    public ResponseEntity<Donation> saveDonation(Donation donation) {
        if (donation.getAmount() == null || donation.getAmount() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        Donation savedDonation = donationRepository.save(donation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonation);
    }

    /**
     * Delete a donation by its ID.
     * 
     * @param id The ID of the donation to delete.
     * @return Response indicating whether the deletion was successful.
     */
    public ResponseEntity<Void> deleteDonation(Long id) {
        if (donationRepository.existsById(id)) {
            donationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
