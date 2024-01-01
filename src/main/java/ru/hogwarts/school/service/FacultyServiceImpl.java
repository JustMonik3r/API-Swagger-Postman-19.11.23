package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty addFaculty(String name, String color) {
        logger.info("Был вызван метод addFaculty");
        Faculty newFaculty = new Faculty(name, color);
        return facultyRepository.save(newFaculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Был вызван метод getFaculty");
        if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(String.format("Faculty [%s] not found", id));
        }
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(long id, String name, String color) {
        logger.info("Был вызван метод updateFaculty");
        Faculty existingFaculty = facultyRepository.findById(id).get();
        existingFaculty.setName(name);
        existingFaculty.setColor(color);
        return facultyRepository.save(existingFaculty);
    }

    @Override
    public void removeFaculty(Long id) {
        logger.info("Был вызван метод removeFaculty");
        facultyRepository.deleteById(id);
        System.out.println(String.format("Faculty %s has been removed", id));
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        logger.info("Был вызван метод getFacultiesByColor");
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getColor() == color)
                .toList();
    }
    @Override
    public Set<Faculty> findFacultyByColorOrName(String param) {
        logger.info("Был вызван метод findFacultyByColorOrName");
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findFacultyByColor(param));
        result.addAll(facultyRepository.findFacultyByName(param));
        return result;
    }

    @Override
    public List<Faculty> findFacultyByName(String name) {
        logger.info("Был вызван метод findFacultyByName");
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getName() == name)
                .toList();
    }
    @Override
    public List<Faculty> findFacultyByColor(String color) {
        logger.info("Был вызван метод findFacultyByColor");
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getName() == color)
                .toList();
    }

    @Override
    public List<Student> getStudentsByFaculty(Faculty faculty) {
        logger.info("Был вызван метод getStudentsByFaculty");
        return studentService.findStudentsFromFaculty(faculty);
    }

    @Override
    public String getFacultyWithLongestName() {
        logger.info("Был вызван метод getFacultyWithLongestName");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max((name1, name2) -> name1.length() - name2.length())
                .get();
    }
}
