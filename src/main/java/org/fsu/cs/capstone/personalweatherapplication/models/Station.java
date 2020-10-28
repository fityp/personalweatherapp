package org.fsu.cs.capstone.personalweatherapplication.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.ZonedDateTime;


@Data
public class Station {
    private int station_id;
    private String stationtype;
    private String model;
    private String freq;
    private String PASSKEY;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime ZonedDateTime;
    private ZonedDateTime date_created;

}
