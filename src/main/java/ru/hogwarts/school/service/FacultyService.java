package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Set;

public interface FacultyService {
    Faculty addFaculty(String name, String color);
    Faculty getFaculty(Long id);
    Faculty updateFaculty (long id, String name, String color);
    void removeFaculty(Long id);
    List<Faculty> getFacultiesByColor(String color);
    Set<Faculty> findFacultyByColorOrName(String param);

    List<Faculty> findFacultyByColor(String color);

    List<Faculty> findFacultyByName(String name);

    List<Student> getStudentsByFaculty(Faculty faculty);
}
