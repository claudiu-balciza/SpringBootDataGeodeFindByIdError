package org.Test.config;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.Test.domain.Class1;
import org.Test.repositories.GeodeRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@EnableEntityDefinedRegions(basePackageClasses = Class1.class, clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
@EnableGemfireRepositories(basePackageClasses = GeodeRepository.class)
@Configuration
class GeodeConfiguration {
}
