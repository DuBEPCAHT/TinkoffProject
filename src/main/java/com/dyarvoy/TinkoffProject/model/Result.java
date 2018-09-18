package com.dyarvoy.TinkoffProject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 * Result entity for PostgreDB
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@Data
@Entity
@Table(name = "RESULT")
public class Result {

    @Id
    @Getter @Setter
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter @Setter
    @Column(name = "CODE")
    private String code;

    @Getter @Setter
    @Column(name = "NUMBER")
    private int number;

    @Getter @Setter
    @Column(name = "FILENAMES")
    private String fileNames;

    @Getter @Setter
    @Column(name = "ERROR")
    private String error;
}
