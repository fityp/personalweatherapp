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
public class StationData {

    @Id
    Integer stationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getheredDate;
    private float tempinf;
    private float humidityin;
    private float baromrelin;
    private float baromabsin;
    private float tempf;
    private float humidity;
    private float winddir;
    private float windspeedmph;
    private float windgustmph;
    private float maxdailygust;
    private float solarradiation;
    private float uv;
    private float rainratein;
    private float eventrainin;
    private float hourlyrainin;
    private float dailyrainin;
    private float weeklyrainin;
    private float monthlyrainin;
    private float yearlyrainin;
    private float totalrainin;
    private float wh65batt;

    public StationData() {

    }
}
