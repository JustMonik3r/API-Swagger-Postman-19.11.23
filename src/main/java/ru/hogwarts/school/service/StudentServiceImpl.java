package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(String name, Integer age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(String.format("Student [%s] not found", id));
        }
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(long id, String name, Integer age) {
        Student existingStudent = getStudent(id);
        existingStudent.setName(name);
        existingStudent.setAge(age);
        return studentRepository.save(existingStudent);
    }

    @Override
    public Student removeStudent(long id) {
        Student existingStudent = getStudent(id);
        studentRepository.deleteById(id);
        return existingStudent;
    }

    @Override
    public List<Student> findStudentsByAge(Integer age) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }
    @Override
    public List<Student> findStudentsWithAgeBetween(Integer min, Integer max) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() >= min && student.getAge() <= max)
                .toList();
    }

   @Override
    public List<Student> findStudentsFromFaculty(Faculty faculty) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getFaculty() == faculty)
                .toList();
    }

    @Override
    public Integer getCount() {
        return studentRepository.getCount();
    }

    @Override
    public Double getAvgAge() {
        return studentRepository.getAvgAge();
    }

    @Override
    public List<Student> getLastFiveAddedStudents() {
        return studentRepository.getLastFiveAddedStudents();
    }


}
