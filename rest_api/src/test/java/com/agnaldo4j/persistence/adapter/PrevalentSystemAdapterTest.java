package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;
import org.junit.Test;

import java.time.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class PrevalentSystemAdapterTest {

    private final ClockSystem clockSystem;

    public PrevalentSystemAdapterTest() {
        this.clockSystem = new MockClockSystem();
    }

    private long timeMinus(long seconds) {
        return this.clockSystem.nowMinusSeconds(seconds);
    }

    private void waitMillis() throws Exception {
        TimeUnit.MILLISECONDS.sleep(500L);
    }

    private void initialState(PrevalentSystemAdapter prevalentSystemAdapter) throws Exception {
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(200)));
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(180)));
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(150)));
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(120)));
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(90)));
        prevalentSystemAdapter.save(new Transaction(-100, timeMinus(80)));
        prevalentSystemAdapter.save(new Transaction(200, timeMinus(75)));
        prevalentSystemAdapter.save(new Transaction(23, timeMinus(70)));
        prevalentSystemAdapter.save(new Transaction(13, timeMinus(65)));
        prevalentSystemAdapter.save(new Transaction(-16, timeMinus(60)));
        prevalentSystemAdapter.save(new Transaction(16, timeMinus(55)));
        prevalentSystemAdapter.save(new Transaction(10, timeMinus(50)));
        prevalentSystemAdapter.save(new Transaction(-5, timeMinus(45)));
        prevalentSystemAdapter.save(new Transaction(20, timeMinus(40)));
        prevalentSystemAdapter.save(new Transaction(-10, timeMinus(35)));
        prevalentSystemAdapter.save(new Transaction(25, timeMinus(30)));
        prevalentSystemAdapter.save(new Transaction(-3, timeMinus(25)));
        prevalentSystemAdapter.save(new Transaction(2, timeMinus(20)));
        prevalentSystemAdapter.save(new Transaction(20, timeMinus(15)));
        prevalentSystemAdapter.save(new Transaction(-7, timeMinus(10)));
        prevalentSystemAdapter.save(new Transaction(-17, timeMinus(5)));
    }

    @Test
    public void saveTransactionsAndGetStatistics() throws Exception {
        PrevalentSystemAdapter prevalentSystemAdapter = new PrevalentSystemAdapter("data1.dat", this.clockSystem);
        prevalentSystemAdapter.reloadState();
        initialState(prevalentSystemAdapter);
        waitMillis();
        Statistic statistic = prevalentSystemAdapter.statistic();
        prevalentSystemAdapter.destroyState();
        assertEquals(12, statistic.count());
        assertEquals(35, statistic.sum(), 0);
        assertEquals(2.916, statistic.avg(), 0.001);
        assertEquals(25, statistic.max(), 0);
        assertEquals(-17, statistic.min(), 0);
    }

    @Test
    public void reloadTransactionsAndGetStatistics() throws Exception {
        PrevalentSystemAdapter prevalentSystemAdapter = new PrevalentSystemAdapter("data2.dat", this.clockSystem);
        prevalentSystemAdapter.reloadState();
        initialState(prevalentSystemAdapter);
        waitMillis();
        prevalentSystemAdapter = new PrevalentSystemAdapter("data2.dat", this.clockSystem);
        prevalentSystemAdapter.reloadState();
        Statistic statistic = prevalentSystemAdapter.statistic();
        prevalentSystemAdapter.destroyState();
        assertEquals(12, statistic.count());
        assertEquals(35, statistic.sum(), 0);
        assertEquals(2.916, statistic.avg(), 0.001);
        assertEquals(25, statistic.max(), 0);
        assertEquals(-17, statistic.min(), 0);
    }

    @Test
    public void cleanTransactionsAndGetCount() throws Exception {
        PrevalentSystemAdapter prevalentSystemAdapter = new PrevalentSystemAdapter("data4.dat", this.clockSystem);
        prevalentSystemAdapter.reloadState();
        initialState(prevalentSystemAdapter);
        waitMillis();
        assertEquals(21, prevalentSystemAdapter.countTransactions());
        prevalentSystemAdapter.cleanOldTransactions(clockSystem.nowMinusSeconds(120));
        prevalentSystemAdapter.destroyState();
        assertEquals(18, prevalentSystemAdapter.countTransactions());
    }

    @Test
    public void emptyStateAndGetStatistics() throws Exception {
        PrevalentSystemAdapter prevalentSystemAdapter = new PrevalentSystemAdapter("data3.dat", this.clockSystem);
        prevalentSystemAdapter.reloadState();
        Statistic statistic = prevalentSystemAdapter.statistic();
        prevalentSystemAdapter.destroyState();
        assertEquals(0, statistic.count());
        assertEquals(0, statistic.sum(), 0);
        assertEquals(0, statistic.avg(), 0);
        assertEquals(0, statistic.max(), 0);
        assertEquals(0, statistic.min(), 0);
    }
}
