package com.example.ReactiveReddit.services.AuthAndFetch;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;

public class FetchSubreddit {
    public static String getSubredditsFromApi(String subReddit) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = AuthToken.getAuthToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent", Collections.singletonList("tomcat:com.e4developer.e4reddit-test:v1.0 (by /u/Longjumping_Form4980)"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = "https://oauth.reddit.com/r/" + subReddit + "/rising";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
