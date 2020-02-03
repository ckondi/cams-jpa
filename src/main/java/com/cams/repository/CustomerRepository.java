package com.cams.repository;

import com.cams.domain.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c from Customer c where c.lastName = ?1")
    List<Customer> findByLastName(String lastName);

}
