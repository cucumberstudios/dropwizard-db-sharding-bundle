package com.cucumberstudios.dropwizard.sharding.migrations;

import io.dropwizard.Configuration;
import liquibase.Liquibase;
import net.sourceforge.argparse4j.inf.Namespace;

public class DbClearChecksumsCommand<T extends Configuration> extends AbstractLiquibaseCommand<T> {
    public DbClearChecksumsCommand(MultiTenantDatabaseConfiguration<T> strategy, Class<T> configurationClass, String migrationsFileName) {
        super("clear-checksums",
                "Removes all saved checksums from the database log",
                strategy,
                configurationClass,
                migrationsFileName);
    }

    @Override
    public void run(Namespace namespace,
                    Liquibase liquibase) throws Exception {
        liquibase.clearCheckSums();
    }
}
