package com.uh.fuelratecheck;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//import com.uh.fuelratecheck.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    
    List<ClientEntity> findByUsername(String username);

}