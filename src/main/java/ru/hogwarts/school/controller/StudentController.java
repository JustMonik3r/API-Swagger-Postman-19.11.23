package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(Long id) {
        studentService.removeStudent(id);
    }

    @GetMapping(params = "age")
    public Collection<Student> getStudentsByAge(@RequestParam Integer age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/age-between")
    public List<Student> getStudentsWithAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.getStudentsWithAgeBetween(min, max);
    }

    @GetMapping("/faculty-id")
    public List<Student> getStudentsFromFaculty(@RequestParam Long facultyId) {
        return studentService.getStudentsFromFaculty(facultyId);
    }
}
