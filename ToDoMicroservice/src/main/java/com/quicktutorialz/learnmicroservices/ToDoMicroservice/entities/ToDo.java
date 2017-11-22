package com.quicktutorialz.learnmicroservices.ToDoMicroservice.entities;

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
@Table(name="todos")
@AllArgsConstructor @NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    @Getter @Setter
    private Integer id;

    @Column(name="DESCPRIPTION")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String description;

    @Column(name="DATE")
    @Getter @Setter
    private Date date;

    @Column(name="PRIORITY")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String priority;

    @Column(name="FK_USER")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String fkUser;

    @PrePersist
    void getTimeOperation(){
        this.date = new Date();
    }


}
