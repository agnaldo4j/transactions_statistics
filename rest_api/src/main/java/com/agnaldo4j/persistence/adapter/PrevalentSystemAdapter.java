package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.domain.persistence.PersistenceAdapter;
import com.agnaldo4j.persistence.adapter.command.CleanOldTransactions;
import com.agnaldo4j.persistence.adapter.query.CountTransactions;
import org.springframework.stereotype.Component;
import com.agnaldo4j.persistence.PrevalentSystem;
import com.agnaldo4j.persistence.adapter.command.SaveTransaction;
import com.agnaldo4j.persistence.adapter.query.RetrieveStatistic;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Timer;

@Component
public class PrevalentSystemAdapter implements PersistenceAdapter {

    public static final int CLAEN_TRANSACTION_PERIOD = 180 * 1000;
    public static final int UPDATE_STATISTIC_PERIOD = 3000;
    public static final int UPDATE_STATISTIC_DELAY = 100;
    private PrevalentSystem<System> system;
    private final Timer timer;
    private final ClockSystem clockSystem;

    public PrevalentSystemAdapter() throws IOException, ClassNotFoundException {
        this("data.dat");
    }

    public PrevalentSystemAdapter(String storageFile) throws IOException, ClassNotFoundException {
        this(storageFile, new ZoneDateTimeClockSystem());
    }

    public PrevalentSystemAdapter(String storageFile, ClockSystem clockSystem) throws IOException, ClassNotFoundException {
        this.system = new PrevalentSystem<>(storageFile);
        this.system.load(new System());
        this.clockSystem = clockSystem;
        this.timer = new Timer("statisticUpdater");
        this.timer.scheduleAtFixedRate(new CalculateStatisticsTask(system, clockSystem), UPDATE_STATISTIC_DELAY, UPDATE_STATISTIC_PERIOD);
        this.timer.scheduleAtFixedRate(new CleanOldTransactionsTask(system, clockSystem), CLAEN_TRANSACTION_PERIOD, CLAEN_TRANSACTION_PERIOD);

    }

    public void destroyState() throws IOException {
        this.timer.cancel();
        system.destroyState();
    }

    public void reloadState() throws IOException, ClassNotFoundException {
        system.load(new System());
        this.timer.scheduleAtFixedRate(new CalculateStatisticsTask(system, clockSystem), UPDATE_STATISTIC_DELAY, UPDATE_STATISTIC_PERIOD);
        this.timer.scheduleAtFixedRate(new CleanOldTransactionsTask(system, clockSystem), CLAEN_TRANSACTION_PERIOD, CLAEN_TRANSACTION_PERIOD);
    }

    public int countTransactions() {
        return system.execute(new CountTransactions());
    }

    public void cleanOldTransactions(long time) throws IOException {
        system.execute(new CleanOldTransactions(time));
    }

    @Override
    public ZonedDateTime now() {
        return this.clockSystem.now();
    }

    @Override
    public long nowMinusSeconds(long seconds) {
        return this.clockSystem.nowMinusSeconds(seconds);
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
