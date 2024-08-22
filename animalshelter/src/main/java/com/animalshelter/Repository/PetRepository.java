package com.animalshelter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animalshelter.Models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
