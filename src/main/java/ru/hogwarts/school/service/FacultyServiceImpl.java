package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        Faculty newFaculty = new Faculty(faculty.getName(), faculty.getColor());
        return facultyRepository.save(newFaculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        /*if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(String.format("Faculty [%s] not found", id));
        }*/
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
}
