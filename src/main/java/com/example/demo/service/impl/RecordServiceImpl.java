package com.example.demo.service.impl;

import com.example.demo.repo.RecordRepo;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepo recordRepo;
}
