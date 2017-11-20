package com.arcosoft.arcLogChef.services.http;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by princegupta on 15/11/17.
 */
@Component
public class HttpUtility<T> {

    Client client;

    @PostConstruct
    public void setupClient(){
        this.client =  ClientBuilder.newClient();
    }

    public Response invokeReguest(String targetUrl, String requestPath){
        return this.client.target(targetUrl)
                .path(requestPath)
        .request(MediaType.APPLICATION_JSON)
        .get();
    }

    public T get(String fullUri, Class<T> classz){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(fullUri, classz);
    }

    public String post(String fullUri){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");
        return restTemplate.postForObject(fullUri, request, String.class);
    }

    public Map<String,List<String>> post(String fullUri, String body){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("queryString", body);
        return restTemplate.postForObject(fullUri, map, Map.class);
    }
}
