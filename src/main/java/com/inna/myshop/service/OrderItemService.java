package com.inna.myshop.service;

import com.inna.myshop.model.Order;
import com.inna.myshop.model.OrderItem;
import com.inna.myshop.repository.OrderItemRepository;
import com.inna.myshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
//        Optional<Order> order = orderRepository.findById(orderItem.getOrder().getId());
//        System.out.println("createOrderItem. Id: " + orderItem.getOrder().getId() + ". IsPresent: " + order.isPresent());
//        if (order.isPresent()) {
//            orderItem.setOrder(order.get());
//            return orderItemRepository.save(orderItem);
//        }
//        return null;
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);

        if (existingOrderItem.isPresent()) {
            updatedOrderItem.setId(id);
            return orderItemRepository.save(updatedOrderItem);
        } else {
            //
            return null;
        }
    }

    public boolean deleteOrderItem(Long id) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);

        if (existingOrderItem.isPresent()) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
