package com.uh.fuelratecheck;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uh.fuelratecheck.FuelQuoteModel;

@Repository
public interface FuelQuoteRepository extends CrudRepository<FuelQuoteModel, Integer> {
    

}