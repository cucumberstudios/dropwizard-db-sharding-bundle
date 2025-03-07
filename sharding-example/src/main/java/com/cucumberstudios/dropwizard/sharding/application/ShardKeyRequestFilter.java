/*
 * Copyright 2018 Cleartax
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

package com.cucumberstudios.dropwizard.sharding.application;

import com.cucumberstudios.dropwizard.sharding.providers.ShardKeyProvider;
import lombok.RequiredArgsConstructor;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION + 1)
@RequiredArgsConstructor
public class ShardKeyRequestFilter implements ContainerRequestFilter {

    private final ShardKeyProvider shardKeyProvider;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Using customer-id as header as well (for this test's purpose)
        final String customerId = requestContext.getHeaderString("X-Auth-Token");
        shardKeyProvider.setKey(customerId);
    }
}
