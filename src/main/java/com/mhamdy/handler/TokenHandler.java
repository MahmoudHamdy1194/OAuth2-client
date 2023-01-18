package com.mhamdy.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 * Created by: Mahmoud Hamdy on 18/01/2023
 */
@Component
public class TokenHandler {
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    @Value("${spring.security.oauth2.client.registration.mhamdy.client-name}")
    private String clientRegistrationName;
    @Value("${spring.security.oauth2.client.registration.mhamdy.client-id}")
    private String clientId;

    public TokenHandler(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    /**
     * Register authorizeRequest to the (authorization server) with the proper client credentials.
     *
     * @return the access token value.
     */
    public String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationName)
                .principal(clientId)
                .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        return accessToken.getTokenValue();
    }

}
