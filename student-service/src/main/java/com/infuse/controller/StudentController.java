package com.infuse.controller;


import com.infuse.entity.Student;
import com.infuse.request.CreateStudentRequest;
import com.infuse.response.StudentResponse;
import com.infuse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
	StudentService studentService;

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/getById/{id}")
    public StudentResponse getById(@PathVariable long id) {
        return studentService.getById(id);
    }

    @GetMapping("/getall")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

}
