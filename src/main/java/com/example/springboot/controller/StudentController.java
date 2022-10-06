package com.example.springboot.controller;

import com.example.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "John", "Jakob");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "test")
                .body(student);
    }

    // http://localhost:8080/students

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Jakob"));
        students.add(new Student(2, "Tom", "Hardy"));
        students.add(new Student(3, "Tony", "Stark"));
        return ResponseEntity.ok(students);
    }

    // Spring Boot REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId) {
        Student student = new Student(studentId, "John", "Jakob");
        return ResponseEntity.ok(student);
    }


    // http://localhost:8080/students/1/john/jakob
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable1(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName) {

        return new Student(studentId, firstName, lastName);
    }


    // Spring Boot REST API with Path Variable
    // http://localhost:8080/students/query?id=1&firstName=Tony&lastName=Stark
    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring Boot REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody

    @PostMapping("/students/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("/students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("/students/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }

}
