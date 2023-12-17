package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Student getStudent(@RequestParam long id){
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student.getName(), student.getAge());
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student.getName(), student.getAge());
    }

    @DeleteMapping
    public void removeStudent(@RequestParam long id) {
        studentService.removeStudent(id);
    }

    @GetMapping(params = "age")
    public Collection<Student> getStudentsByAge(@RequestParam Integer age) {
        return studentService.findStudentsByAge(age);
    }

    @GetMapping("/age-between")
    public List<Student> getStudentsWithAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.findStudentsWithAgeBetween(min, max);
    }

    @GetMapping("/faculty-id")
    public List<Student> getStudentsFromFaculty(@RequestParam Faculty faculty) {
        return studentService.findStudentsFromFaculty(faculty);
    }
}
