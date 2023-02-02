package com.infuse.FeignClients;

import com.infuse.request.CreateAddressRequest;
import com.infuse.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//address.service.url is coming from properties file
//@FeignClient(url = "${address.service.url}" , value = "address-feign-client",
//                 path = "/api/address")

/*This feignclient is valid for address service only*/
//@FeignClient(value = "ADDRESS-SERVICE",
//             path = "/api/address")

/* we want to make one feignclient for all services so request response will be from api-gateway*/
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
      //add the controller and method from address service you want to call

      @GetMapping("/address-service/api/address/getbyid/{id}")
      AddressResponse getAddressById(@PathVariable long id);

      @PostMapping("/create")
      AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest);
}
