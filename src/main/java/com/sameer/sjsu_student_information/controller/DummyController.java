package com.sameer.sjsu_student_information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.sameer.sjsu_student_information.service.StudentService;

@RestController
public class DummyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/dummy")
    public String dummy() {
        return "Hello World";
    }

    @GetMapping("/student-travel-info")
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/student-phone")
    public String getStudentPhone(@RequestParam String name) {
        String phoneNumber = studentService.getStudentPhoneNumber(name);
        return "Phone number for " + name + ": " + phoneNumber;
    }
}
