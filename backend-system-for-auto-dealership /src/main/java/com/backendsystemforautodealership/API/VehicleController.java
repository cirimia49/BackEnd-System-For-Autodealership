package com.backendsystemforautodealership.API;

import com.backendsystemforautodealership.domain.vehicle.Vehicle;
import com.backendsystemforautodealership.domain.vehicle.VehicleRepository;
import com.backendsystemforautodealership.service.VehicleService;
import org.omg.GIOP.VersionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;


import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


/**
 * This class implements a REST Controller
 */

@RestController
@RequestMapping("/cars")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleResourceAssembler vehicleResourceAssembler;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleResourceAssembler vehicleResourceAssembler) {
        this.vehicleService = vehicleService;
        this.vehicleResourceAssembler = vehicleResourceAssembler;
    }


    /**
     * Now lets create a list of vehicles and make sure we also can
     * return that
     */

    @GetMapping
    Resources<Resource<Vehicle>> list() {
        List<Resource<Vehicle>> resources = vehicleService.vehicleList()
                .stream()
                .map(vehicleResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(VehicleController.class).list()).withSelfRel());
    }

    /**
     * Method to get the details about a vehicle based on the id
     */
    @GetMapping
    Resource<Vehicle> get(@PathVariable Long id){
        Vehicle vehicle = vehicleService.findById(id);
        return vehicleResourceAssembler.toResource(vehicle);
    }

    /**
     * Method to add a new vehicle in the system
     * or handles the exceptions that could be thrown
     */

    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Vehicle vehicle) throws URISyntaxException{

        Vehicle savedVehicle = vehicleService.save(vehicle);
        Resource<Vehicle> resource = vehicleResourceAssembler.toResource(savedVehicle);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);

    }

    /**
     * Method to update the information of a vehicle if that one
     * already exists
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put (@PathVariable Long id, @Valid @RequestBody Vehicle vehicle){

        vehicle.setId(id);
        vehicle = vehicleService.save(vehicle);
        return ResponseEntity.ok(vehicleResourceAssembler.toResource(vehicle));

    }

    /**
     * Method to delete a vehicle
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
