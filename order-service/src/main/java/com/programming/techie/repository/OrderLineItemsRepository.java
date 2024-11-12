package com.programming.techie.repository;

import com.programming.techie.entity.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems,Long> {
}
