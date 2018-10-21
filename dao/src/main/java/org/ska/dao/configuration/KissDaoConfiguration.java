package org.ska.dao.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 06/09/18.
 */

@Configuration
@ComponentScan("org.ska.dao")
@EntityScan(basePackages = {"org.ska.dao.entity"})
@EnableJpaRepositories(basePackages = {"org.ska.dao.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class KissDaoConfiguration {
}
