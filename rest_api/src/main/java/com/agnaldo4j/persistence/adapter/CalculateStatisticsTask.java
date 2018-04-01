package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.agnaldo4j.persistence.PrevalentSystem;
import com.agnaldo4j.persistence.adapter.command.UpdateStatistic;
import com.agnaldo4j.persistence.adapter.query.CalculateStatistics;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimerTask;

public class CalculateStatisticsTask extends TimerTask {

    private final static Logger logger = LoggerFactory.getLogger(CalculateStatisticsTask.class);
    public static final int PERIOD_FOR_STATISTICS_SECONDS = 60;
    private final PrevalentSystem<System> system;
    private ZonedDateTime now;

    public CalculateStatisticsTask(PrevalentSystem<System> system, ZonedDateTime now) {
        if (now == null) this.now = ZonedDateTime.now( ZoneOffset.UTC );
        else this.now = now;
        this.system = system;
    }

    public void run() {
        try {
            long time = now.minusSeconds(PERIOD_FOR_STATISTICS_SECONDS).toInstant().toEpochMilli();
            Statistic newStatistic = system.execute(new CalculateStatistics(time));
            system.execute(new UpdateStatistic(newStatistic));
            this.now = ZonedDateTime.now( ZoneOffset.UTC );
        } catch (Exception e) {
            logger.error("Error when update statistic", e);
        }
    }
}
