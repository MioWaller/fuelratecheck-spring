package com.uh.fuelratecheck;

import org.springframework.data.repository.CrudRepository;
import com.uh.fuelratecheck.ClientInfoEntity;
import java.util.List;

public interface ClientInfoRepository extends CrudRepository<ClientInfoEntity, Integer> {
    
    List<ClientInfoEntity> findByUserid(Integer userid);
}
