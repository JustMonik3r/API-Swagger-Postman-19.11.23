package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Faculty getFaculty(@RequestParam long id){
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty.getName(), faculty.getColor());
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    @DeleteMapping
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

    @GetMapping("/longest-name")
    public String getFacultyWithLongestName() {
        return facultyService.getFacultyWithLongestName();
    }
}
