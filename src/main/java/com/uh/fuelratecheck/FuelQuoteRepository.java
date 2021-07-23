package com.uh.fuelratecheck;

import org.springframework.data.repository.CrudRepository;

import com.uh.fuelratecheck.FuelQuoteModel;

public interface FuelQuoteRepository extends CrudRepository<FuelQuoteModel, Integer> {
    

}