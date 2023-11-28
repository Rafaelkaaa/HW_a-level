package org.example.service;

import org.example.dao.RecordDao;
import org.example.entity.Record;

import java.util.List;
import java.util.UUID;

public class RecordService {
    private RecordDao recordDao = new RecordDao();

    public List<Record> findRecordsByAccountId(UUID accountId){
        return recordDao.findRecordsByAccountId(accountId).stream().toList();
    }
}
