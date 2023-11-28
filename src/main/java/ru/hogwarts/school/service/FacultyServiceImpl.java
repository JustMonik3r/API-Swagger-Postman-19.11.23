package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        Faculty newFaculty = new Faculty(faculty.getName(), faculty.getColor());
        return facultyRepository.save(newFaculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(String.format("Faculty [%s] not found", id));
        }
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyRepository.findById(id).get();
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
        System.out.println(String.format("Faculty %s has been removed", id));
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getColor() == color)
                .toList();
    }
    @Override
    public Set<Faculty> findFacultyByColorOrName(String param) {
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findFacultyByColor(param));
        result.addAll(facultyRepository.findFacultyByName(param));
        return result;
    }

    @Override
    public List<Faculty> findFacultyByName(String name) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getName() == name)
                .toList();
    }
    @Override
    public List<Faculty> findFacultyByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getName() == color)
                .toList();
    }

    @Override
    public List<Student> getStudentsByFacultyID(Long id){
        return studentService.getStudentsFromFaculty(id);
    };
}
