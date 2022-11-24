package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.model.RecordForm;

import java.util.List;

public interface RecordService {
    boolean saveRecord(Record record);

    List<Record> findRecords(RecordForm recordForm);
}
