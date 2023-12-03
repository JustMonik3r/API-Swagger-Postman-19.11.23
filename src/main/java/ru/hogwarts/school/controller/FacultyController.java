package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id){
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public void removeFaculty(Long id) {
        facultyService.removeFaculty(id);
    }

    /*@GetMapping(params = "color")
    public Collection<Faculty> getFacultiesByColor(@RequestParam String color) {
        return facultyService.getFacultiesByColor(color);
    }*/

    @GetMapping("/faculty-color")
    public List<Faculty> findFacultyByColor(@RequestParam String color) {
        return facultyService.findFacultyByColor(color);
    }

    @GetMapping("/faculty-color-or-name")
    public Set<Faculty> findFacultyByColorOrName(@RequestParam String param) {
        return facultyService.findFacultyByColorOrName(param);
    }

    @GetMapping("/students-faculty")
    public List<Student> getStudentsByFaculty(@RequestParam Faculty faculty) {
        return facultyService.getStudentsByFaculty(faculty);
    }
}
