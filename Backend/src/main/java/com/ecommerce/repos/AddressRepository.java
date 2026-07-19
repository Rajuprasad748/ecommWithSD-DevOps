package com.ecommerce.repos;

import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository
        extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);

}