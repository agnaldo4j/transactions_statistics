package com.agnaldo4j.restapi.services;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.persistence.PersistenceAdapter;
import com.agnaldo4j.restapi.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StatisticService {

    @Autowired
    private PersistenceAdapter persistenceAdapter;

    public Statistic get() {
        try {
            return persistenceAdapter.statistic();
        } catch (IOException e) {
            throw new PersistenceException("Error when try persit transaction");
        }
    }
}
