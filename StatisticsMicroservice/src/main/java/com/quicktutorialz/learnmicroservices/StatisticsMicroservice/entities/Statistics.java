package com.quicktutorialz.learnmicroservices.StatisticsMicroservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="latest_statistics")
@AllArgsConstructor @NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    @Getter @Setter
    private Integer Id;

    @Column(name="DESCRIPTION")
    @Getter @Setter
    @NotBlank @NotEmpty @NotNull
    private String description;

    @Column(name="DATE")
    @Getter @Setter
    private Date date;

    @Column(name="EMAIL")
    @Getter @Setter
    @NotBlank @NotEmpty @NotNull
    private String email;

    @PrePersist
    private void getTimeOperation(){
        this.date = new Date();
    }

}
