package de.springwegarche.webpage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;

@Configuration
public class AppConfiguration {
    @Bean
    @Primary
    public TopicsService topicsServiceConfiguartion() {
        return new TopicsService();
    }
}
