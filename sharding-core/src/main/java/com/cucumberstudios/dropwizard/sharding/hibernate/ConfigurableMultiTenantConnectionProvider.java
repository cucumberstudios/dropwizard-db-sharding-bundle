/*
 * Copyright 2018 Saurabh Agrawal (Cleartax)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.cucumberstudios.dropwizard.sharding.hibernate;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.util.Map;

public class ConfigurableMultiTenantConnectionProvider
        extends AbstractMultiTenantConnectionProvider {

    private final Map<String, ConnectionProvider> connectionProviderMap;

    public ConfigurableMultiTenantConnectionProvider(
            Map<String, ConnectionProvider> connectionProviderMap) {
        this.connectionProviderMap = ImmutableMap.copyOf(connectionProviderMap);
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return connectionProviderMap.values().iterator().next();
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
        return connectionProviderMap.get(tenantIdentifier);
    }
}