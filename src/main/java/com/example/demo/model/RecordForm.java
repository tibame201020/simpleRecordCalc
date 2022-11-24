package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RecordForm implements Serializable {
    private String filterKey;
    private String category;
    private int startTime;
    private int endTime;
    private String item;
}
