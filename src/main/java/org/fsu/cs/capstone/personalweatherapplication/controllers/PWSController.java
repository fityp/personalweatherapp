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

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PWSController {
    private static final int ALLOWED_UPDATE_FREQUENCY = 1*60*1000;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    StationDataRepository stationDataRepository;

    @PostMapping ("/data/report")
    public void greetingJsonPost(PWSData pwsData) throws Exception {

        Optional<Station> ostation = stationRepository.findByPasskey(pwsData.getPASSKEY());
        Station station = null;
        if (ostation.isEmpty()){
            System.out.println(pwsData.getPASSKEY());
            return;
        }
        if (!ostation.get().registrationComplete()) {
            station = Station.builder()
                    .stationType(pwsData.getStationtype())
                    .model(pwsData.getModel())
                    .freq(pwsData.getFreq())
                    .passkey(pwsData.getPASSKEY())
                    .dateCreated(Calendar.getInstance().getTime()).build();

            station = stationRepository.saveAndFlush(station);
        }
        StationData mostRecentStationData = stationDataRepository.findFirstByStationOrderByGetheredDateDesc(ostation.get());

        if (null != mostRecentStationData){
            if(pwsData.getDateutc().getTime() - mostRecentStationData.getGetheredDate().getTime() < 1*60*1000) return;
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
                .station(ostation.get()).build();

        stationDataRepository.saveAndFlush(newStationData);
        System.out.println(pwsData);

    }
}
