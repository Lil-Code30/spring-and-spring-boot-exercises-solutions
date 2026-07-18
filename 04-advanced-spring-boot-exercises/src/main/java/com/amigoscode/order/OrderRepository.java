package com.amigoscode.order;

// TODO: 10 - Replace this class with a JpaRepository interface:
//  public interface OrderRepository extends JpaRepository<Order, Long>
//  Delete all the manual List-based code — JPA handles everything
//  Then update OrderService to use JPA methods (findAll, findById, save, deleteById)

// TODO: 11 - Add derived query methods:
//  List<Order> findByStatus(String status);
//  List<Order> findByCustomerEmail(String email);

// TODO: 12 - Add a custom @Query:
//  @Query("SELECT o FROM Order o WHERE o.totalAmount > :amount")
//  List<Order> findByTotalAmountGreaterThan(@Param("amount") double amount);

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);
    List<Order> findByCustomerEmail(String email);

    @Query("SELECT o FROM Order o WHERE o.totalAmount > :amount")
    List<Order> findByTotalAmountGreaterThan(@Param("amount") double amount);

}
