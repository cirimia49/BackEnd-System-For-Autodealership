package com.pricing.repository;

import com.pricing.domain.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * Make this interface repository which extends
 * CrudRepository for different operations
 */

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @RestResource(path="byVehicleId")
    public Price findByVehicleId(@Param("id") Long vehicleId);
}
