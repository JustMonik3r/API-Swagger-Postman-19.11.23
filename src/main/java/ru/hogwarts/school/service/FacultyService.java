package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Set;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty getFaculty(Long id);
    Faculty updateFaculty(Long id, Faculty faculty);
    void removeFaculty(Long id);
    List<Faculty> getFacultiesByColor(String color);
    Set<Faculty> findFacultyByColorOrName(String param);

    List<Faculty> findFacultyByColor(String color);

    List<Faculty> findFacultyByName(String name);

    List<Student> getStudentsByFaculty(Faculty faculty);
}
