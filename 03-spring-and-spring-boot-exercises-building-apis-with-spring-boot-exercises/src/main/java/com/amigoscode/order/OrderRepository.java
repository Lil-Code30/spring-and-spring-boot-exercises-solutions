package com.amigoscode.order;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 12 - Create the OrderRepository class:
//  - Annotate with @Repository
//  - Add a private List<Order> field initialized with sample orders (use ArrayList)
//  - Add a private long nextId field starting at 4
//  - Implement these methods:
//    List<Order> findAll()
//    Optional<Order> findById(Long id)
//    List<Order> findByStatus(String status)
//    Order save(Order order) — sets the id and adds to the list
//    void update(Order order) — finds by id and updates fields
//    boolean deleteById(Long id) — removes from list
//  Hint: look at CustomerRepository for reference

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>(List.of(
            new Order(1L, "Laptop", "PENDING", 999.99, "john@mail.com", LocalDate.now(), "rush"
            ),
            new Order(2L, "Smartphone", "SHIPPED", 699.99, "alice@mail.com", LocalDate.now().minusDays(2), "gift wrap"
            ),
            new Order(3L, "Headphones", "DELIVERED", 149.99, "bob@mail.com", LocalDate.now().minusDays(5), "leave at front door"
            ),
            new Order(4L, "Monitor", "PROCESSING", 299.99, "emma@mail.com", LocalDate.now().minusDays(1), "fragile"
            ),
            new Order(5L, "Mechanical Keyboard", "CANCELLED", 129.99, "david@mail.com", LocalDate.now().minusDays(3), "customer requested cancellation"
            ))
    );

    private Long nextId = 6L;

    public List<Order> findAll() {
        return orders;
    }

    public Optional<Order> findById(Long id){
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public List<Order> fingByStatus(String status){
        return orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public Order save(Order entity){
        entity.setId(nextId++);
        orders.add(entity);

        return  entity;
    }

    public boolean update(Long id, Order entity){
        Optional<Order> existing = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();

        if(existing.isPresent()){
            Order o = existing.get();

            o.setDescription(entity.getDescription());
            o.setStatus(entity.getStatus());
            o.setOrderDate(entity.getOrderDate());
            o.setCustomerEmail(entity.getCustomerEmail());
            o.setInternalNotes(entity.getInternalNotes());
            o.setTotalAmount(entity.getTotalAmount());

            return true;
        }

        return  false;
    }

    public void deleteById(Long id){
        Optional<Order> existing = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();

        existing.ifPresent(orders::remove);
    }
}