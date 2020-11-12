package org.fsu.cs.capstone.personalweatherapplication.repositories;

import org.fsu.cs.capstone.personalweatherapplication.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Optional<Station> findByPasskey(String passkey);
}
