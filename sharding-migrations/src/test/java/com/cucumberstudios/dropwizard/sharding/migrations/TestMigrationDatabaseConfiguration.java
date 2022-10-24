package com.cucumberstudios.dropwizard.sharding.migrations;

import com.cucumberstudios.dropwizard.sharding.hibernate.MultiTenantDataSourceFactory;

public class TestMigrationDatabaseConfiguration implements MultiTenantDatabaseConfiguration<TestMigrationConfiguration> {

    @Override
    public MultiTenantDataSourceFactory getDataSourceFactory(TestMigrationConfiguration configuration) {
        return configuration.getDataSource();
    }
}
