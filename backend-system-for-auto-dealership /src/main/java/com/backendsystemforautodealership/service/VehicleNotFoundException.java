package com.backendsystemforautodealership.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Vehicle has not been found")
public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(){}


}
