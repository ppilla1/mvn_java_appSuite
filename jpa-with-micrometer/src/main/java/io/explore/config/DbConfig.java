package io.explore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
public class DbConfig {

    @ConfigurationProperties(prefix = "h2.datasource")
    @Bean(name = "dsProperties")
    Map<String, Object> h2DsProperties(){
        return new HashMap<>();
    }

    @Bean
    @Primary
    HikariDataSource dataSource(@Qualifier("dsProperties") Map<String, Object> dsProperties) {
        Map<String, Object> dsProp = (Map<String, Object>) dsProperties.get("dsProperties");
        log.info("DB Properties:\n{}", dsProperties.get("dsProperties"));
        HikariConfig config = new HikariConfig();
        config.setPoolName("JPA_EXPLORE");
        config.setJdbcUrl(dsProp.get("url").toString());
        config.setDriverClassName(dsProp.get("driverClassName").toString());
        config.setUsername(dsProp.get("username").toString());
        config.setPassword(dsProp.get("password").toString());

        /**
         * This property controls the maximum size that the pool is
         * allowed to reach, including both idle and in-use connections.
         */
        config.setMaximumPoolSize(5);

        /**
         * This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool.
         * If the idle connections dip below this value and total connections in the pool are less than maximumPoolSize,
         * HikariCP will make a best effort to add additional connections quickly and efficiently. However, for maximum
         * performance and responsiveness to spike demands, we recommend not setting this value and instead allowing
         * HikariCP to act as a fixed size connection pool.
         */
        config.setMinimumIdle(1);

        /**
         * This property controls the maximum lifetime of a connection in the pool.
         * An in-use connection will never be retired, only when it is closed will
         * it then be removed. On a connection-by-connection basis, minor negative
         * attenuation is applied to avoid mass-extinction in the pool. We strongly
         * recommend setting this value, and it should be several seconds shorter than
         * any database or infrastructure imposed connection time limit. A value
         * of 0 indicates no maximum lifetime (infinite lifetime), subject of course
         * to the idleTimeout setting
         */
        config.setMaxLifetime(Duration.ofSeconds(30).toMillis());

        /**
         * This property controls how frequently HikariCP will attempt to keep a
         * connection alive, in order to prevent it from being timed out by the
         * database or network infrastructure. This value must be less than the
         * maxLifetime value. A "keepalive" will only occur on an idle connection.
         * When the time arrives for a "keepalive" against a given connection, that
         * connection will be removed from the pool, "pinged", and then returned to
         * the pool. The 'ping' is one of either: invocation of the JDBC4 isValid()
         * method, or execution of the connectionTestQuery.
         */
        config.setKeepaliveTime(Duration.ofSeconds(30).toMillis());

        /**
         * This property controls the maximum number of milliseconds
         * that a client (that's you) will wait for a connection from the pool.
         * If this time is exceeded without a connection becoming available, a SQLException
         * will be thrown
         */
        config.setConnectionTimeout(Duration.ofSeconds(30).toMillis());

        /**
         * This property controls the maximum amount of time that a connection is allowed to sit idle in the pool.
         * This setting only applies when minimumIdle is defined to be less than maximumPoolSize
         */

        HikariDataSource datasource = new HikariDataSource(config);
        return datasource;
    }
}
