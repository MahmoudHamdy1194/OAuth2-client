package com.mhamdy.handler;

import com.mhamdy.model.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

/**
 * Created by: Mahmoud Hamdy on 18/01/2023
 */
@Component
public class ResourceServerHandler {

    private static final Logger log = LogManager.getLogger(ResourceServerHandler.class);
    private final RestTemplate restTemplate;
    private final TokenHandler tokenHandler;

    @Value("${resource-server-url}")
    private String url;

    public ResourceServerHandler(RestTemplate restTemplate, TokenHandler tokenHandler) {
        this.restTemplate = restTemplate;
        this.tokenHandler = tokenHandler;
    }

    /**
     * Call the Resource Server by using the restTemplate provided by all info that server need (headers, Body..etc).
     *
     * @param token      the access token to the Resource Server.
     * @param creditCard object that we want to be sent to the Resource Server.
     * @return APi response of the Resource server.
     */
    public String callResourceServer(String token, CreditCard creditCard) {
        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, "Bearer " + token);

        // body
        MultiValueMap<String, String> body = constructBody(creditCard);

        // request - the body object must exist as a first parameter
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // response
        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);

        log.debug("Response from API Service: {}", response);
        return response.getBody();
    }

    /**
     * Construct to be accepted in HttpEntity body.
     */
    private MultiValueMap<String, String> constructBody(CreditCard creditCard) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("No", creditCard.getNo());
        body.add("Account Owner", creditCard.getName());
        body.add("CCV", creditCard.getCcv());
        body.add("Valid Thru", creditCard.getValidThru());
        return body;
    }
}
