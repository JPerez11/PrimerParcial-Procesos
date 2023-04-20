package com.procesos.parcial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Bean Configuration class.
 */
@Configuration
public class BeanConfiguration {

    /**
     * Method to instantiate a RestTemplate Bean in the Spring container
     * @return The RestTemplate class.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
