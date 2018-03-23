package com.squidward.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="sprint")
@Getter @Setter @ToString
public class Sprint implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="sprint_id")
    private int id;

    @Column(name="sprint_number")
    private int number;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="project_id")
    private Project project;

    @Column(name="release")
    private boolean release;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;
}
