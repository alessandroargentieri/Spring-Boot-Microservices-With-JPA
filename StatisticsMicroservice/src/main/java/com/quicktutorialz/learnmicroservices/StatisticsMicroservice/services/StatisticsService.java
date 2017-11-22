package com.quicktutorialz.learnmicroservices.StatisticsMicroservice.services;

import com.quicktutorialz.learnmicroservices.StatisticsMicroservice.entities.Statistics;

import java.util.List;

public interface StatisticsService {

    List<Statistics> getStatistics(String jwt, String email);
}
