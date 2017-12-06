package com.arcosoft.arcLogChef.services.dish.impls;

import com.arcosoft.arcLogChef.PropertyConfigurator;
import com.arcosoft.arcLogChef.dto.DishInfo;
import com.arcosoft.arcLogChef.dto.GenericResponse;
import com.arcosoft.arcLogChef.dto.Query;
import com.arcosoft.arcLogChef.dto.QueryResponse;
import com.arcosoft.arcLogChef.exceptions.ChefException;
import com.arcosoft.arcLogChef.services.dish.DishService;
import com.arcosoft.arcLogChef.services.http.HttpUtility;
import com.arcosoft.arcLogChef.util.ConfigurationUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by princegupta on 15/11/17.
 */
@Service
public class DishInfoServiceImpl implements DishService {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DishInfoServiceImpl.class);


    @Autowired
    HttpUtility<DishInfo> dishInfoHttpUtility;

    @Autowired
    HttpUtility<String> stringHttpUtility;


    @Autowired
    PropertyConfigurator configurator;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Override
    public TreeSet<DishInfo> getDishsInfo() throws ChefException {
        Collection<Callable<DishInfo>> tasksList = new HashSet<>(configurator.getHost().size());

        configurator.getHost().values()
                .stream()
                .forEach(url -> tasksList.add(() -> dishInfoHttpUtility.get(url + "/info/", DishInfo.class)));

        ExecutorService dishPool = Executors.newCachedThreadPool();
        try {
            List<Future<DishInfo>> responseList = dishPool.invokeAll(tasksList);

            TreeSet<DishInfo> resultList = new TreeSet<>(Comparator.comparing(DishInfo::getName));
            responseList.stream()
                    .forEach(dishInfoFuture -> {
                        DishInfo dishInfo;
                        try {
                            dishInfo = dishInfoFuture.get();
                            resultList.add(dishInfo);
                        } catch (InterruptedException | ExecutionException e) {
                            LOGGER.error("Got Exception while fetching dish info : ",e);
                            String message = e.getMessage();
                            resultList.add(processStaleDish(message));
                        }

                    });

            return resultList;
        } catch (InterruptedException exceptions) {
            throw new ChefException(exceptions.getCause().getMessage(), exceptions.getLocalizedMessage());
        }
    }

    @Override
    public DishInfo getDishInfo(String id) {
        DishInfo dishInfo;
        try {
            dishInfo = dishInfoHttpUtility.get(configurator.getHost().get(id) + "/info/", DishInfo.class);
        } catch (HttpStatusCodeException ex) {
            return processStaleDish(ex.getMessage());
        } catch (RestClientException ex) {
            return processStaleDish(ex.getMessage());
        }
        return dishInfo;
    }

    @Override
    public String shutdownDish(String id) {
        return dishInfoHttpUtility.post(configurator.getHost().get(id) + "/management/shutdown");
    }

    public Set<QueryResponse> queryDishs(Query query) throws ChefException {

        String queryString = query.getQueryString();
        Map<String, String> hostMap;
        if (query.getDishId() == null) {
            hostMap = configurator.getHost();
        } else {
            hostMap = new HashMap<>();
            hostMap.put(query.getDishId(), configurator.getHost().get(query.getDishId()));
        }
        Collection<Callable<Map<String, List<String>>>> tasksList = new HashSet<>(hostMap.size());

        hostMap.values()
                .stream()
                .forEach(url -> tasksList.add(() -> dishInfoHttpUtility.post(url + "/query", queryString)));


        ExecutorService dishPool = Executors.newCachedThreadPool();
        try {
            List<Future<Map<String, List<String>>>> resultList = dishPool.invokeAll(tasksList);
            Set<QueryResponse> responseList = new TreeSet<>(Comparator.comparing(QueryResponse::getDishName));

            resultList.stream()
                    .forEach(result -> {
                        QueryResponse queryResponse = new QueryResponse();
                        try {
                            Map<String, List<String>> map = result.get();
                            map.keySet().forEach(key -> {
                                queryResponse.setDishName(key);
                                queryResponse.setResult(map.get(key));
                            });
                            responseList.add(queryResponse);
                        } catch (InterruptedException | ExecutionException exception) {
                            LOGGER.error(exception.getCause().getMessage(), exception.getLocalizedMessage());
                        }
                    });
            return responseList;
        } catch (InterruptedException exception) {
            throw new ChefException(exception.getCause().getMessage(), exception.getLocalizedMessage());
        }
    }

    @Override
    public GenericResponse reIndex(String dishId) {
        String response = stringHttpUtility.get(configurator.getHost().get(dishId) + "/index/reIndex", String.class);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setStatus("200");
        genericResponse.addData("response", response);
        return genericResponse;
    }

    private DishInfo processStaleDish(String message) {
        String staleDishUrl = message.substring(message.indexOf("http"), message.indexOf("/info"));
        DishInfo dishInfo = new DishInfo();
        String dishId = configurationUtil.getDishId(staleDishUrl);
        dishInfo.setId(dishId);
        dishInfo.setName(dishId);
        dishInfo.setHost(configurationUtil.getHostName(staleDishUrl));
        dishInfo.setRunning(false);
        return dishInfo;
    }

}
