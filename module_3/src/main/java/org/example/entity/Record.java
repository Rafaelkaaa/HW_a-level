package org.example.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.UUID;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Record {
    int amount;
    UUID accountId;
    Timestamp date;
    String operation;
}
