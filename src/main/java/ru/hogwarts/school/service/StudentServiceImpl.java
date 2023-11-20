package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private Map<Long, Student> studentMap = new HashMap<>();
    private int counter = 0;

    @Override
    public Student addStudent(Student student) {
        long id = counter++;
        Student newStudent = new Student(id, student.getName(), student.getAge());
        studentMap.put(id, newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentMap.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public void removeStudent(Long id) {
        studentMap.remove(id);
        System.out.println(String.format("Student %s has been removed", id));
    }
}
