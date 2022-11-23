package com.example.demo.controller;

import com.example.demo.model.Record;
import com.example.demo.model.RecordForm;
import com.example.demo.repo.RecordRepo;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/record")
@RestController
public class RecordController {

    @Autowired
    private RecordRepo recordRepo;

    @Autowired
    private RecordService recordService;

    @RequestMapping("/save")
    public boolean saveRecord(@RequestBody Record record) {
        try {
            recordRepo.save(record);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/delete")
    public boolean deleteRecord(@RequestBody long id) {
        try {
            recordRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/findRecords")
    public List<Record> findRecords(@RequestBody RecordForm recordForm) {
        String filterKey = recordForm.getFilterKey();
        List<Record> originalRecords = (filterKey == null || filterKey.trim().isEmpty()) ?
                recordRepo.findAll() : recordRepo.findAll().stream().filter(record -> record.toString().contains(filterKey)).collect(Collectors.toList());

        //todo need impl search case

        return originalRecords;
    }
}
