package com.infuse.service;

import com.infuse.entity.Address;
import com.infuse.repository.AddressRepository;
import com.infuse.request.CreateAddressRequest;
import com.infuse.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressResponse getById(long id) {
        logger.info("Inside getById "+ id);
        Address address = addressRepository.findById(id).get();
        return new AddressResponse(address);

    }

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());
        addressRepository.save(address);
        return new AddressResponse(address);
    }
}
