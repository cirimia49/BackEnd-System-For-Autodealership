package com.backendsystemforautodealership.API;

import com.backendsystemforautodealership.domain.vehicle.Vehicle;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Maps the CarController to the Car class using HATEOAS
 */
@Component
public class VehicleResourceAssembler implements ResourceAssembler<Vehicle, Resource<Vehicle>> {

    @Override
    public Resource<Vehicle> toResource(Vehicle vehicle) {
        return new Resource<>(vehicle,
                linkTo(methodOn(VehicleController.class).get(vehicle.getId())).withSelfRel(),
                linkTo(methodOn(VehicleController.class).list()).withRel("cars"));

    }
}
