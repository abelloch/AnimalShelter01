package com.animalshelter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animalshelter.Models.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    /**
     * Find donations by the donor's name.
     * 
     * @param donorName The name of the donor.
     * @return A list of donations made by the given donor.
     */
    List<Donation> findByDonorName(String donorName);

    // Puedes añadir más métodos personalizados según sea necesario.
}
