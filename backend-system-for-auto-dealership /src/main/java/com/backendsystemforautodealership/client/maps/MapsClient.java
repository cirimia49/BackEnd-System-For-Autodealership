package com.backendsystemforautodealership.client.maps;

import com.backendsystemforautodealership.domain.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
public class MapsClient {

    private final WebClient webClient;
    private final ModelMapper modelMapper;

    public MapsClient(WebClient webClient, ModelMapper modelMapper) {
        this.webClient = webClient;
        this.modelMapper = modelMapper;
    }

    public Location getAddress(Location location){

       try {
           Address address = webClient
                   .get()
                   .uri(uriBuilder -> uriBuilder
                           .path("/maps")
                           .queryParam("latitude", location.getLatitude())
                           .queryParam("longitude", location.getLongitude())
                           .build()
                   )
                   .retrieve().bodyToMono(Address.class).block();
           modelMapper.map(Objects.requireNonNull(address), location);

           return location;

       }catch(Exception exception){
           System.out.println("The service to get maps is DOWN" + "\n"
                                + "Try again later!");
           return location;
       }


    }
}
