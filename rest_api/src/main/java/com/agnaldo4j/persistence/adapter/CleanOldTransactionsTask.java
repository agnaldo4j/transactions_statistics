package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.persistence.PrevalentSystem;
import com.agnaldo4j.persistence.adapter.command.CleanOldTransactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class CleanOldTransactionsTask extends TimerTask {

    private final static Logger logger = LoggerFactory.getLogger(CleanOldTransactionsTask.class);
    public static final int PERIOD_FOR_CLEAN_SECONDS = 120;
    private final PrevalentSystem<System> system;
    private ClockSystem clockSystem;

    public CleanOldTransactionsTask(PrevalentSystem<System> system, ClockSystem clockSystem) {
        this.clockSystem = clockSystem;
        this.system = system;
    }

    public void run() {
        try {
            long time = clockSystem.nowMinusSeconds(PERIOD_FOR_CLEAN_SECONDS);
            system.execute(new CleanOldTransactions(time));
        } catch (Exception e) {
            logger.error("Error when clean old transactions", e);
        }
    }
}
