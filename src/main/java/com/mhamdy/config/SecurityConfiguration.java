package com.mhamdy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;

/**
 * Configuring the needed bean for OAuth2.
 * <p>
 * Created by: Mahmoud Hamdy on 18/01/2023
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Adding OAuth2AuthorizedClientManager to the Spring Context.
     * use AuthorizedClientServiceOAuth2AuthorizedClientManager if you operate outside HttpServletRequest context, otherwise use DefaultOAuth2AuthorizedClientManager.
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientRepository) {
        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    /**
     * RestTemplate to call an exposed endpoint (the resource server).
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
