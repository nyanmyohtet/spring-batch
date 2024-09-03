package com.nyanmyohtet.springbatch.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Double trxAmount;
    private String description;
    private LocalDate trxDate;
    private LocalTime trxTime;
    private String customerId;
    @Version
    @JsonIgnore
    private Integer version;
}
