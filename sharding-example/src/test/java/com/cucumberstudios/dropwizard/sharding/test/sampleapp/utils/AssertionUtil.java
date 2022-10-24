package com.cucumberstudios.dropwizard.sharding.test.sampleapp.utils;

import com.cucumberstudios.dropwizard.sharding.application.TestApplication;
import com.cucumberstudios.dropwizard.sharding.application.TestConfig;
import com.cucumberstudios.dropwizard.sharding.dao.OrderDao;
import com.cucumberstudios.dropwizard.sharding.dto.OrderDto;
import com.cucumberstudios.dropwizard.sharding.entities.Order;
import com.cucumberstudios.dropwizard.sharding.hibernate.ConstTenantIdentifierResolver;
import com.cucumberstudios.dropwizard.sharding.hibernate.MultiTenantSessionSource;
import com.cucumberstudios.dropwizard.sharding.transactions.DefaultUnitOfWorkImpl;
import com.cucumberstudios.dropwizard.sharding.transactions.TransactionContext;
import com.cucumberstudios.dropwizard.sharding.transactions.TransactionRunner;
import io.dropwizard.testing.junit.DropwizardAppRule;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mohitsingh on 17/12/18.
 */
public class AssertionUtil {

    public static void assertOrderPresentOnShard(final String expectedOnShard, final OrderDto orderDto,
                                                 List<String> shards, DropwizardAppRule<TestConfig> RULE) throws Throwable {
        final GuiceBundle<TestConfig> guiceBundle = ((TestApplication) RULE.getApplication()).getGuiceBundle();
        final OrderDao orderDao = guiceBundle.getInjector().getInstance(OrderDao.class);
        final MultiTenantSessionSource multiTenantSessionSource = guiceBundle.getInjector()
                .getInstance(MultiTenantSessionSource.class);
        for (final String eachShard : shards) {
            new TransactionRunner<Order>(multiTenantSessionSource.getUnitOfWorkAwareProxyFactory(),
                    multiTenantSessionSource.getSessionFactory(), new ConstTenantIdentifierResolver(eachShard), TransactionContext.create(AssertionUtil.class.getEnclosingMethod())) {
                @Override
                public Order run() {
                    Order order = orderDao.get(orderDto.getId());
                    if (eachShard.equals(expectedOnShard)) {
                        assertThat(order)
                                .describedAs(String.format("Expecting order with id: %s, " +
                                                "for customer: %s, on shard: %s",
                                        orderDto.getId(), orderDto.getCustomerId(), eachShard))
                                .isNotNull();
                    } else {
                        // Two orders with same ID can exist on different shard
                        assertThat(order == null || !order.getCustomerId().equals(orderDto.getCustomerId()))
                                .describedAs(String.format("Not expecting order with id: %s, " +
                                                "for customer: %s, on shard: %s",
                                        orderDto.getId(), orderDto.getCustomerId(), eachShard))
                                .isTrue();
                    }
                    return order;
                }
            }.start(false, new DefaultUnitOfWorkImpl());
        }
    }
}
