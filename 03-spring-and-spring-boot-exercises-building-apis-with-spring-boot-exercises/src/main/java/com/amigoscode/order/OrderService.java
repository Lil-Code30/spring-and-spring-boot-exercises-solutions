package com.amigoscode.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: 11 - Create the OrderService class:
//  - Annotate with @Service
//  - Inject OrderRepository via constructor injection
//  - Implement these methods:
//    List<Order> getAllOrders()
//    Optional<Order> getOrderById(Long id)
//    List<Order> getOrdersByStatus(String status)
//    Order createOrder(Order order)
//    void updateOrder(Order order)
//    boolean deleteOrder(Long id)
//  Hint: look at CustomerService for reference


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByStatus(String status){
        return orderRepository.fingByStatus(status);
    }

    public Order createOrder(Order newOrder){
        return orderRepository.save(newOrder);
    }

    public boolean updateOrder(Long id, Order updatedOrder){
        return orderRepository.update(id, updatedOrder);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

}
