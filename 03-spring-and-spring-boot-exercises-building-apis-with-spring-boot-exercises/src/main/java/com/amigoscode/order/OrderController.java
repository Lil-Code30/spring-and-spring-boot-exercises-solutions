package com.amigoscode.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

// TODO: 9 - Add @RequestMapping("/api/v1/orders") at the class level
//  Then simplify all endpoint paths below (remove "/api/v1/orders" prefix)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    List<Order> orders = List.of(
            new Order(
                    1L,
                    "Laptop",
                    "PENDING",
                    999.99,
                    "john@mail.com",
                    LocalDate.now(),
                    "rush"
            ),
            new Order(
                    2L,
                    "Smartphone",
                    "SHIPPED",
                    699.99,
                    "alice@mail.com",
                    LocalDate.now().minusDays(2),
                    "gift wrap"
            ),
            new Order(
                    3L,
                    "Headphones",
                    "DELIVERED",
                    149.99,
                    "bob@mail.com",
                    LocalDate.now().minusDays(5),
                    "leave at front door"
            ),
            new Order(
                    4L,
                    "Monitor",
                    "PROCESSING",
                    299.99,
                    "emma@mail.com",
                    LocalDate.now().minusDays(1),
                    "fragile"
            ),
            new Order(
                    5L,
                    "Mechanical Keyboard",
                    "CANCELLED",
                    129.99,
                    "david@mail.com",
                    LocalDate.now().minusDays(3),
                    "customer requested cancellation"
            )
    );

    // TODO: 13 - Inject OrderService via constructor injection
    //  (replace direct data access with service calls)

    // TODO: 1 - Create a GET endpoint mapped to "/api/v1/orders/welcome"
    //  that returns the string "Welcome to the Orders API"
    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage(){
        return ResponseEntity.ok().body("Welcome to the Orders API");
    }

    // TODO: 2 - Create a GET endpoint mapped to "/api/v1/orders/sample"
    //  that returns a single hardcoded Order object
    //  Hint: new Order(1L, "Laptop", "PENDING", 999.99, "john@mail.com", LocalDate.now(), "rush")
    @GetMapping("/sample")
    public ResponseEntity<Order> getSample(){
        Order sample =  new Order(1L,
                "Laptop",
                "PENDING",
                999.99,
                "john@mail.com",
                LocalDate.now(),
                "rush");

        return ResponseEntity.ok().body(sample);
    }

    // TODO: 3 - Create a GET endpoint mapped to "/api/v1/orders"
    //  that returns a hardcoded List of Order objects
    //  Hint: use List.of(...)\
    @GetMapping()
    public ResponseEntity<List<Order>>getAllOrders(){
         return ResponseEntity.ok().body(orders);
    }

    // TODO: 4 - Create a GET endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and returns an Order
    //  For now, return a hardcoded Order with the given id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){

        Optional<Order> order =  orders.stream().filter(o -> o.getId().equals(id)).findFirst();

//        order.isPresent() ? ResponseEntity.ok().body(order) : ResponseEntity.notFound().build();

        return order.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: 5 - Create a GET endpoint mapped to "/api/v1/orders/filter"
    //  that takes a @RequestParam(required = false) String status
    //  Return a filtered list if status is provided, otherwise return all
    @GetMapping("/filter")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam(required = false) String status){
        List<Order> orderList;

        if(status != null){
            orderList = orders.stream()
                    .filter(o -> o.getStatus().equalsIgnoreCase(status)).toList();
        }else{
            orderList = orders;
        }

        return ResponseEntity.ok().body(orderList);
    }

    // TODO: 6 - Create a POST endpoint mapped to "/api/v1/orders"
    //  that takes an Order @RequestBody and returns the saved order
    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder){
        orders.add(newOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // TODO: 7 - Create a PUT endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and @RequestBody Order
    //  Set the id on the order and update it
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder){
        Optional<Order> foundOrder = orders.stream()
                .filter(o -> o.getId().equals(id)).findFirst();

        if(foundOrder.isPresent()){
            Order o = foundOrder.get();

             o.setDescription(updatedOrder.getDescription());
             o.setStatus(updatedOrder.getStatus());
             o.setOrderDate(updatedOrder.getOrderDate());
             o.setCustomerEmail(updatedOrder.getCustomerEmail());
             o.setInternalNotes(updatedOrder.getInternalNotes());
             o.setTotalAmount(updatedOrder.getTotalAmount());
        }

        return ResponseEntity.noContent().build();
    }

    // TODO: 8 - Create a DELETE endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and deletes the order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        Optional<Order> foundOrder = orders.stream()
                .filter(o -> o.getId().equals(id)).findFirst();

//        foundOrder.ifPresent(o -> orders.remove(o));

        if(foundOrder.isPresent()){
            orders.remove(foundOrder.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // TODO: 10 - Refactor all endpoints to return ResponseEntity<> with proper status codes:
    //  - GET returns 200 (OK)
    //  - POST returns 201 (Created)
    //  - PUT returns 204 (No Content)
    //  - DELETE returns 204 or 404
    //  Hint: look at CustomerController for reference

}
