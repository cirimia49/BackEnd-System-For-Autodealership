package com.backendsystemforautodealership.service;

import com.backendsystemforautodealership.client.maps.MapsClient;
import com.backendsystemforautodealership.client.prices.PriceClient;
import com.backendsystemforautodealership.domain.vehicle.Vehicle;
import com.backendsystemforautodealership.domain.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implement all CRUD operations on a vehicle plus
 * that it gathers data about location and price
 */
@Service
public class VehicleService {

    private final PriceClient priceClient;
    private final VehicleRepository vehicleRepository;
    private final MapsClient mapsClient;

    public VehicleService(PriceClient priceClient, VehicleRepository vehicleRepository, MapsClient mapsClient) {
        this.priceClient = priceClient;
        this.vehicleRepository = vehicleRepository;
        this.mapsClient = mapsClient;
    }

    /**
     * Method to get all the vehicles and return them
     */
    public List<Vehicle> vehicleList (){
        return vehicleRepository.findAll();
    }

    /**
     * Method to find a vehicle by id
     */
    public Vehicle findById(Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

        vehicle.setPrice(priceClient.getPrice(vehicle.getId()));

        vehicle.setLocation(mapsClient.getAddress(vehicle.getLocation()));

        return vehicle;
    }

    /**
     * Method to save a new vehicle or to update one if it already exists
     */
    public Vehicle save(Vehicle vehicle){

        if(vehicle.getId() != null){
            return vehicleRepository.findById(vehicle.getId())
                    .map(updatedVehicle -> {
                        updatedVehicle.setDetails(vehicle.getDetails());
                        updatedVehicle.setLocation(vehicle.getLocation());
                        return vehicleRepository.save(updatedVehicle);
                    }).orElseThrow(VehicleNotFoundException::new);

        }
        return vehicleRepository.save(vehicle);
    }

    /**
     * Method to delete a car by id
     */
    public void delete(Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);
        vehicleRepository.delete(vehicle);
    }
}
