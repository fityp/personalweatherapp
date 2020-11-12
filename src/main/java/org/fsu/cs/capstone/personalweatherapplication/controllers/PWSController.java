package org.fsu.cs.capstone.personalweatherapplication.controllers;

import org.fsu.cs.capstone.personalweatherapplication.models.PWSData;
import org.fsu.cs.capstone.personalweatherapplication.models.Station;
import org.fsu.cs.capstone.personalweatherapplication.models.StationData;
import org.fsu.cs.capstone.personalweatherapplication.repositories.StationDataRepository;
import org.fsu.cs.capstone.personalweatherapplication.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PWSController {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    StationDataRepository stationDataRepository;

    @PostMapping ("/data/report")
    public void greetingJsonPost(PWSData pwsData) throws Exception {

        Station station = stationRepository.findByPasskey(pwsData.getPASSKEY());
        if (station == null) {
            station = Station.builder()
                    .stationType(pwsData.getStationtype())
                    .model(pwsData.getModel())
                    .freq(pwsData.getFreq())
                    .passkey(pwsData.getPASSKEY()).build();

            station = stationRepository.saveAndFlush(station);
        }

        StationData newStationData = StationData.builder()
                .getheredDate(pwsData.getDateutc())
                .tempinf(pwsData.getTempinf())
                .humidityin(pwsData.getHumidityin())
                .baromrelin(pwsData.getBaromrelin())
                .baromabsin(pwsData.getBaromabsin())
                .tempf(pwsData.getTempf())
                .humidity(pwsData.getHumidity())
                .winddir(pwsData.getWinddir())
                .windgustmph(pwsData.getWindgustmph())
                .windspeedmph(pwsData.getWindspeedmph())
                .maxdailygust(pwsData.getMaxdailygust())
                .solarradiation(pwsData.getSolarradiation())
                .uv(pwsData.getUv())
                .rainratein(pwsData.getRainratein())
                .eventrainin(pwsData.getEventrainin())
                .hourlyrainin(pwsData.getHourlyrainin())
                .dailyrainin(pwsData.getDailyrainin())
                .weeklyrainin(pwsData.getWeeklyrainin())
                .monthlyrainin(pwsData.getMonthlyrainin())
                .yearlyrainin(pwsData.getYearlyrainin())
                .totalrainin(pwsData.getTotalrainin())
                .wh65batt(pwsData.getWh65batt())
                .stationId(station.getStation_id()).build();

        stationDataRepository.saveAndFlush(newStationData);
        System.out.println(pwsData);

    }
}
