package com.epam.amatias;

import com.epam.amatias.rest.CloudHashMapClient;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Aleksei_Matias on 3/23/2017.
 */
@Configuration
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig {
    @Bean
    CloudHashMapClient cloudHashMapClient() {
        return connectionFactory().service(CloudHashMapClient.class);
    }
}
