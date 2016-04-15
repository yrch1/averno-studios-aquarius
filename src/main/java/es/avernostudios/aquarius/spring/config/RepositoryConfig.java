package es.avernostudios.aquarius.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dortega on 15/04/2016.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"es.avernostudios.aquarius.bean"})
@EnableJpaRepositories(basePackages = {"es.avernostudios.aquarius.jpa.repositories"})
@EnableTransactionManagement
@Slf4j
public class RepositoryConfig {
    public RepositoryConfig(){
      LOGGER.debug("Created");
    }
}