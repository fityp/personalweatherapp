package org.fsu.cs.capstone.personalweatherapplication.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class PWSData {
    private String PASSKEY;
    private String stationtype;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateutc;
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
    private String freq;
    private String model;

    public void setDateutc(String dateutc) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(dateutc);
            ZonedDateTime zdt = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
            System.out.println(zdt.format(DateTimeFormatter.ofPattern(pattern)));
            this.dateutc=date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
