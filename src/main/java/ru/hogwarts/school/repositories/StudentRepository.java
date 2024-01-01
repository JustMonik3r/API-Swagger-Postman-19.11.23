package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    /*List<Student> getStudentsWithAgeBetween(Integer min, Integer max);
    List<Student> getStudentsFromFaculty (Long facultyId);*/

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM student",
            nativeQuery = true
    )
    Integer getCount();

    @Query(
            value = "SELECT AVG(s.age) " +
                    "FROM student s",
            nativeQuery = true
    )
    Double getAvgAge();

    @Query(
            value = "SELECT * " +
                    "FROM student " +
                    "ORDER BY id DESC " +
                    "LIMIT 5",
            nativeQuery = true
    )
    List<Student> getLastFiveAddedStudents();
}
