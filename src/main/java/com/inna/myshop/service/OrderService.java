package com.inna.myshop.service;

import com.inna.myshop.model.Order;
import com.inna.myshop.model.OrderItem;
import com.inna.myshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            order.addOrderItem(orderItem);
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        } else {
            // Handle the case where the order with the given ID doesn't exist.
            // You might want to throw an exception or handle it differently based on your requirements.
            return null;
        }
    }

    public boolean deleteOrder(Long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
