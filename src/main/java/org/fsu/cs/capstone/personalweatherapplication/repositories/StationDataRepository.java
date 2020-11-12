package org.fsu.cs.capstone.personalweatherapplication.repositories;

import org.fsu.cs.capstone.personalweatherapplication.models.Station;
import org.fsu.cs.capstone.personalweatherapplication.models.StationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface StationDataRepository extends JpaRepository<StationData, Integer> {
    List<StationData> findByGetheredDateBetween(Date startDate, Date endDate);
    StationData findFirstByStationOrderByGetheredDateDesc (Station station);
}
