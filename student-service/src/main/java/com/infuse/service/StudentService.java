package com.infuse.service;

import com.infuse.FeignClients.AddressFeignClient;
import com.infuse.entity.Student;
import com.infuse.repository.StudentRepository;
import com.infuse.request.CreateAddressRequest;
import com.infuse.request.CreateStudentRequest;
import com.infuse.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;


    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        CreateAddressRequest addressRequest = new CreateAddressRequest();
        addressRequest.setCity(createStudentRequest.getCity());
        addressRequest.setStreet(createStudentRequest.getStreet());
        addressFeignClient.createAddress(addressRequest);
        studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId()));
        return studentResponse;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


//    this is webclient way we are now using feignclient
//    public AddressResponse getAddressById(long addressId)
//    {
//        Mono<AddressResponse> addressResponse = webClient.get().uri("/getbyid/" + addressId)
//                .retrieve().bodyToMono(AddressResponse.class);
//        return addressResponse.block();
//    }




}
