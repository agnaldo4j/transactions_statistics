package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.domain.persistence.PersistenceAdapter;
import org.springframework.stereotype.Component;
import com.agnaldo4j.persistence.PrevalentSystem;
import com.agnaldo4j.persistence.adapter.command.SaveTransaction;
import com.agnaldo4j.persistence.adapter.query.RetrieveStatistic;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Timer;

@Component
public class PrevalentSystemAdapter implements PersistenceAdapter {

    private PrevalentSystem<System> system;
    private final Timer timer;

    public PrevalentSystemAdapter() throws IOException, ClassNotFoundException {
        this("data.dat");
    }

    public PrevalentSystemAdapter(String storageFile) throws IOException, ClassNotFoundException {
        this(storageFile, null);
    }

    public PrevalentSystemAdapter(String storageFile, ZonedDateTime now) throws IOException, ClassNotFoundException {
        this.system = new PrevalentSystem<>(storageFile);
        this.system.load(new System());
        this.timer = new Timer("statisticUpdater");
        this.timer.scheduleAtFixedRate(new CalculateStatisticsTask(system, now), 100, 5000);
    }

    public void destroyState() throws IOException {
        this.timer.cancel();
        system.destroyState();
    }

    public void reloadState(ZonedDateTime now) throws IOException, ClassNotFoundException {
        system.load(new System());
        this.timer.scheduleAtFixedRate(new CalculateStatisticsTask(system, now), 100, 5000);
    }

    @Override
    public void save(Transaction transaction) throws IOException {
        system.execute(new SaveTransaction(transaction));
    }

    @Override
    public Statistic statistic() {
        return system.execute(new RetrieveStatistic());
    }
}
