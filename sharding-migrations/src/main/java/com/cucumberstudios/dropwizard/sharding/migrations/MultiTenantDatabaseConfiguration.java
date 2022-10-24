package com.cucumberstudios.dropwizard.sharding.migrations;

import com.cucumberstudios.dropwizard.sharding.hibernate.MultiTenantDataSourceFactory;
import io.dropwizard.Configuration;

/**
 * Created on 09/10/18
 */
public interface MultiTenantDatabaseConfiguration<T extends Configuration> {
    MultiTenantDataSourceFactory getDataSourceFactory(T configuration);
}
