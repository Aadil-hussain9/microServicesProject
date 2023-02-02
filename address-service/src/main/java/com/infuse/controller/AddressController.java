package com.infuse.controller;

import com.infuse.entity.Address;
import com.infuse.request.CreateAddressRequest;
import com.infuse.response.AddressResponse;
import com.infuse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/getbyid/{id}")
    public AddressResponse getAddressById(@PathVariable long id)
    {
        return addressService.getById(id);
    }

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest)
    {
        return addressService.createAddress(createAddressRequest);
    }


}
