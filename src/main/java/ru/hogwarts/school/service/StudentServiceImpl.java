package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
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
    public Student addStudent(Student student) {
        Student newStudent = new Student(student.getName(), student.getAge(), student.getFacultyID());
        return studentRepository.save(newStudent);
    }

    @Override
    public Student getStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(String.format("Student [%s] not found", id));
        }
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).get();
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
        System.out.println(String.format("Student %s has been removed", id));
    }

    @Override
    public List<Student> getStudentsByAge(Integer age) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }
    @Override
    public List<Student> getStudentsWithAgeBetween(Integer min, Integer max) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() >= min && student.getAge() <= max)
                .toList();
    }

   @Override
    public List<Student> getStudentsFromFaculty(Long facultyId) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getFacultyID() == facultyId)
                .toList();
    }

}
