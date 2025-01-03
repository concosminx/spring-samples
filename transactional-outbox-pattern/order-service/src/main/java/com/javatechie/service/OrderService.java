package com.nimsoc.service;

import com.nimsoc.common.dto.OrderRequestDTO;
import com.nimsoc.common.mapper.OrderDTOtoEntityMapper;
import com.nimsoc.common.mapper.OrderEntityToOutboxEntityMapper;
import com.nimsoc.entity.Order;
import com.nimsoc.entity.Outbox;
import com.nimsoc.repository.OrderRepository;
import com.nimsoc.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDTOtoEntityMapper orderDTOtoEntityMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private OrderEntityToOutboxEntityMapper orderEntityToOutboxEntityMapper;


    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);

        return order;
    }
}
