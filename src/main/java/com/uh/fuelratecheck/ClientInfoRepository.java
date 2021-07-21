package com.uh.fuelratecheck;

import org.springframework.data.repository.CrudRepository;
import com.uh.fuelratecheck.ClientInfoEntity;

public interface ClientInfoRepository extends CrudRepository<ClientInfoEntity, Integer> {
    

}
