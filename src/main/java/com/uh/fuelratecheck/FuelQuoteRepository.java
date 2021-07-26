package com.uh.fuelratecheck;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import com.uh.fuelratecheck.FuelQuoteEntity;

@Repository
public interface FuelQuoteRepository extends CrudRepository<FuelQuoteEntity, Integer> {
    
    List<FuelQuoteEntity> findByUserid(Integer userid);

}