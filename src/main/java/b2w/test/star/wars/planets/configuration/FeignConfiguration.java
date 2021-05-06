package b2w.test.star.wars.planets.configuration;

import b2w.test.star.wars.planets.thirdparty.swapi.SwapiApi;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfiguration {

    @Value("${swapi.api.url}")
    private String swapiUrl;

    @Bean
    public SwapiApi swapiApi() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(SwapiApi.class))
                .logLevel(Logger.Level.FULL)
                .target(SwapiApi.class, swapiUrl);
    }

}
