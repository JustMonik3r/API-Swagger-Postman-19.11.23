package ru.hogwarts.school.service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(String name, Integer ag);
    Student getStudent(long id);
    Student updateStudent(long id, String name, Integer age);
    Student removeStudent(long id);
    List<Student> findStudentsByAge(Integer age);
    List<Student> findStudentsWithAgeBetween(Integer min, Integer max);
    List<Student> findStudentsFromFaculty(Faculty faculty);
    Integer getCount();
    Double getAvgAge();
    List<Student> getLastFiveAddedStudents();
    List<String> getStudentsWithNamesStartingWithA();
    double getAvgAgeWithStream();

}
