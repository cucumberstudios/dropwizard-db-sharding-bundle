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

package com.cucumberstudios.dropwizard.sharding.services;

import com.cucumberstudios.dropwizard.sharding.dao.OrderDao;
import com.cucumberstudios.dropwizard.sharding.dto.OrderDto;
import com.cucumberstudios.dropwizard.sharding.dto.OrderMapper;
import com.cucumberstudios.dropwizard.sharding.entities.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.JDBCException;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper = new OrderMapper();

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws JDBCException {
        Order order = orderDao.save(orderMapper.from(orderDto));
        return orderMapper.to(order);
    }

    @Override
    public OrderDto getOrder(long orderId) throws JDBCException {
        Order order = orderDao.get(orderId);
        return orderMapper.to(order);
    }
}
