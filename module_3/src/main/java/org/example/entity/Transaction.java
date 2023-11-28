package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transactions")
@Getter
@Setter
@ToString
public class Transaction extends BaseEntity {
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    Set<Transfer> transfers;

    @Positive(message = "Transfer amount should be biggest than 0")
    @Column(name = "transaction_amount")
    int transactionAmount;

    @Basic
    Timestamp date;
}
