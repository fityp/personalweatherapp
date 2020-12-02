package org.fsu.cs.capstone.personalweatherapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@Builder
@Entity
@AllArgsConstructor
public class Station {

    @Id
    private String passkey;
    @ManyToOne
    User stationUser;
    private String stationType;
    private String model;
    private String freq;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    public Station() {

    }

    public boolean registrationComplete(){
        return this.stationType != null &&
                this.model != null &&
                this.freq != null &&
                this.dateCreated != null;

    }
}
