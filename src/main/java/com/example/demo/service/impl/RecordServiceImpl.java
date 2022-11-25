package com.example.demo.service.impl;

import com.example.demo.model.Record;
import com.example.demo.model.RecordForm;
import com.example.demo.repo.RecordRepo;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepo recordRepo;

    @Override
    public boolean saveRecord(Record record) {
        try {

            if (record.getId() != null && record.getId() == -1) {
                record.setId(null);
            }

            record.setRecordTime(checkDate(record.getRecordTime()));
            recordRepo.save(record);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Record> findRecords(RecordForm recordForm) {
        String filterKey = recordForm.getFilterKey();
        List<Record> originalRecords = (filterKey == null || filterKey.trim().isEmpty()) ?
                recordRepo.findAll() : recordRepo.findAll().stream().filter(record -> record.toString().contains(filterKey)).collect(Collectors.toList());

        String category = recordForm.getCategory();
        if (category != null && !category.equals("all") && category.length() > 0) {
            originalRecords = originalRecords.stream().filter(record -> record.getCategory().equals(category)).collect(Collectors.toList());
        }

        return originalRecords.stream()
                .sorted(Comparator.comparing(Record::getCategory))
                .sorted(Comparator.comparing(Record::getRecordTime).reversed())
                .collect(Collectors.toList());
    }


    private int checkDate(int recordTime) {
        if (String.valueOf(recordTime).length() == 7) {
            return recordTime;
        }
        int westYear = LocalDate.now().getYear();
        int rocYear = westYear - 1911;

        return Integer.parseInt(String.valueOf(rocYear) + recordTime);
    }
}
