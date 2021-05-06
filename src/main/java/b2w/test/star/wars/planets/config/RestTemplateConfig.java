package b2w.test.star.wars.planets.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, HttpComponentsClientHttpRequestFactory factory) {
        var restTemplate = restTemplateBuilder
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();

        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory(CloseableHttpClient httpClient) {
        var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        return factory;
    }

    @Bean
    public LaxRedirectStrategy redirectStrategy() {
        return new LaxRedirectStrategy();
    }

    @Bean
    public CloseableHttpClient httpClient(LaxRedirectStrategy redirectStrategy) {
        return HttpClientBuilder.create()
                .setRedirectStrategy(redirectStrategy)
                .build();
    }

}
