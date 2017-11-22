package com.quicktutorialz.learnmicroservices.StatisticsMicroservice.services;

import com.quicktutorialz.learnmicroservices.StatisticsMicroservice.daos.StatisticsDao;
import com.quicktutorialz.learnmicroservices.StatisticsMicroservice.entities.Statistics;
import com.quicktutorialz.learnmicroservices.StatisticsMicroservice.utilities.JsonResponseBody;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
@Log
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsDao statisticsDao;

    @Override
    public List<Statistics> getStatistics(String jwt, String email){

        //1. call ToDoMicroservice to get List<ToDos> (we need to result logged passing a valid JWT in the HEADER of the request)
        //   we get the valid jwt from the client (PostMan or Browser with HTML interface) which has done the login (first call)
        List<LinkedHashMap> todos = getNewDataFromToDoMicroservice(jwt);


        //2. calculate the statistics on the List of ToDos received
        String statisticsDescr = "No statistics available";
        if(todos !=null && todos.size()>0){
            int lowPriorityTodos = 0;
            int highPriorityTodos = 0;
            for(int i=0; i<todos.size(); i++){
                LinkedHashMap todo = todos.get(i);
                String priority = (String) todo.get("priority");
                if("low".equals(priority)) lowPriorityTodos++;
                if("high".equals(priority)) lowPriorityTodos++;

            }
            statisticsDescr = "You have <b>" + lowPriorityTodos + " low priority</b> ToDos and <b>" + highPriorityTodos + " high priority</b> ToDo";
        }

        //3. save the new statistic into statisticdb if a day has passed from the last update
        List<Statistics> statistics = statisticsDao.getLastStatistics(email);
        if(statistics.size()>0){
            Date now = new Date();
            long diff = now.getTime() - statistics.get(0).getDate().getTime();

            long days = diff/(24 * 60 * 60 * 1000);
            if(days>1){
                statistics.add(statisticsDao.save(new Statistics(null, statisticsDescr, new Date(), email)));
            }
        }
        //4. return the List of statistics
        return statistics;
    }


    private List<LinkedHashMap> getNewDataFromToDoMicroservice(String jwt){
        //prepare the header of request with jwt
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("jwt", jwt);
        HttpEntity<?> request = new HttpEntity(String.class, headers);

        //call ToDoMicroservice getting ResponseEntity<JsonResponseBody> as HTTP response
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8383/showToDos", HttpMethod.POST, request, JsonResponseBody.class);

        //extract from JsonResponseBody (recognized as java Object from JacksonLibrary) the "response" attribute.
        //This response attribute for "/showToDos" is a List<ToDos>. But in this microservice we haven't defined ToDo.java
        //then JacksonLibrary cannot interpret it as a List<ToDos> but it will interpret it as a List<LinkedHashMap>
        List<LinkedHashMap> todoList = (List) responseEntity.getBody().getResponse();

        return todoList;
    }

}
