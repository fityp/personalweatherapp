package org.fsu.cs.capstone.personalweatherapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@Builder
@Entity
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer station_id;
    private String stationType;
    private String model;
    private String freq;
    private String passkey;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date_created;

    public Station() {

    }
}
