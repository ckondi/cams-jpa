package com.cams.repository;

import com.cams.domain.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.customer.id=:customerId")
    List<Address> findAddressesForCustomer(@Param("customerId") Long customerId);

    @Query("SELECT a FROM Address a JOIN a.customer c WHERE c.id=:customerId AND a.id=:addressId")
    Optional<Address> findByCustomerIdAddressId(@Param("customerId") Long customerId, @Param("addressId") Long addressId);

}
