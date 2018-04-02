package com.agnaldo4j.restapi.controllers;

import com.agnaldo4j.restapi.json.APIViews;
import com.agnaldo4j.restapi.services.StatisticService;
import com.agnaldo4j.restapi.view.StatisticsOutput;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;

    @JsonView(APIViews.Public.class)
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public StatisticsOutput get() {
        return new StatisticsOutput(statisticService.get());
    }
}
