package io.explore.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> buildMeterRegistry() {
        return registry -> {
            registry.config()
                    .commonTags("app", "jpa-with-micrometer","region", "local");
        };
    }
}
