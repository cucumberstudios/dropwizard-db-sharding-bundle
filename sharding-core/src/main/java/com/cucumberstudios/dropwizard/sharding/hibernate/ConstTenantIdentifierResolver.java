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

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class ConstTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    private final String tenantId;

    public ConstTenantIdentifierResolver(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
