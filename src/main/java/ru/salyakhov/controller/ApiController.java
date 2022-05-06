package ru.salyakhov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.salyakhov.entity.User;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class ApiController {

    private final String URL = "http://94.198.50.185:7081/api/users";
    private final String URL3 = "http://94.198.50.185:7081/api/users/3";


    private final RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public  String getSeesionId() {
       ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
       List result = response.getHeaders().get("Set-Cookie");
       String sessionId = "";
        for (int i = 0; i < result.size(); i++) {
            sessionId += result.get(i);
        }
        return sessionId;
    }

    public String userPost(String sessionId, User userPost){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(userPost, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity,String.class).getBody();
    }

    public String userPut(String sessionId, User userPut){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(userPut, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity,String.class).getBody();
    }

    public String userDelete(String sessionId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL3, HttpMethod.DELETE, entity,String.class).getBody();
    }
}
