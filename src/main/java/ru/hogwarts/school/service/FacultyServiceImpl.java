package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService{

    private Map<Long, Faculty> facultyMap = new HashMap<>();
    private long counter = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        long id = counter++;
        Faculty newFaculty = new Faculty(id, faculty.getName(), faculty.getColor());
        facultyMap.put(id,newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(String.format("Faculty [%s] not found", id));
        }
        return facultyMap.get(id);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyMap.get(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public void removeFaculty(Long id) {
        facultyMap.remove(id);
        System.out.println(String.format("Faculty %s has been removed", id));
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(faculty -> faculty.getColor() == color)
                .toList();
    }
}
